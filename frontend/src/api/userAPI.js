export const userAPI = {
    requestLogin,
    requestSignup,
    requestLogout,
    requestUpdateRoute,
    requestUpdateClusters,
    requestAddCollege,
    requestDeleteCollege,
    requestDeleteData,
    requestDeleteAccount
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

function requestSignup(payload) {
    console.log(payload)
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
        .then(data => {
            console.log(data)
            return data}
            );
}

function requestUpdateClusters(payload) {
    const request = new Request("/api/user/getClusters", {
        method: 'POST',
        body: JSON.stringify(payload)
    })
    return fetch(request)
        .then(response => response.json())
        .then(data => data);
}


function requestAddCollege(payload) {
    const request = new Request("/api/user/addCollege", {
        method: 'POST',
        body: JSON.stringify({ 
            username: payload.username,
            collegeID: payload.collegeID 
        })
    })
    return fetch(request)
        .then(response => response.json())
        .then(data => data);
}

function requestDeleteCollege(payload) {
    const request = new Request("/api/user/deleteCollege", {
        method: 'POST',
        body: JSON.stringify({ 
            username: payload.username,
            collegeID: payload.collegeID 
        })
    })
    return fetch(request)
        .then(response => response.json())
        .then(data => data);
}

function requestDeleteData(payload) {
    const request = new Request('/api/user/deleteData', {
        method: 'POST',
        body: JSON.stringify({ 
            username: payload}),
    })


    return fetch(request)
        .then(response => response.json())
        .then(data => data);
}

function requestDeleteAccount(payload) {
    const request = new Request('/api/user/deleteAccount', {
        method: 'POST',
        body: JSON.stringify({ 
            username: payload}),
    })


    return fetch(request)
        .then(response => response.json())
        .then(data => data);
}