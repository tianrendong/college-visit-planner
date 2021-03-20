exports.get = (input) => fetch("/api/maps/nearby" + new URLSearchParams({
    input
}));