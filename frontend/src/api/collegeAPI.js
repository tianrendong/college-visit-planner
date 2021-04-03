export const collegeAPI = {
   getDefaultColleges,
   getAutocorrectColleges,
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