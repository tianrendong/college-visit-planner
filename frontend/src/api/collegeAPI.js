export const collegeAPI = {
    getDefaultColleges,
    getAutocorrectColleges,
    getCollegeInfo,
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
        body: JSON.stringify({ input })
    })

    return fetch(request)
        .then(response => response.json())
        .then(data => data);
}

function getCollegeInfo(payload) {
    console.log(payload)
    const request = new Request("/api/college/collegeInfo", {
        method: 'POST',
        body: JSON.stringify({
            collegeID: payload
        })
    })

    return fetch(request)
        .then(response => response.json())
        .then(data => {
            
            return {
                college: JSON.parse(data.college),
                nearbyAirport: data.hasOwnProperty("nearbyAirport") ? JSON.parse(data.nearbyAirport) : null, 
                nearbyColleges: data.hasOwnProperty("nearbyColleges") ? JSON.parse(data.nearbyColleges) : null,
            }
        })
    }