const initialState = {
    sidebarOpen: false,
    sidebar: 'login',
    infobar: '',
}

const routeReducer = (state = initialState, action) => {
    switch (action.type) {
        case "LOGIN_SUCCESS":
            return {
                sidebarOpen: true,
                sidebar: 'userhome',
            };
        case "SIGNUP_SUCCESS":
            return {
                sidebarOpen: true,
                sidebar: 'login',
            };
        case "NAVIGATE_SIDEBAR":
            return {
                ...state,
                sidebarOpen: true,
                sidebar: action.payload.sidebar
            };
        case "NAVIGATE_INFOBAR":
            return {
                ...state,
                infobar: action.payload.infobar
            };
        case "TOGGLE_SIDEBAR":
            return {
                ...state,
                sidebarOpen: !state.sidebarOpen,
            };
        case "UPDATE_ROUTE":
            return {
                ...state,
                sidebarOpen: false,
                infobar: '',
            };
        case "EXPAND_CLUSTER":
            return {
                ...state,
                sidebarOpen: false,
                infobar: '',
            };
        default:
            return state
    }
}

export default routeReducer;