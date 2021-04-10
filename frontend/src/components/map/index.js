import React, { useState, useEffect } from "react";
import GoogleMap from 'google-map-react';
import './index.css'
import mapStyles from './mapStyles'
import { connect, useDispatch } from 'react-redux';
import CollegeMarker from './Marker/collegeMarker.js'
import RouteClusterMarker from './routeClusterMarker.js'
import ClusterMarker from './Marker/clusterMarker.js';
import supercluster from 'points-cluster';
import Slider from '@material-ui/core/Slider';
import { ClusterSlider } from './clusterSlider'
import { findCenter } from './geocoordinateCalculations';
import { renderDirections, clearDirections } from './directionsRenderer'

const MAP = {
    defaultZoom: 5,
    defaultCenter: { lat: 37.5, lng: -97 }, // center of US (slightly adjusted)
    options: {
        styles: mapStyles.basic,
        disableDefaultUI: true,
        // minZoom: 4.3
    },
};

function Map(props) {
    const [clustersDisplayed, setClustersDisplayed] = useState([]);
    const [defaultColleges, setDefaultColleges] = useState([]);
    const [mapOptions, setMapOptions] = useState({});
    const [sliderValue, setSliderValue] = useState(20);

    const dispatch = useDispatch();

    const handleSliderChange = (event, value) => {
        setSliderValue(value);

    }


    // request default colleges when page is first loaded
    useEffect(() => {
        dispatch({
            type: 'REQUEST_DEFAULT_COLLEGES',
        })
    }, [])

    // parse default colleges and store in local state right after we get them from backend
    useEffect(() => {
        setDefaultColleges(Object.values(props.defaultColleges).map((c, index) => ({
            id: c.id,
            lat: c.lat,
            lng: c.lon
        })))
    }, [props.defaultColleges])

    // calculate clusters
    const getCollegeClusters = () => {
        console.log(mapOptions)
        const clusters = supercluster(defaultColleges, {
            minZoom: 0,
            maxZoom: 16,
            radius: 60,
        });
        return clusters(mapOptions);
    };

    function getCurrentRouteCluster() {
        return Object.values(Object.values(props.route)[props.selectedCluster]);
    }


    const createClusters = () => {
        setClustersDisplayed(
            mapOptions.hasOwnProperty("bounds") ?
                getCollegeClusters().map(({ wx, wy, numPoints, points }) => ({
                    lat: wy,
                    lng: wx,
                    numPoints,
                    id: `${numPoints}_${points[0].id}`,
                    points,
                }))
                : [],
        );
    };

    const handleMapChange = ({ center, zoom, bounds }) => { setMapOptions({ center, zoom, bounds });};

    useEffect(createClusters, [mapOptions])

    function getRouteClusters() {
        return props.route.map(cluster =>
            findCenter(Object.values(cluster).map(college => [college.lat, college.lon]))
        )
    }

  
    const handleApiLoaded = (map, maps) => {
        dispatch({
            payload: { map, maps },
            type: 'ON_LOADED',
        })
    }

    useEffect(() => {
        if ((props.mapRef !== null) && (props.mapsRef !== null)) {
            const center = props.mapRef.getCenter();
            const bounds = props.mapRef.getBounds();
            const ne = { lat: bounds.getNorthEast().lat(), lng: bounds.getNorthEast().lng() };
            const sw = { lat: bounds.getSouthWest().lat(), lng: bounds.getSouthWest().lng() }
            const mapBounds = {
                ne: ne,
                nw: { lat: ne.lat, lng: sw.lng },
                se: { lat: sw.lat, lng: ne.lng },
                sw: sw,
            }
            handleMapChange({
                center: { lat: center.lat(), lng: center.lng() },
                zoom: props.mapRef.getZoom(),
                bounds: mapBounds
            })
        }
    }
        , [props.mapRef, props.mapsRef])

    const distanceToMouse = (pt, mp) => {
        if (pt && mp) {
            // return distance between the marker and mouse pointer
            return Math.sqrt(
                (pt.x - mp.x) * (pt.x - mp.x) + (pt.y - mp.y) * (pt.y - mp.y)
            );
        }
    };

    useEffect(() => {
        if (props.viewport === 'zoomedIn') {
            const currentCluster = getCurrentRouteCluster();
            const waypts = [];
            for (let i = 1; i < currentCluster.length - 1; i++) {
                waypts.push({
                    location: new window.google.maps.LatLng(currentCluster[i].lat, currentCluster[i].lon),
                    stopover: true,
                });
            }

            const start = new window.google.maps.LatLng(currentCluster[0].lat, currentCluster[0].lon);
            const end = new window.google.maps.LatLng(
                currentCluster[currentCluster.length - 1].lat, currentCluster[currentCluster.length - 1].lon);
            renderDirections(props.mapRef, start, end, waypts);

            dispatch({
                payload: { sidebar: 'routeInfo' },
                type: 'NAVIGATE_SIDEBAR'
            })
        } else {
            if (props.mapRef !== null) {
                props.mapRef.setCenter(MAP.defaultCenter)
                props.mapRef.setZoom(MAP.defaultZoom)
                clearDirections();
            }
        }
    }, [props.viewport])

    return (
        <>
        <div className="mapContainer">
            <GoogleMap
                bootstrapURLKeys={{ key: 'AIzaSyBIJk5AqilYH8PHt2TP4f5d7QY-UxtJf58' }} //process.env.REACT_APP_GOOGLE_KEY
                defaultCenter={MAP.defaultCenter}
                defaultZoom={MAP.defaultZoom}
                options={MAP.options}
                onChange={handleMapChange}
                distanceToMouse={distanceToMouse}
                yesIWantToUseGoogleMapApiInternals
                onGoogleApiLoaded={({ map, maps }) => { handleApiLoaded(map, maps) }}
            >

                {(props.viewport === 'default') && clustersDisplayed.map(item => {
                    if (item.numPoints === 1) {
                        return (
                            <CollegeMarker
                                collegeID={item.points[0].id}
                                lat={item.points[0].lat}
                                lng={item.points[0].lng}
                            />
                        );
                    }

                    // return (
                    //     <ClusterMarker
                    //         index={item.id}
                    //         lat={item.lat}
                    //         lng={item.lng}
                    //         points={item.points}
                    //     />
                    // );

                })}

                {(props.viewport === 'clusters') &&
                    getRouteClusters().map((cluster, index) => (
                        <RouteClusterMarker index={index} lat={cluster[0]} lng={cluster[1]} />
                    ))
                }

                {(props.viewport === 'zoomedIn') &&
                    getCurrentRouteCluster().map((college, index) => (
                        <div lat={college.lat} lng={college.lon}> {college.name}</div>
                    ))
                }

            </GoogleMap>
        </div>
        {(props.viewport === 'clusters') &&
            <div className="sliderContainer">
                <ClusterSlider valueLabelDisplay="off" defaultValue={300} step={60} min={240} max={420} 
                value={sliderValue} onChangeCommitted={handleSliderChange}/>
            </div>
         }
        </>
    );
}


const mapStateToProps = ({ rMap: { mapRef, mapsRef, defaultColleges, selectedCluster, viewport, }, rUser: { user, route } }) =>
    ({ mapRef, mapsRef, defaultColleges, selectedCluster, viewport, user, route });

export default connect(mapStateToProps)(Map);