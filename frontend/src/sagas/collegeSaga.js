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
    const response = yield call(collegeAPI.getNearbyAirports, payload.payload)
    console.log(response);
    // yield put({
    //     payload: { 
    //         airports: JSON.parse(response.airports)
    //     },
    //     type: 'UPDATE_AIRPORTS',
    // });
    yield put({
        payload: { 
            airports: [{code: "LAX", name: "Los Angeles International Airport", lat: 33.9415889, lon: -118.40853, state: "California", city: "Los Angeles", website: "http://www.lawa.org/lax/welcomeLAX.cfm"}]
        },
        type: 'UPDATE_AIRPORTS',
    });
}
