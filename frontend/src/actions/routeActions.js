export const routeActions = {
    navigateSignup,
};

function navigateSignup() {
    return {
        payload: {newSidebarView: 'signup'},
        type: 'NAVIGATE_SIDEBAR',
      };
    }