const initialState = {
    loggedIn: false,
    user: null,
    dataConsent: false,
}

const userReducer = (state = initialState, action) => {
    switch (action.type) {
        case "LOGIN_REQUEST":
            return {
                loggingIn: true,
                user: action.user
            };
        case "LOGIN_SUCCESS":
            return {
                loggedIn: true,
                user: action.user
            };
        case "LOGIN_FAILURE":
            return {};
        case "LOGOUT":
            return {};
        case "REGISTER_REQUEST":
            return { registering: true };
        case "REGISTER_SUCCESS":
            return {};
        case "REGISTER_FAILURE":
            return {};
        default:
            return state
    }
}

export default userReducer;