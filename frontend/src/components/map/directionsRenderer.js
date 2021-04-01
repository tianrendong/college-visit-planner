import React, { useState, useEffect } from "react";
import GoogleMap from 'google-map-react';
import { rgbToHex } from "@material-ui/core";

function calculateAndDisplayRoute(directionsService, directionsRenderer, start, end, waypts) {
    directionsService.route({
        origin: start,
        destination: end,
        waypoints: waypts,
        optimizeWaypoints: false,
        travelMode: window.google.maps.TravelMode.DRIVING,
    }, (response, status) => {
        if (status === window.google.maps.DirectionsStatus.OK) {
            directionsRenderer.setDirections(response);
        } else {
            console.error(`error fetching directions ${response}`);
        }
    });
}

// export function renderDirections(map, startLat, startLon, endLat, endLon) {
//     const directionsRenderer = new window.google.maps.DirectionsRenderer();
//     const directionsService = new window.google.maps.DirectionsService();
//     const start = new window.google.maps.LatLng(startLat, startLon);
//     const end = new window.google.maps.LatLng(endLat, endLon);
//     directionsRenderer.setMap(map);
//     calculateAndDisplayRoute(directionsService, directionsRenderer, start, end);
//   }
const renderOptionsMain = {
    strokeColor: '#265A8A',
    strokeWeight: '5',
};

const renderOptionsBorder = {
    strokeColor: '#DAA697',
    strokeWeight: '80',
    strokeOpacity: '0.3'
};

export function renderDirections(map, start, end, waypts) {
    const directionsRenderer = new window.google.maps.DirectionsRenderer({
        suppressInfoWindows: true,
        polylineOptions: renderOptionsMain,
    });
    const directionsBorderRenderer = new window.google.maps.DirectionsRenderer({
        suppressInfoWindows: true,
        suppressMarkers: true,
        polylineOptions: renderOptionsBorder,
    });
    const directionsService = new window.google.maps.DirectionsService();
    directionsRenderer.setMap(map);
    directionsBorderRenderer.setMap(map);
    calculateAndDisplayRoute(directionsService, directionsRenderer, start, end, waypts);
    calculateAndDisplayRoute(directionsService, directionsBorderRenderer, start, end, waypts);
}

    // renderDirections(41.8507300, -87.6512600, 41.8525800, -87.6514100)