import { takeLatest, call, all, put } from 'redux-saga/effects';
import { UserSaga } from './UserSaga';

export default function* watchAll() {
    yield all([
        takeLatest('LOGIN_REQUEST', UserSaga.loginAsync),
        takeLatest('SIGNUP_REQUEST', UserSaga.signupAsync),
    ]);
}