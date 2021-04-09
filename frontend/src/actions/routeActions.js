export const routeActions = {
    navigateSidebar,
    navigateInfobar,
    navigatePopDialog,
};

function navigateSidebar(sidebar) {
    return {
        payload: {sidebar},
        type: 'NAVIGATE_SIDEBAR',
    };
}

function navigateInfobar(infobar) {
    return {
        payload: {infobar},
        type: 'NAVIGATE_INFOBAR',
    };
}

function navigatePopDialog(popDialog) {
    return {
        payload: {popDialog: popDialog},
        type: 'NAVIGATE_POPDIALOG',
    };
}
