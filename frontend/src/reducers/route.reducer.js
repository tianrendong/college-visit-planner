const initialState = {
    sidebarOpen: false,
    sidebar: 'login',
    infobar: '',
    popDialog: '',
}

const routeReducer = (state = initialState, action) => {
    switch (action.type) {
        case "LOGIN_SUCCESS":
            return {
                ...state,
                sidebarOpen: true,
                sidebar: 'userhome',
                infobar: '',
            };
        case "SIGNUP_SUCCESS":
            return {
                ...state,
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
        case "NAVIGATE_POPDIALOG":
            return {
                ...state,
                popDialog: action.payload.popDialog
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
        case "LOGOUT":
            return {
                ...state,
                sidebarOpen: true,
                sidebar: 'login',
                infobar: '',
            };
        case "NAVIGATE_BACK":
            return {
                ...state,
                sidebarOpen: true,
                sidebar: (action.payload.loggedIn === true) ? 'userhome' : 'login',
                infobar: '',
            };
        default:
            return state
    }
}

export default routeReducer;