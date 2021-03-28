export const userAPI = {
    login,
    signup,
    // logout,
    // // getById,
    // update,
    // delete: _delete
};

function login(username, password) {
    const request = new Request('/api/user/login', {
        method: 'POST',
        body: JSON.stringify({username, password})
    })

    return fetch(request).then(res => {
        console.log(res)
    })
}

function checkUsername(username) {
    const request = new Request('/api/user/checkUsername', {
        method: 'POST',
        body: JSON.stringify({username})
    })

    return fetch(request).then(res => {
        console.log(res)
    })
}

function signup(firstname, lastname, username, password) {
    const request = new Request('/api/user/signup', {
        method: 'POST',
        body: JSON.stringify({firstname, lastname, username, password})
    })

    return fetch(request).then(res => {
        console.log(res)
    })
}

