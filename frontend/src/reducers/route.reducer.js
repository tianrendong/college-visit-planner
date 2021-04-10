const initialState = {
    sidebarOpen: true,
    sidebar: 'login',
    infobar: '',
    popDialog: '',
    currentCollege: {},
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
                infobar: '',
            };
        case "UPDATE_CLUSTERS":
            return {
                ...state,
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
        case "UPDATE_CURRENT_COLLEGE":
            return {
                ...state,
                sidebarOpen: true,
                sidebar: 'collegeInfo',
                infobar: '',
                currentCollege: action.payload.collegeInfo,
            }
        default:
            return state
    }
}

export default routeReducer;