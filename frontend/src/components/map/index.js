import React, { useState, useEffect } from "react";
import GoogleMap from 'google-map-react';
import './index.css'
import mapStyles from './mapStyles'
import { connect, useDispatch } from 'react-redux';
import CollegeMarker from './markers/collegeMarker.js'
import RouteClusterMarker from './markers/routeClusterMarker.js'
import ClusterMarker from './markers/clusterMarker.js';
import supercluster from 'points-cluster';
import ClusterSlider from './clusterSlider'
import { findCenter } from './geocoordinateCalculations';
import { renderDirections, clearDirections } from './directionsRenderer'
import airportIcon from '../../assets/mapsSVG/airport.svg';
import locationIcon from '../../assets/mapsSVG/location.svg';
import { routeActions } from '../../actions/routeActions'
import RouteReloader from './routeReloader'

const MAP = {
    defaultZoom: 5,
    defaultCenter: { lat: 37.5, lng: -97 }, // center of US (slightly adjusted)
    options: {
        styles: mapStyles.basic,
        // disableDefaultUI: true,
        // minZoom: 4.3
    },
};

function Map(props) {
    const [clustersDisplayed, setClustersDisplayed] = useState([]);
    const [defaultColleges, setDefaultColleges] = useState([]);
    const [mapOptions, setMapOptions] = useState({});


    const dispatch = useDispatch();

    // request default colleges when page is first loaded
    useEffect(() => {
        dispatch({
            type: 'REQUEST_DEFAULT_COLLEGES',
        })
    }, [])

    // parse default colleges and store in redux store right after we get them from backend
    useEffect(() => {
        // console.log(props.defaultColleges)
        setDefaultColleges(Object.values(props.defaultColleges).map((c) => ({
            id: c.id,
            lat: c.lat,
            lng: c.lon
        })))
    }, [props.defaultColleges])

    // calculate clusters
    const getCollegeClusters = () => {
        const clusters = supercluster(defaultColleges, {
            minZoom: 0,
            maxZoom: 16,
            radius: 60,
        });
        return clusters(mapOptions);
    };

    // reset map center, zoom, bounds whenever map changes
    const handleMapChange = ({ center, zoom, bounds }) => {
        setMapOptions({ center, zoom, bounds });
    };

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

    // create clusters whenever map changes
    useEffect(createClusters, [mapOptions])

    function getRouteClusters() {
        return props.route.map(cluster =>
            findCenter(Object.values(cluster).map(college => [college.lat, college.lon]))
        )
    }

    const handleApiLoaded = (map, maps) => {
        // store a reference to the GoogleMap map and maps object in redux store
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
        if (props.viewport !== 'zoomedIn' && props.mapRef !== null) {
            props.mapRef.setCenter(MAP.defaultCenter)
            props.mapRef.setZoom(MAP.defaultZoom)
            clearDirections();
            clearMarkers();
        }
    }, [props.viewport])

    useEffect(() => {
        // draw route when route reloads, or visibility option changed
        if (props.showRoute && props.selectedCluster !== '' && props.route !== null) {
            clearDirections();
            clearMarkers();
            drawRoute();
            drawMarkers();
            dispatch({
                payload: { sidebar: 'routeInfo' },
                type: 'NAVIGATE_SIDEBAR'
            })
        } else {
            // clear route when visibility option is changed
            clearDirections();
        }
    }, [props.route, props.showRoute])

    function getCurrentRouteCluster() {
        return Object.values(Object.values(props.route)[props.selectedCluster]);
    }

    const drawRoute = () => {
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
    }

    const [markers, setMarkers] = useState([]);
    const drawMarkers = () => {
        const locations = getCurrentRouteCluster();
        for (let i = 0; i < locations.length; i++) {
            let marker = new window.google.maps.Marker({
                position: new window.google.maps.LatLng(locations[i].lat, locations[i].lon),
                map: props.mapRef,
                icon: {
                    url: locations[i].type === "airport" ? airportIcon : locationIcon,
                    scaledSize: new window.google.maps.Size(50, 50),
                    labelOrigin: new window.google.maps.Point(25, 16.5),
                },
                label: locations[i].type === "airport" ? {} : {
                    text: "" + i,
                    fontWeight: 'bold',
                    fontSize: '20px',
                    fontFamily: '"Inter", sans-serif',
                    color: 'white'
                },
            });
            marker.setMap(props.mapRef)

            const infowindow = new window.google.maps.InfoWindow({
                content: locations[i].name,
            });

            marker.addListener("mouseover", () => {
                if (!props.tooltip.includes("zoomedIn")) {
                    dispatch(routeActions.addTooltipShowed("zoomedIn"))
                }
                infowindow.open(props.mapRef, marker);
            });

            marker.addListener("mouseout", () => {
                infowindow.close(props.mapRef, marker);
            });

            setMarkers(markers => [...markers, marker]);
        }
    }

    const clearMarkers = () => {
        for (let i = 0; i < markers.length; i++) {
            markers[i].setMap(null);
        }
    }

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

                        return (
                            <ClusterMarker
                                index={item.points[0].id}
                                lat={item.lat}
                                lng={item.lng}
                                points={item.points}
                            />
                        );

                    })}

                    {(props.viewport === 'clusters') &&
                        getRouteClusters().map((cluster, index) => (
                            <RouteClusterMarker index={index} lat={cluster[0]} lng={cluster[1]} />
                        ))
                    }

                </GoogleMap>
            </div>
            {(props.viewport === 'clusters') && (props.infobar === '') &&
                <ClusterSlider />
            }

            {(props.viewport === 'zoomedIn') && (props.infobar === '') && 
                <div className="reloadContainer">
                    <RouteReloader />
                </div>
            }

        </>
    );
}


const mapStateToProps = ({ rMap: { mapRef, mapsRef, defaultColleges, selectedCluster, viewport, showRoute },
    rUser: { user, route, routesUpdated }, rRoute: { tooltip, infobar } }) =>
    ({ mapRef, mapsRef, defaultColleges, selectedCluster, viewport, showRoute, user, route, routesUpdated, tooltip, infobar });

export default connect(mapStateToProps)(Map);