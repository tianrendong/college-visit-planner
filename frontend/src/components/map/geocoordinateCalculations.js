/**
 * Get a center latitude,longitude from an array of like geopoints
 * @param array data 2 dimensional array of latitudes and longitudes
 * For Example:
 * $data = array
 * (
 *   0 = > array(45.849382, 76.322333),
 *   1 = > array(45.843543, 75.324143),
 *   2 = > array(45.765744, 76.543223),
 *   3 = > array(45.784234, 74.542335)
 * );
*/
export function findCenter(data) {
    if (data.length <= 0) {
        return false;
    }

    let num_coords = data.length;

    let X = 0.0;
    let Y = 0.0;
    let Z = 0.0;

    for (let i = 0; i < data.length; i++) {
        let lat = data[i][0] * Math.PI / 180;
        let lon = data[i][1] * Math.PI / 180;

        let a = Math.cos(lat) * Math.cos(lon);
        let b = Math.cos(lat) * Math.sin(lon);
        let c = Math.sin(lat);

        X += a;
        Y += b;
        Z += c;
    }

    X /= num_coords;
    Y /= num_coords;
    Z /= num_coords;

    let lon = Math.atan2(Y, X);
    let hyp = Math.sqrt(X * X + Y * Y);
    let lat = Math.atan2(Z, hyp);

    let newX = (lat * 180 / Math.PI);
    let newY = (lon * 180 / Math.PI);

    return new Array(newX, newY);
}

