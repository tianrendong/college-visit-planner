const initialState = {
    sidebar: 'login',
    mainview: 'initialMap',
}

const routeReducer = (state = initialState, action) => {
    switch (action.type) {
        case "NAVIGATE_SIDEBAR":
            return {
                ...state,
                sidebar: action.payload.newSidebarView
            }
        case "NAVIGATE_MAINVIEW":
            return {
                ...state,
                mainview: action.payload.newMainview
            };
        default:
            return state
    }
}

export default routeReducer;