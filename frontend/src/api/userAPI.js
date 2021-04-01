export const userAPI = {
    requestLogin,
    requestSignup,
    requestLogout,
    requestUpdateRoute,
    // logout,
    // // getById,
    // update,
    // delete: _delete
};

function requestLogin(payload) {
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

function requestSignup(payload) {
    console.log(payload);
;    const request = new Request('/api/user/signup', {
        method: 'POST',
        body: JSON.stringify({ 
            username: payload.username, 
            password: payload.password,
            firstname: payload.firstname, 
            lastname: payload.lastname })
    })

    return fetch(request)
    .then(response => response.json())
        .then(data => data);
}

function requestLogout() {
    const request = new Request('/api/user/logout', {method: 'POST'})

    return fetch(request)
    .then(response => response.json())
        .then(data => data);
}

function requestUpdateRoute() {
    const request = new Request("/api/user/getRoute", {
        method: 'GET',
    })
    return fetch(request)
        .then(response => response.json())
        .then(data => data);
}
