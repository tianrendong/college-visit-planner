export const collegeAPI = {
   getDefaultColleges,
   getAutocorrectColleges,
   getNearbyAirports
};

function getDefaultColleges() {
    const request = new Request("/api/college/defaultColleges", {
        method: 'GET',
    })
    return fetch(request)
        .then(response => response.json())
        .then(data => data);
}

function getAutocorrectColleges(input) {
    const request = new Request("/api/college/autocorrect", {
        method: 'POST',
        body: JSON.stringify({input})
    })

    return fetch(request)
        .then(response => response.json())
        .then(data => 
            {console.log(data);
                return data});
}

function getNearbyAirports(payload) {
    console.log(payload)
    const request = new Request("/api/college/nearbyAirports", {
        method: 'POST',
        body: JSON.stringify({
            college: payload
        })
    })

    return fetch(request)
        .then(response => response.json())
        .then(data => 
            {console.log(data);
                return data});
}