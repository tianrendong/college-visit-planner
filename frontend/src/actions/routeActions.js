export const routeActions = {
    navigateSignup,
    navigateLogin,
    navigateUserhome,
    navigateMyColleges,
    navigateSettings,
    navigateCollegeInfo,
    closeInfobar,
};

function navigateSignup() {
    return {
        payload: {sidebar: 'signup'},
        type: 'NAVIGATE_SIDEBAR',
    };
}

function navigateLogin() {
    return {
        payload: {sidebar: 'login'},
        type: 'NAVIGATE_SIDEBAR',
    };
}

function navigateUserhome() {
    return {
        payload: {sidebar: 'userhome'},
        type: 'NAVIGATE_SIDEBAR',
    };
}

function navigateCollegeInfo() {
    return {
        payload: {infobar: 'collegeInfo'},
        type: 'NAVIGATE_SIDEBAR',
    };
}

function navigateMyColleges() {
    return {
        payload: {infobar: 'myColleges'},
        type: 'NAVIGATE_INFOBAR',
    };
}

function navigateSettings() {
    return {
        payload: {infobar: 'settings'},
        type: 'NAVIGATE_INFOBAR',
    };
}

function closeInfobar() {
    return {
        payload: {infobar: ''},
        type: 'NAVIGATE_INFOBAR',
    };
}