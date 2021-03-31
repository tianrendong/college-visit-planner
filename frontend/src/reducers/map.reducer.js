const initialState = {
    mapRef: null,
    defaultColleges: '',
    viewport: 'default',
    selectedCluster: '',
    center: { lat: 37.5, lng: -97.4 },
    zoom: 5.3,

}

const mapReducer = (state = initialState, action) => {
    switch (action.type) {
        case "ON_LOADED":
            return {
                ...state,
                mapRef: action.payload.mapRef
            };
        case "RENDER_DEFAULT_COLLEGES":
            return {
                ...state,
                defaultColleges: action.payload.defaultColleges
            };
        // case "RENDER_ROUTE":
        //     console.log(action);
        //     return {
        //         ...state,
        //         center: { lat: 37.5, lng: -97.4 },
        //         zoom: 5.3
        //     };
        case "UPDATE_ROUTE":
            return {
                selectedCluster: '',
                viewport: 'clusters',
                center: { lat: 37.5, lng: -97.4 },
                zoom: 5.3
            };
        case "EXPAND_CLUSTER":
            return {
                selectedCluster: action.payload.clusterIndex,
                viewport: 'zoomedIn',
                center: action.payload.center,
                zoom: action.payload.zoom
            };
        default:
            return state
    }
}

export default mapReducer;