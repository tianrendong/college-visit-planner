import { userAPI } from '../api/userAPI';

export const userActions = {
    // login,
    // logout,
    // register,
    // getAll,
    // delete: _delete
};


// function login(username, password) {
//     userAPI.requestLogin(username, password)
//         .then(data => {
//             if (data.success === true) {
//                 // console.log(JSON.parse(data.user))
//                 return {
//                     payload: { user: JSON.parse(data.user) },
//                     type: 'LOGIN_SUCCESS',
//                 };
//             } else {
//                 return {
//                     payload: { loginError: data.error },
//                     type: 'LOGIN_FAILURE',
//                 };
//             }
//         })

    
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



function signup(firstname, lastname, username, password) {
    userAPI.signup(firstname, lastname, username, password)
        .then(a => console.log("aaa"))
}