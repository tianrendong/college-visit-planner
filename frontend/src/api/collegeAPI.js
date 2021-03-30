export const collegeAPI = {
   getDefaultColleges,
};

function getDefaultColleges() {
    const request = new Request("/api/college/defaultColleges", {
        method: 'GET',
    })
    return fetch(request)
        .then(response => response.json())
        .then(data => data);
}