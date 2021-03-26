export const routeActions = {
    navigateSignup,
    navigateLogin,
};

function navigateSignup() {
    return {
        payload: {newSidebarView: 'signup'},
        type: 'NAVIGATE_SIDEBAR',
    };
}

function navigateLogin() {
    return {
        payload: {newSidebarView: 'login'},
        type: 'NAVIGATE_SIDEBAR',
    };
}