import { userAPI } from '../api/userAPI';

export const userActions = {
    login,
    // logout,
    // register,
    // getAll,
    // delete: _delete
};

function login(username, password) {
    
    userAPI.login(username, password)
    .then(a => console.log("userAction: loggedin"))
    //         .then(
    //             user => { 
    //                 dispatch(success(user));
    //                 history.push(from);
    //             },
    //             error => {
    //                 dispatch(failure(error.toString()));
    //                 dispatch(alertActions.error(error.toString()));
    //             }
    //         );

    return {
        payload: {username, password},
        type: 'LOGIN_REQUEST',
      };
    // return dispatch => {
    //     dispatch(request({ username }));

    //     userService.login(username, password)
    //         .then(
    //             user => { 
    //                 dispatch(success(user));
    //                 history.push(from);
    //             },
    //             error => {
    //                 dispatch(failure(error.toString()));
    //                 dispatch(alertActions.error(error.toString()));
    //             }
    //         );
    // };

    // function request(user) { return { type: userConstants.LOGIN_REQUEST, user } }
    // function success(user) { return { type: userConstants.LOGIN_SUCCESS, user } }
    // function failure(error) { return { type: userConstants.LOGIN_FAILURE, error } }
}