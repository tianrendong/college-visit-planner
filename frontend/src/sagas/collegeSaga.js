import { call, put } from 'redux-saga/effects';
import { collegeAPI } from '../api/collegeAPI'

export const CollegeSaga = {
    defaultCollegesAsync,
}

export function* defaultCollegesAsync() {
    const response = yield call(collegeAPI.getDefaultColleges)
    console.log(response);
    yield put({
        payload: { defaultColleges: response },
        type: 'RENDER_DEFAULT_COLLEGES',
    });
}
