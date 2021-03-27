export const userAPI = {
    login,
    // logout,
    // register,
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

