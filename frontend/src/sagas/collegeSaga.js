import { call, put } from 'redux-saga/effects';
import { collegeAPI } from '../api/collegeAPI'

export const CollegeSaga = {
    defaultCollegesAsync,
    getNearbyAirportsAsync
}

export function* defaultCollegesAsync() {
    const response = yield call(collegeAPI.getDefaultColleges)
    console.log(response);
    yield put({
        payload: { defaultColleges: response },
        type: 'RENDER_DEFAULT_COLLEGES',
    });
}

export function* getNearbyAirportsAsync(payload) {
    console.log(payload);
    const response = yield call(collegeAPI.getNearbyAirport, payload.payload)
    console.log(response);
    console.log(typeof(response))
    yield put({
        payload: { 
            airport: response
        },
        type: 'UPDATE_AIRPORTS',
    });
}
