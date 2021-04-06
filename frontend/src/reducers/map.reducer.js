const initialState = {
    mapRef: null,
    mapsRef: null,
    defaultColleges: '',
    viewport: 'default',
    selectedCluster: '',
    markerClicked: {},
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
            console.log(action);
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
                    markerClicked: {},
                };
        case "CLICK_MARKER":
            return {
                    ...state,
                    markerClicked: action.payload,
                };
        case "UPDATE_AIRPORTS": 
        console.log(action)
            return {
                ...state,
                markerClicked: {
                    ...state.markerClicked,
                    airport: action.payload.airport,
                }

            }
        default:
            return state
    }
}

export default mapReducer;