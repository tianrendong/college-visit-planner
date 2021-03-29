import { useDispatch } from 'react-redux';

export const userAPI = {
    requestLogin,
    signup,
    // logout,
    // // getById,
    // update,
    // delete: _delete
};

function requestLogin(payload) {
    console.log(payload)
    const request = new Request('/api/user/login', {
        method: 'POST',
        body: JSON.stringify({ 
            username: payload.username, 
            password: payload.password }),
    })

    return fetch(request)
        .then(response => response.json())
        .then(data => data);
}

function checkUsername(username) {
    const request = new Request('/api/user/checkUsername', {
        method: 'POST',
        body: JSON.stringify({ username })
    })

    return fetch(request).then(res => {
        console.log(res)
    })
}

function signup(firstname, lastname, username, password) {
    const request = new Request('/api/user/signup', {
        method: 'POST',
        body: JSON.stringify({ firstname, lastname, username, password })
    })

    return fetch(request).then(res => {
        console.log(res)
    })
}

