import React, { useState, useEffect } from "react";
import GoogleMapReact from 'google-map-react';
import './index.css'
import mapStyles from './mapStyles'
import { getDefaultColleges } from "../../api";

function Map() {
    const [defaultColleges, setDefaultColleges] = useState([]);
    const [selected, setSelected] = useState({});

    const onSelect = item => {
        setSelected(item);
    }

    const requestDefaultColleges = () => {
        getDefaultColleges()
            .then(resp => {
                return resp.json()
            })
            .then(colleges => {
                console.log(colleges)
                setDefaultColleges(colleges)
            })
    }

    useEffect(() => {
        requestDefaultColleges();
    }, []);

    const defaultMarkers = defaultColleges.map(college => {
        return {
            name: college.name,
            location: { lat: college.lat, lng: college.lon }
        }
    })
    console.log(defaultMarkers)

    // const MyMapComponent = withScriptjs(withGoogleMap((props) =>

/* {defaultMarkers.map(item => {
    console.log(props.google)
    console.log(item);
    return (
        
        <MarkerWithLabel
            key={item.name}
            position={item.location}
            onClick={() => onSelect(item)}
            labelAnchor={props.google.maps.Point(0, 0)}
            labelStyle={{ backgroundColor: "yellow", fontSize: "32px", padding: "16px" }}
        >
            <div>Hello There!</div>
        </MarkerWithLabel>
    )
})
}
{
    selected.location &&
    (
        <InfoWindow
            position={selected.location}
            clickable={true}
            onCloseClick={() => setSelected({})}
        >
            <p>{selected.name}</p>
        </InfoWindow>
    )
} */
    // ))

    // return (<MyMapComponent
    //     isMarkerShown
    //     googleMapURL={`https://maps.googleapis.com/maps/api/js?v=3.exp&libraries=geometry,drawing,places&key=
    //     ${process.env.REACT_APP_GOOGLE_KEY}`}
    //     loadingElement={<div style={{ height: `100%` }} />}
    //     containerElement={<div style={{ height: '100vh', zIndex: -1 }} />}
    //     mapElement={<div style={{ height: `100%` }} />}
    // />)
    return (<GoogleMapReact
        bootstrapURLKeys={{ key: 'AIzaSyBIJk5AqilYH8PHt2TP4f5d7QY-UxtJf58' }}
        defaultZoom={5}
                defaultCenter={{ lat: 37.0902, lng: -95.7129 }}
                /* options={{ styles: mapStyles.basic, mapTypeControl: false }} */
      >
        <button
          lat={59.955413}
          lng={30.337844}
        >
            sssssssss
            </button>
      </GoogleMapReact>)
}

export default Map;
