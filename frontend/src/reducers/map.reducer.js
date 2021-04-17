const initialState = {
    mapRef: null,
    mapsRef: null,
    defaultColleges: '',
    viewport: 'default',
    selectedCluster: '',
    showRoute: false,
    sliderValue: 350,
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
                selectedCluster: action.payload.clusterIndex,
                viewport: 'zoomedIn',
                showRoute: true,
            };
        case "UPDATE_CLUSTERS":
            return {
                ...state,
                selectedCluster: '',
                viewport: 'clusters',
            };
        case "LOGOUT":
            return {
                ...state,
                selectedCluster: '',
                viewport: 'default',
            };
        case "NAVIGATE_BACK":
            return {
                    ...state,
                    selectedCluster: '',
                    viewport: action.payload.viewport,
                    showRoute: false,
                };
        case "TOGGLE_SHOW_ROUTE":
            return {
                ...state,
                showRoute: !state.showRoute
            }
        case "REQUEST_UPDATE_CLUSTERS":
            console.log(action)
            return {
                ...state,
                sliderValue: action.payload.radius,
            }
        default:
            return state
    }
}

export default mapReducer;