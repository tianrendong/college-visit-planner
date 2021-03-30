const initialState = {
    markers: '',
    clusters: '',
    center: { lat: 37.5, lng: -97.4 },
    zoom: 5.3
}

const mapReducer = (state = initialState, action) => {
    switch (action.type) {
        case "RENDER_DEFAULT_COLLEGES":
            return {
                ...state,
                markers: action.payload.defaultColleges
            };
        // case "RENDER_ROUTE":
        //     console.log(action);
        //     return {
        //         ...state,
        //         center: { lat: 37.5, lng: -97.4 },
        //         zoom: 5.3
        //     };
        default:
            return state
    }
}

export default mapReducer;