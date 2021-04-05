import React, { useState, useEffect } from "react";
import GoogleMap from 'google-map-react';
import { rgbToHex } from "@material-ui/core";

function calculateAndDisplayRoute(directionsService, directionsRenderer, start, end, waypts, map) {
    directionsService.route({
        origin: start,
        destination: end,
        waypoints: waypts,
        optimizeWaypoints: false,
        travelMode: window.google.maps.TravelMode.DRIVING,
    }, (response, status) => {
        if (status === window.google.maps.DirectionsStatus.OK) {
            directionsRenderer.setDirections(response);
            console.log(response)
            console.log(response.routes[0].legs)
            showSteps(response, map);
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
        suppressMarkers: true,
        polylineOptions: renderOptionsMain,
    });
    directionsBorderDisplay = new window.google.maps.DirectionsRenderer({
        suppressInfoWindows: true,
        suppressMarkers: true,
        polylineOptions: renderOptionsBorder,
    });
}

function showSteps(directionResult, map) {
    // For each step, place a marker, and add the text to the marker's
    // info window. Also attach the marker to an array so we
    // can keep track of it and remove it when calculating new
    // routes.

    var myRoute = directionResult.routes[0].legs;
  
    for (var i = 0; i < myRoute.length; i++) {
        var marker = new window.google.maps.Marker({
          position: myRoute[i].start_point,
          map: map
        });
        // markerArray[i] = marker;
    }
  }

  
export function renderDirections(map, start, end, waypts) {
    const directionsService = new window.google.maps.DirectionsService();
    setDirectionsRenderer();
    directionsInnerDisplay.setMap(map);
    directionsBorderDisplay.setMap(map);
    calculateAndDisplayRoute(directionsService, directionsInnerDisplay, start, end, waypts, map);
    calculateAndDisplayRoute(directionsService, directionsBorderDisplay, start, end, waypts, map);
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