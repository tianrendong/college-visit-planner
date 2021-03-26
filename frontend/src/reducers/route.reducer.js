const initialState = {
    sidebar: 'login',
    mainview: 'initialMap',
}

const routeReducer = (state = initialState, action) => {
    switch (action.type) {
        case "NAVIGATE_SIDEBAR":
            console.log(action)
            return {
                sidebar: action.payload.newSidebarView
            };
        case "NAVIGATE_MAINVIEW":
            return {
                mainview: action.payload.newMainview
            };
        default:
            console.log('routerrrrrrrrr')
            return state
    }
}

export default routeReducer;