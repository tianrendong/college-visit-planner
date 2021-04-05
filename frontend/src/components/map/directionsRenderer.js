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

const renderOptionsMain = {
    strokeColor: '#265A8A',
    strokeWeight: '5',
};

const renderOptionsBorder = {
    strokeColor: '#DAA697',
    strokeWeight: '80',
    strokeOpacity: '0.3'
};

let directionsInnerDisplay;
let directionsBorderDisplay;

function setDirectionsRenderer() {
    directionsInnerDisplay = new window.google.maps.DirectionsRenderer({
        suppressInfoWindows: true,
        polylineOptions: renderOptionsMain,
    });
    directionsBorderDisplay = new window.google.maps.DirectionsRenderer({
        suppressInfoWindows: true,
        suppressMarkers: true,
        polylineOptions: renderOptionsBorder,
    });
}

export function renderDirections(map, start, end, waypts) {
    const directionsService = new window.google.maps.DirectionsService();
    setDirectionsRenderer();
    directionsInnerDisplay.setMap(map);
    directionsBorderDisplay.setMap(map);
    calculateAndDisplayRoute(directionsService, directionsInnerDisplay, start, end, waypts);
    calculateAndDisplayRoute(directionsService, directionsBorderDisplay, start, end, waypts);
}

export function clearDirections(){
    if (directionsInnerDisplay != null) {
        directionsInnerDisplay.setMap(null);
        directionsInnerDisplay = null;
    }
    if (directionsBorderDisplay != null) {
        directionsBorderDisplay.setMap(null);
        directionsBorderDisplay = null;
    }
}