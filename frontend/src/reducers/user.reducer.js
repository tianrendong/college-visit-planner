const initialState = {
    loggedIn: false,
    loginError: '',
    user: null,
    dataConsent: false,
}

const userReducer = (state = initialState, action) => {
    switch (action.type) {
        case "LOGIN_REQUEST":
            return {
                loggingIn: true,
            };
        case "LOGIN_SUCCESS":
            console.log(action.payload);
            return {
                loggingIn: false,
                loggedIn: true,
                user: action.payload.user
            };
        case "LOGIN_FAILURE":
            console.log(action.payload);
            return {
                loggingIn: false,
                loggedIn: false,
                loginError: action.payload.error,
                user: null
            };
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