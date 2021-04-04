export const mapActions = {
    clickMarker
};

function clickMarker(type, content) {
    return {
        payload: {type, content},
        type: 'CLICK_MARKER',
    };
}