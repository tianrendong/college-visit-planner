const initialState = {
    sidebar: 'login',
    infobar: '',
}

const routeReducer = (state = initialState, action) => {
    switch (action.type) {
        case "NAVIGATE_SIDEBAR":
            return {
                ...state,
                sidebar: action.payload.sidebar
            };
        case "NAVIGATE_INFOBAR":
            return {
                ...state,
                infobar: action.payload.infobar
            };  
        default:
            return state
    }
}

export default routeReducer;