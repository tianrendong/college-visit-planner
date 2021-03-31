import React, { useState, useEffect } from "react";
import GoogleMap from 'google-map-react';
import './index.css'
import mapStyles from './mapStyles'
import { connect, useDispatch } from 'react-redux';
import { collegeAPI } from '../../api/collegeAPI';
import CollegeMarker from './collegeMarker'
import Infocard from './infocard'
import { findCenter } from './geocoordinateCalculations';

function Map(props) {
    const dispatch = useDispatch();
    // const bounds = new window.google.maps.LatLngBounds();

    // const USA_BOUNDS = {
    //     north: 49.5904,
    //     south: 24.9493,
    //     west: -125.0011,
    //     east: -66.9326,
    //   };

    

    // const renderMarkers = () => {
    //     Object.values(props.markers).map(college => {
    //         const position = new google.maps.LatLng(college.lat, college.lon);
    //         bounds.extend(position)
    //     return <CollegeMarker lat={college.lat} lng={college.lon} college={college} />
    //     })
    //     }

    

    // edit: dispatch this action whenever logged out?
    useEffect(() => {
        dispatch({
            type: 'REQUEST_DEFAULT_COLLEGES',
        })
    }, [])

    const defaultMarkers = () => Object.values(props.defaultColleges).map(college => (
        <CollegeMarker lat={college.lat} lng={college.lon} college={college}/>
    ))

    // PROBLEM: gets called before user has value
    function clusterCenters() {
        return Object.values(props.user.route).map(cluster => 
            findCenter(Object.values(cluster).map(college => [college.lat, college.lon]))
        )
    }

    const zoom = (map, maps) => {
        console.log(maps);

        // const currentCluster = Object.values(Object.values(props.user.route)[props.selectedCluster])

        

    }

    // console.log(props.mapRef)
    const handleApiLoaded = (map, maps) => {
        // Store a reference to the google map instance in store
        dispatch({
            payload: {mapRef: maps},
            type: 'ON_LOADED',
        })
    }

    useEffect(() => {
        if (props.viewport === 'zoomedIn') {
            const bounds = new window.google.maps.LatLngBounds();
            console.log("a");
        }
    }, [props.viewport])

    const getMapBounds = (map, maps) => {
        console.log("a");
        const currentCluster = Object.values(Object.values(props.user.route)[props.selectedCluster])

        const bounds = new maps.LatLngBounds();
      
        currentCluster.forEach((college) => {
          bounds.extend(new maps.LatLng(college.lat, college.lon));
        });
        return bounds;
      };
    
    const handleClickCluster = (e) => {
        console.log(e.target.dataset.index)
        dispatch({
            payload: {clusterIndex: e.target.dataset.index},
            type: 'EXPAND_CLUSTER',
        })
        // calculate zoom, get center, dispatch as payload
        // dispatch({
        //     payload: {},
        //     type: 'EXPAND_CLUSTER',
        // })
    }



    return (
        <div style={{ position: 'absolute' }}>
            <GoogleMap
                // ref={(map) => { 
                //     console.log(map);
                //     // if (props.viewport === 'zoomedIn') {
                //     //     zoom(map);
                //     // }
                // }}
                style={{ height: '100vh', width: '100vw', zIndex: -1 }}
                bootstrapURLKeys={{ key: 'AIzaSyBIJk5AqilYH8PHt2TP4f5d7QY-UxtJf58' }} //process.env.REACT_APP_GOOGLE_KEY
                defaultCenter={{ lat: 37.5, lng: -97.4 }}
                defaultZoom={5.3}
                // restriction={{
                //     latLngBounds: USA_BOUNDS,
                //     strictBounds: true,
                // }}  // doesnt work
                options={{ styles: mapStyles.basic, mapTypeControl: false }}
                yesIWantToUseGoogleMapApiInternals
                onGoogleApiLoaded={({ map, maps }) => { handleApiLoaded(map, maps)}}
                >
                
                 {(props.viewport === 'default') && defaultMarkers()} 

                 {(props.viewport === 'clusters')
                 && clusterCenters().map((cluster, index) => ( 
                    <div data-index={index} lat={cluster[0]} lng={cluster[1]} onClick={e => handleClickCluster(e)}> a</div>
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


const mapStateToProps = ({ rMap: { mapRef, defaultColleges, selectedCluster, viewport, center, zoom }, rUser: { user } }) => 
({ mapRef, defaultColleges, selectedCluster, viewport, center, zoom, user });

export default connect(mapStateToProps)(Map);