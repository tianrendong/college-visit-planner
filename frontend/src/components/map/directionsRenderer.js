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
        } else {
            console.error(`error fetching directions ${response}`);
        }
    });
}

const renderOptionsMain = {
    strokeColor: '#416ea3',
    strokeOpacity: '0.7',
    strokeWeight: '7',
};

// const renderOptionsBorder = {
//     strokeColor: '#DAA697',
//     strokeWeight: '80',
//     strokeOpacity: '0.3'
// };

let directionsInnerDisplay;
// let directionsBorderDisplay;

function setDirectionsRenderer() {
    directionsInnerDisplay = new window.google.maps.DirectionsRenderer({
        suppressInfoWindows: true,
        suppressMarkers: true,
        polylineOptions: renderOptionsMain,
    });
    // directionsBorderDisplay = new window.google.maps.DirectionsRenderer({
    //     suppressInfoWindows: true,
    //     suppressMarkers: true,
    //     polylineOptions: renderOptionsBorder,
    // });
}

  
export function renderDirections(map, start, end, waypts) {
    const directionsService = new window.google.maps.DirectionsService();
    setDirectionsRenderer();
    directionsInnerDisplay.setMap(map);
    // directionsBorderDisplay.setMap(map);
    calculateAndDisplayRoute(directionsService, directionsInnerDisplay, start, end, waypts, map);
    // calculateAndDisplayRoute(directionsService, directionsBorderDisplay, start, end, waypts, map);
}

export function clearDirections(){
    if (directionsInnerDisplay != null) {
        directionsInnerDisplay.setMap(null);
        directionsInnerDisplay = null;
    }
    // if (directionsBorderDisplay != null) {
    //     directionsBorderDisplay.setMap(null);
    //     directionsBorderDisplay = null;
    // }
}
