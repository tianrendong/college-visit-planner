import React, { useState, useEffect } from "react";
import GoogleMap from 'google-map-react';
import './index.css'
import mapStyles from './mapStyles'
import { connect, useDispatch } from 'react-redux';
import { collegeAPI } from '../../api/collegeAPI';
import CollegeMarker from './collegeMarker'
import Infocard from './infocard'

function Map(props) {
    const dispatch = useDispatch();

    const markers = Object.values(props.markers).map(college => (
        <CollegeMarker lat={college.lat} lng={college.lon} college={college} />
        // <div
        //     key={college.name}
        //     lat={college.lat}
        //     lng={college.lon}
        // >
        //     {college.name}
        // </div>
    ))

    useEffect(() => {
        dispatch({
            type: 'REQUEST_DEFAULT_COLLEGES',
        })
    }, [])

    return (
        <div style={{ position: 'absolute' }}>
            <GoogleMap
                style={{ height: '100vh', width: '100vw', zIndex: -1 }}
                bootstrapURLKeys={{ key: 'AIzaSyBIJk5AqilYH8PHt2TP4f5d7QY-UxtJf58' }} //process.env.REACT_APP_GOOGLE_KEY
                defaultCenter={{
                    lat: 37.5,
                    lng: -97.4
                }}
                defaultZoom={5.3}
                options={{ styles: mapStyles.basic, mapTypeControl: false }}
            >
                {markers}
                {/* {defaultMarkers}
                {
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


const mapStateToProps = ({ rMap: { markers } }) => ({ markers });

export default connect(mapStateToProps)(Map);