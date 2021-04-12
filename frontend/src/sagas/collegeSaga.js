import { call, put } from 'redux-saga/effects';
import { collegeAPI } from '../api/collegeAPI'

export const CollegeSaga = {
    defaultCollegesAsync,
    getCollegeInfoAsync
}

export function* defaultCollegesAsync() {
    const response = yield call(collegeAPI.getDefaultColleges)
    yield put({
        payload: { defaultColleges: response },
        type: 'RENDER_DEFAULT_COLLEGES',
    });
}

export function* getCollegeInfoAsync(payload) {
    const response = yield call(collegeAPI.getCollegeInfo, payload.payload)
    console.log(response);
    console.log(typeof(response))
    yield put({
        payload: { 
            collegeInfo: response
        },
        type: 'UPDATE_CURRENT_COLLEGE',
    });
}


