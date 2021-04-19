const initialState = {
    sidebar: 'login',
    infobar: '',
    popDialog: '',
    error: '',
    currentCollege: null,
    tooltip: [],
    successMessage: ''
}

const routeReducer = (state = initialState, action) => {
    switch (action.type) {
        case "LOGIN_SUCCESS":
            return {
                ...state,
                sidebar: 'userhome',
                infobar: '',
                error: '',
            };
        case "LOGIN_FAILURE":
            return {
                ...state,
                error: action.payload.error,
                successMessage: '',
            };
        case "ERROR":
            return {
                ...state,
                error: action.payload.error,
                successMessage: '',
            };
        case "SUCCESS_MESSAGE":
            return {
                ...state,
                successMessage: action.payload.msg,
                error: '',
            };
        case "CLEAR_ERROR":
            return {
                ...state,
                error: '',
                successMessage: '',
            };
        case "SIGNUP_SUCCESS":
            return {
                ...state,
                sidebar: 'login',
                error: '',
                successMessage: 'Signed Up!'
            };
        case "SIGNUP_FAILURE":
            return {
                ...state,
                error: action.payload.error,
                successMessage: ''
            };
        case "NAVIGATE_SIDEBAR":
            return {
                ...state,
                sidebar: action.payload.sidebar,
                error: '',
                successMessage: '',
            };
        case "NAVIGATE_INFOBAR":
            return {
                ...state,
                infobar: action.payload.infobar,
                error: '',
                successMessage: '',
            };
        case "NAVIGATE_POPDIALOG":
            return {
                ...state,
                popDialog: action.payload.popDialog,
                error: '',
                successMessage: '',
            };
        case "UPDATE_ROUTE":
            return {
                ...state,
                infobar: '',
                error: '',
                successMessage: '',
            };
        case "UPDATE_CLUSTERS":
            return {
                ...state,
                infobar: '',
                error: '',
                successMessage: '',
            };
        case "LOGOUT":
            return {
                ...state,
                sidebar: 'login',
                infobar: '',
                error: '',
                successMessage: '',
            };
        case "NAVIGATE_BACK":
            return {
                ...state,
                sidebar: (action.payload.loggedIn === true) ? 'userhome' : 'login',
                infobar: '',
                currentCollege: null,
                error: '',
                successMessage: '',
            };
        case "UPDATE_CURRENT_COLLEGE":
            return {
                ...state,
                sidebar: 'collegeInfo',
                infobar: '',
                currentCollege: action.payload.collegeInfo,
                error: '',
                successMessage: '',
            }
        case "ADD_TOOLTIP":
            return {
                ...state,
                tooltip: [...state.tooltip, action.payload.tooltip]
            }
        default:
            return state
    }
}

export default routeReducer;