import React from "react";
import {withScriptjs, withGoogleMap, GoogleMap, Marker} from "react-google-maps"
import './index.css'
import mapStyles from './mapStyles'

const MyMapComponent = withScriptjs(withGoogleMap((props) =>
    <GoogleMap
        defaultZoom={8}
        defaultCenter={{lat: -34.397, lng: 150.644}}
        options={{ styles: mapStyles.basic, mapTypeControl: false }}
    >
        {props.isMarkerShown && <Marker position={{lat: -34.397, lng: 150.644}}/>}
    </GoogleMap>
))

export default function Map() {
    return (<MyMapComponent
        isMarkerShown
        googleMapURL={`https://maps.googleapis.com/maps/api/js?v=3.exp&libraries=geometry,drawing,places&key=
        ${process.env.REACT_APP_GOOGLE_KEY}`}
        loadingElement={<div style={{height: `100%`}}/>}
        containerElement={
            <div
                style={{
                    height: '100vh',
                    zIndex: -1
                }}
            />
        }
        mapElement={<div style={{height: `100%`}}/>}
    />)
}
