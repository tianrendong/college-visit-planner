exports.get = (input) => fetch("/api/account/login" + new URLSearchParams({
    input
}));

exports.get = (input) => fetch("/api/account/register" + new URLSearchParams({
    input
}));

exports.get = (input) => fetch("/api/account/addCollege" + new URLSearchParams({
    input
}));

exports.get = (input) => fetch("/api/account/deleteCollege" + new URLSearchParams({
    input
}));

