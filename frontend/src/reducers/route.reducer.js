const initialState = {
    sidebar: 'login',
    // infobar: '',
    infobar: 'settings',
    popDialog: '',
    currentCollege: null,
}

const routeReducer = (state = initialState, action) => {
    switch (action.type) {
        case "LOGIN_SUCCESS":
            return {
                ...state,
                sidebar: 'userhome',
                infobar: '',
            };
        case "SIGNUP_SUCCESS":
            return {
                ...state,
                sidebar: 'login',
            };
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
        case "NAVIGATE_POPDIALOG":
            return {
                ...state,
                popDialog: action.payload.popDialog
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
                sidebar: 'login',
                infobar: '',
            };
        case "NAVIGATE_BACK":
            console.log(action)
            return {
                ...state,
                sidebar: (action.payload.loggedIn === true) ? 'userhome' : 'login',
                infobar: '',
            };
        case "UPDATE_CURRENT_COLLEGE":
            return {
                ...state,
                sidebar: 'collegeInfo',
                infobar: '',
                currentCollege: action.payload.collegeInfo,
            }
        default:
            return state
    }
}

export default routeReducer;