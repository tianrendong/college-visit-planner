export const routeActions = {
    navigateSidebar,
    navigateInfobar,
    navigatePopDialog,
    addTooltipShowed,
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
        payload: {popDialog},
        type: 'NAVIGATE_POPDIALOG',
    };
}

function addTooltipShowed(tooltip) {
    return {
        payload: {tooltip},
        type: 'ADD_TOOLTIP',
    };
}