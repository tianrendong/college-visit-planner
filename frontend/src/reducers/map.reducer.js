const initialState = {
    mapRef: null,
    mapsRef: null,
    defaultColleges: '',
    viewport: 'default',
    selectedCluster: '',
}

const mapReducer = (state = initialState, action) => {
    switch (action.type) {
        case "ON_LOADED":
            return {
                ...state,
                mapRef: action.payload.map,
                mapsRef: action.payload.maps
            };
        case "RENDER_DEFAULT_COLLEGES":
            return {
                ...state,
                defaultColleges: action.payload.defaultColleges
            };
        case "UPDATE_ROUTE":
            return {
                ...state,
                selectedCluster: '',
                viewport: 'clusters',
            };
        case "EXPAND_CLUSTER":
            return {
                ...state,
                selectedCluster: action.payload.clusterIndex,
                viewport: 'zoomedIn',
            };
        default:
            return state
    }
}

export default mapReducer;