import React, { useState, useEffect } from "react";
import GoogleMap from 'google-map-react';
import './index.css'
import mapStyles from './mapStyles'
import { getDefaultColleges } from "../../api";

function Map() {
    const [defaultColleges, setDefaultColleges] = useState([]);
    const [selected, setSelected] = useState({});

    const defaultMarkers = defaultColleges.map((college) => (
        <div
            key={college.name}
            lat={college.lat}
            lng={college.lon}
            
        >
            {college.name}
        </div>
    ))

    // const requestDefaultColleges = () => {
    //     getDefaultColleges()
    //         .then(resp => {
    //             return resp.json()
    //         })
    //         .then(colleges => {
    //             console.log(colleges)
    //             setDefaultColleges(colleges)
    //         })
    // }

    // useEffect(() => {
    //     requestDefaultColleges();}, []);

    return (
        <GoogleMap
            style={{ height: '100vh', width: '100vw' }}
            bootstrapURLKeys={{ key: 'AIzaSyBIJk5AqilYH8PHt2TP4f5d7QY-UxtJf58' }} //process.env.REACT_APP_GOOGLE_KEY
            defaultCenter={{
                lat: 37.5,
                lng: -97.4
            }}
            defaultZoom={5.3}
            options={{ styles: mapStyles.basic, mapTypeControl: false }}
        >
            {defaultMarkers}
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
            }
        </GoogleMap>
    );
}


export default Map;