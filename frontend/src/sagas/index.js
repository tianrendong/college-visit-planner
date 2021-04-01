import { takeLatest, call, all, put } from 'redux-saga/effects';
import { UserSaga } from './userSaga';
import { CollegeSaga } from './collegeSaga';

export default function* watchAll() {
    yield all([
        takeLatest('LOGIN_REQUEST', UserSaga.loginAsync),
        takeLatest('SIGNUP_REQUEST', UserSaga.signupAsync),

        takeLatest('REQUEST_DEFAULT_COLLEGES', CollegeSaga.defaultCollegesAsync),

        takeLatest('REQUEST_UPDATE_ROUTE', UserSaga.updateRouteAsync),

    ]);
}