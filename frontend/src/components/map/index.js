import React, { useState, useEffect } from "react";
import GoogleMap from 'google-map-react';
import './index.css'
import mapStyles from './mapStyles'
import { connect, useDispatch } from 'react-redux';
import { collegeAPI } from '../../api/collegeAPI';
import CollegeMarker from './collegeMarker'
import Infocard from './infocard'
import { findCenter } from './geocoordinateCalculations';
import { renderDirections } from './directionsRenderer'

function Map(props) {
    const dispatch = useDispatch();
    // edit: dispatch this action whenever logged out?
    useEffect(() => {
        dispatch({
            type: 'REQUEST_DEFAULT_COLLEGES',
        })
    }, [])

    const defaultMarkers = () => Object.values(props.defaultColleges).map(college => (
        <CollegeMarker lat={college.lat} lng={college.lon} college={college}/>
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

    //automatic zoom
    useEffect(() => {
        if (props.viewport === 'zoomedIn') {
            console.log("a");
            const currentCluster = getCurrentCluster();
            // for (let i = 0; i < currentCluster.length - 1; i++) {
            //     renderDirections(props.mapRef, currentCluster[i].lat, currentCluster[i].lon, 
            //         currentCluster[i+1].lat, currentCluster[i+1].lon)
            // }
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



            const bounds = new window.google.maps.LatLngBounds();
            getCurrentCluster().forEach((college) => {
                bounds.extend(new window.google.maps.LatLng(college.lat, college.lon));
              });
            props.mapRef.fitBounds(bounds);
        }
    }, [props.viewport])
    
    const handleClickCluster = (e) => {
        dispatch({
            payload: {clusterIndex: e.target.dataset.index},
            type: 'EXPAND_CLUSTER',
        })
    }

    return (
        <div style={{ position: 'absolute' }}>
            <GoogleMap
                style={{ height: '100vh', width: '100vw', zIndex: -1 }}
                bootstrapURLKeys={{ key: 'AIzaSyBIJk5AqilYH8PHt2TP4f5d7QY-UxtJf58' }} //process.env.REACT_APP_GOOGLE_KEY
                defaultCenter={{ lat: 37.5, lng: -97.4 }}
                defaultZoom={5.3}
                options={{ styles: mapStyles.basic, mapTypeControl: false }}
                yesIWantToUseGoogleMapApiInternals
                onGoogleApiLoaded={({ map, maps }) => { handleApiLoaded(map, maps)}}
                >
                
                 {(props.viewport === 'default') && defaultMarkers()} 

                 {(props.viewport === 'clusters') && 
                 getClusters().map((cluster, index) => ( 
                    <div data-index={index} lat={cluster[0]} lng={cluster[1]} onClick={e => handleClickCluster(e)}> a</div>
                    ))}
                
                {(props.viewport === 'zoomedIn') &&
                getCurrentCluster().map((college, index) => ( 
                    <div lat={college.lat} lng={college.lon}> {college.name}</div>
                    ))}
                

                {/* {
                    selected.location &&
                    (
                        <div
                            lat={selected.location.lat}
                            lng={selected.location.lon}
                            clickable={true}
                            onCloseClick={() => setSelected({})}
                        >
                            <p>{selected.name}</p>
                        </div>
                    )
                } */}
            </GoogleMap>
        </div>

    );
}


const mapStateToProps = ({ rMap: { mapRef, defaultColleges, selectedCluster, viewport}, rUser: { user } }) => 
({ mapRef, defaultColleges, selectedCluster, viewport, user });

export default connect(mapStateToProps)(Map);