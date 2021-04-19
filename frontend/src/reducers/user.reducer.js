const initialState = {
    loggedIn: false,
    updatingRoute: false,
    signingUp: false,
    user: null,
    clusterUpdated: false,
    routesUpdated: [],
    route: []
}

const userReducer = (state = initialState, action) => {
    switch (action.type) {
        case "LOGIN_SUCCESS":
            return {
                loggingIn: false,
                loggedIn: true,
                user: action.payload.user,
            };
        case "LOGIN_FAILURE":
            return {
                loggingIn: false,
                loggedIn: false,
                user: null
            };
        case "LOGOUT":
            return {
                loggedIn: false,
                user: null,
            };
        case "SIGNUP_REQUEST":
            return {
                signingUp: true,
            };
        case "SIGNUP_SUCCESS":
            return {
                signingUp: false,
            };
        case "SIGNUP_FAILURE":
            return {
                signingUp: false,
            };
        case "UPDATE_ROUTE":
            const updatedClusterIndex = action.payload.clusterIndex
            const clusterUpdated = state.routesUpdated.includes(updatedClusterIndex)
            return {
                ...state,
                // if cluster is not bookmarked : update the specific cluster based on the index passed in
                route: clusterUpdated ? state.route : ((state.route.map((cluster, i) => i === updatedClusterIndex ? action.payload.route : cluster))),
                updatingRoute: false,
            };

        case "TOGGLE_BOOKMARK_ROUTE":
            const clusterIndex = action.payload.clusterIndex
            const clusterBookmarked = state.routesUpdated.includes(clusterIndex)
            const filteredCluster = state.routesUpdated.filter(r => r !== clusterIndex)
            return {
                ...state,
                // add or delete cluster index to/from routesUpdated
                routesUpdated: clusterBookmarked ? filteredCluster : [...state.routesUpdated, clusterIndex],
            }
        case "ADD_COLLEGE":
            if (state.user.hasOwnProperty("colleges")) {
                return {
                    ...state,
                    clusterUpdated: false,
                    routesUpdated: [],
                    user: {
                        ...state.user,
                        colleges: [...state.user.colleges, action.payload.newCollege],
                    }
                };
            } else {
                console.log("c")
                return {
                    ...state,
                    user: {
                        ...state.user,
                        colleges: [action.payload.newCollege],
                    }
                };
            }
        case "DELETE_COLLEGE":
            console.log(action);
            return {
                ...state,
                clusterUpdated: false,
                routesUpdated: [],
                user: {
                    ...state.user,
                    colleges: [...state.user.colleges.filter(c => c.id !== action.payload.deletedCollegeID)],
                }
            };
        case "REQUEST_UPDATE_ROUTE": {
            return {
                ...state,
                updatingRoute: true,
            }
        }
        case "DELETE_DATA":
            return {
                ...state,
                user: {
                    ...state.user,
                    route: [],
                    colleges: [],
                }

            }
        case "UPDATE_CLUSTERS":
            return {
                ...state,
                clusterUpdated: true,
                routesUpdated: action.payload ? [] : state.routesUpdated,
                route: action.payload ? action.payload.clusters : state.route
            }
        default:
            return state
    }
}

export default userReducer;