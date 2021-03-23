import React, {useState, useEffect} from "react";
import {withScriptjs, withGoogleMap, GoogleMap, Marker, InfoWindow} from "react-google-maps"
import './index.css'
import mapStyles from './mapStyles'
import {getDefaultColleges} from "../../api";

function Map() {
    const [defaultColleges, setDefaultColleges] = useState([]);
    const [ selected, setSelected ] = useState({});

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
        requestDefaultColleges();}, []);

    const defaultMarkers = defaultColleges.map(college => {
        return {
            name: college.name,
            location: {lat: college.lat, lng: college.lon}
        }
    })
    console.log(defaultMarkers)

    const MyMapComponent = withScriptjs(withGoogleMap((props) =>
        <GoogleMap
            defaultZoom={5}
            defaultCenter={{lat: 37.0902, lng: -95.7129}}
            options={{ styles: mapStyles.basic, mapTypeControl: false }}
        >
            {defaultMarkers.map(item => {
                console.log(item.location);
                    return (
                        <Marker key={item.name} position={item.location} onClick={() => onSelect(item)}/>
                    )
                })
            }
            {/*{*/}
            {/*    selected.location &&*/}
            {/*    (*/}
            {/*        <InfoWindow*/}
            {/*            position={selected.location}*/}
            {/*            clickable={true}*/}
            {/*            onCloseClick={() => setSelected({})}*/}
            {/*        >*/}
            {/*            <p>{selected.name}</p>*/}
            {/*        </InfoWindow>*/}
            {/*    )*/}
            {/*}*/}

        </GoogleMap>
    ))

    return (<MyMapComponent
        isMarkerShown
        googleMapURL={`https://maps.googleapis.com/maps/api/js?v=3.exp&libraries=geometry,drawing,places&key=
        ${process.env.REACT_APP_GOOGLE_KEY}`}
        loadingElement={<div style={{height: `100%`}}/>}
        containerElement={<div style={{height: '100vh', zIndex: -1}}/>}
        mapElement={<div style={{height: `100%`}}/>}
    />)
}

export default Map;
