export const collegeAPI = {
   getDefaultColleges,
   getAutocorrectColleges,
   getNearbyAirport,
   getCollegesByID,
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

function getNearbyAirport(payload) {
    console.log(payload)
    const request = new Request("/api/college/nearbyAirport", {
        method: 'POST',
        body: JSON.stringify({
            collegeID: payload
        })
    })

    return fetch(request)
        .then(response => response.json())
        .then(data => 
            {console.log(data);
                return data});
}

function getCollegesByID(payload) {
    console.log(payload)
    const request = new Request("/api/college/getCollegesByID", {
        method: 'POST',
        body: JSON.stringify({
            collegeIDs: payload // List of College IDs
        })
    })

    return fetch(request)
        .then(response => response.json())
        .then(data => 
            {console.log(data);
                return data});
}