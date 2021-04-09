export const userAPI = {
    requestLogin,
    requestSignup,
    requestLogout,
    requestUpdateRoute,
    requestAddCollege,
    requestDeleteCollege,
    requestDeleteData
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

function requestUpdateRoute(payload) {
    console.log(payload)
    const request = new Request("/api/user/getRoute", {
        method: 'POST',
        body: JSON.stringify({colleges: payload})
    })
    return fetch(request)
        .then(response => response.json())
        .then(data => data);
}


function requestAddCollege(payload) {
    console.log(payload)
    const request = new Request("/api/user/addCollege", {
        method: 'POST',
        body: JSON.stringify({ 
            username: payload.username,
            collegeID: payload.collegeID 
        })
    })
    console.log(request);
    return fetch(request)
        .then(response => response.json())
        .then(data => data);
}

function requestDeleteCollege(payload) {
    console.log(payload)
    const request = new Request("/api/user/deleteCollege", {
        method: 'POST',
        body: JSON.stringify({ 
            username: payload.username,
            collegeID: payload.collegeID 
        })
    })
    console.log(request);
    return fetch(request)
        .then(response => response.json())
        .then(data => data);
}

function requestDeleteData(payload) {
    console.log(payload)
    const request = new Request('/api/user/deleteData', {
        method: 'POST',
        body: JSON.stringify({ 
            username: payload}),
    })

    console.log(request)

    return fetch(request)
        .then(response => response.json())
        .then(data => data);
}