import React, { useState, useEffect } from "react";
import GoogleMap from 'google-map-react';
import './index.css'
import mapStyles from './mapStyles'
import { connect, useDispatch } from 'react-redux';
import { collegeAPI } from '../../api/collegeAPI';
import CollegeMarker from './collegeMarker'
import ClusterMarker from './clusterMarker'
import Infocard from './infocard'
import { findCenter } from './geocoordinateCalculations';
import { renderDirections, clearDirections } from './directionsRenderer'

const DEFAULT_CENTER = { lat: 37.5, lng: -104 } // center of US (slightly adjusted)
const DEFAULT_ZOOM = 5
const MIN_ZOOM = 4.3

function Map(props) {
    const dispatch = useDispatch();
    const [clicked, setClicked] = useState(false);

    useEffect(() => {
        dispatch({
            type: 'REQUEST_DEFAULT_COLLEGES',
        })
    }, [])

    const defaultMarkers = () => Object.values(props.defaultColleges).map((college, index) => (
        <CollegeMarker lat={college.lat} lng={college.lon} 
        college={college} index={index}/>
    ))

    function getClusters() {
        return Object.values(props.user.route).map(cluster => 
            findCenter(Object.values(cluster).map(college => [college.lat, college.lon]))
        )
    }

    function getCurrentCluster() {
        return Object.values(Object.values(props.user.route)[props.selectedCluster]);
    }

    const handleApiLoaded = (map, maps) => {
        // Store a reference to the google map instance in store
        dispatch({
            payload: {map, maps},
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
      
    const getMarker = (marker) => {
        if (marker.type === 'defaultMarker') {
            return <Infocard lat={marker.content.lat} lng={marker.content.lon}/>
        }
    }
    
    useEffect(() => {
        if (props.viewport === 'zoomedIn') {
            const currentCluster = getCurrentCluster();
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
                payload: {sidebar: 'routeInfo'},
                type: 'NAVIGATE_SIDEBAR'
            })
        } else {
            if (props.mapRef !== null) {
                props.mapRef.setCenter(DEFAULT_CENTER)
                props.mapRef.setZoom(DEFAULT_ZOOM)
                clearDirections();
            }
        }
    }, [props.viewport])
    
    return (
        <div style={{ position: 'absolute' }}>
            <GoogleMap
                style={{ height: '100vh', width: '100vw', zIndex: -1 }}
                bootstrapURLKeys={{ key: 'AIzaSyBIJk5AqilYH8PHt2TP4f5d7QY-UxtJf58' }} //process.env.REACT_APP_GOOGLE_KEY
                defaultCenter={DEFAULT_CENTER}
                defaultZoom={DEFAULT_ZOOM}
                options={{ styles: mapStyles.basic, disableDefaultUI: true, minZoom: MIN_ZOOM }}
                distanceToMouse={distanceToMouse}
                yesIWantToUseGoogleMapApiInternals
                onGoogleApiLoaded={({ map, maps }) => { handleApiLoaded(map, maps)}}
                >
                
                 {(props.viewport === 'default') && defaultMarkers()} 

                 {(props.viewport === 'clusters') && 
                 getClusters().map((cluster, index) => ( 
                    <ClusterMarker index={index} lat={cluster[0]} lng={cluster[1]}/>
                    ))}
                
                {(props.viewport === 'zoomedIn') &&
                getCurrentCluster().map((college, index) => ( 
                    <div lat={college.lat} lng={college.lon}> {college.name}</div>
                    ))}
                

                {(props.markerClicked !== {}) && getMarker(props.markerClicked)}
            </GoogleMap>
        </div>

    );
}


const mapStateToProps = ({ rMap: { mapRef, defaultColleges, selectedCluster, viewport, markerClicked}, rUser: { user } }) => 
({ mapRef, defaultColleges, selectedCluster, viewport, user, markerClicked });

export default connect(mapStateToProps)(Map);