import React, { useState, useEffect } from "react";
import GoogleMap from 'google-map-react';
import './index.css'
import mapStyles from './mapStyles'
import { connect, useDispatch } from 'react-redux';
import CollegeMarker from './Marker/collegeMarker.js'
import RouteClusterMarker from './routeClusterMarker.js'
import ClusterMarker from './Marker/clusterMarker.js';
import supercluster from 'points-cluster';
import { findCenter } from './geocoordinateCalculations';
import { renderDirections, clearDirections } from './directionsRenderer'


export const susolvkaCoords = { lat: 60.814305, lng: 47.051773 };

export const markersData = [...Array(100)]
  .fill(0) // fill(0) for loose mode
  .map((__, index) => ({
    id: index,
    lat:
      susolvkaCoords.lat +
      0.01 *
        index *
        Math.sin(30 * Math.PI * index / 180) *
        Math.cos(50 * Math.PI * index / 180) +
      Math.sin(5 * index / 180),
    lng:
      susolvkaCoords.lng +
      0.01 *
        index *
        Math.cos(70 + 23 * Math.PI * index / 180) *
        Math.cos(50 * Math.PI * index / 180) +
      Math.sin(5 * index / 180),
  }));


const MAP = {
    defaultZoom: 5,
    defaultCenter: { lat: 37.5, lng: -104 }, // center of US (slightly adjusted)
    options: {
        styles: mapStyles.basic,
        disableDefaultUI: true,
        // minZoom: 4.3
    },
};

function Map(props) {
    const [clustersDisplayed, setClustersDisplayed] = useState([]);
    const [mapOptions, setMapOptions] = useState(
        {
            center: MAP.defaultCenter,
            zoom: MAP.defaultZoom,
        });

    const dispatch = useDispatch();

    useEffect(() => {
        dispatch({
            type: 'REQUEST_DEFAULT_COLLEGES',
        })
    }, [])

    const defaultMarkers = () => Object.values(props.defaultColleges).map((college, index) => (
        <CollegeMarker lat={college.lat} lng={college.lon}
            college={college} index={index} />
    ))

    const getColleges = () => Object.values(props.defaultColleges).map((c, index) => ({
        id: index, 
        lat: c.lat,
        lng: c.lon
    }));

    const getCollegeClusters = () => {
        // console.log(getColleges())
        // const clusters = supercluster(markersData, {
        //     minZoom: 0,
        //     maxZoom: 16,
        //     radius: 60,
        // });
        const clusters = supercluster(getColleges(), {
            minZoom: 0,
            maxZoom: 16,
            radius: 60,
        });
        console.log(clusters(mapOptions))
        return clusters(mapOptions); 
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

    const handleMapChange = ({ center, zoom, bounds }) => {
        setMapOptions({
                    center,
                    zoom,
                    bounds,
                },
        );
    };

    useEffect(() => {
        createClusters(); 
    }, [mapOptions])

    function getRouteClusters() {
        return Object.values(props.user.route).map(cluster =>
            findCenter(Object.values(cluster).map(college => [college.lat, college.lon]))
        )
    }

    function getCurrentRouteCluster() {
        return Object.values(Object.values(props.user.route)[props.selectedCluster]);
    }

    const handleApiLoaded = (map, maps) => {
        // Store a reference to the google map instance in store
        dispatch({
            payload: { map, maps },
            type: 'ON_LOADED',
        })
    }

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
        <div style={{ position: 'absolute' }}>
            <GoogleMap
                style={{ height: '100vh', width: '100vw', zIndex: -1 }}
                bootstrapURLKeys={{ key: 'AIzaSyBIJk5AqilYH8PHt2TP4f5d7QY-UxtJf58' }} //process.env.REACT_APP_GOOGLE_KEY
                defaultCenter={MAP.defaultCenter}
                defaultZoom={MAP.defaultZoom}
                options={MAP.options}
                onChange={handleMapChange}
                distanceToMouse={distanceToMouse}
                yesIWantToUseGoogleMapApiInternals
                onGoogleApiLoaded={({ map, maps }) => { handleApiLoaded(map, maps) }}
            >

                {/* {(props.viewport === 'default') && defaultMarkers()}  */}

                {(props.viewport === 'default') && clustersDisplayed.map(item => {
                    // console.log(item)
                    console.log(item);
                    if (item.numPoints === 1) {
                      return (
                          <div lat={item.points[0].lat}
                             lng={item.points[0].lng}>aaa</div>
                        // <CollegeMarker
                        //   key={item.id}
                        //   lat={item.points[0].lat}
                        //   lng={item.points[0].lng}
                        // />
                      );
                    }

                    return (
                        <ClusterMarker
                          index={item.id}
                          lat={item.lat}
                          lng={item.lng}
                          points={item.points}
                        />
                      );
                
                })}



                    {
                        (props.viewport === 'clusters') &&
                            getRouteClusters().map((cluster, index) => (
                                <RouteClusterMarker index={index} lat={cluster[0]} lng={cluster[1]} />
                            ))
                    }

                    {
                        (props.viewport === 'zoomedIn') &&
                            getCurrentRouteCluster().map((college, index) => (
                                <div lat={college.lat} lng={college.lon}> {college.name}</div>
                            ))
                    }

                
            </GoogleMap>
        </div>

    );
}


const mapStateToProps = ({ rMap: { mapRef, defaultColleges, selectedCluster, viewport, markerClicked }, rUser: { user } }) =>
    ({ mapRef, defaultColleges, selectedCluster, viewport, user, markerClicked });

export default connect(mapStateToProps)(Map);