import { takeLatest, call, all, put } from 'redux-saga/effects';
import { userAPI } from '../api/userAPI'

export const UserSaga = {
    loginAsync,
    signupAsync,
}

export function* loginAsync(payload) {
    const response = yield call(userAPI.requestLogin, payload.payload)
    if (response.success === true ) {
        yield put({
            payload: { user: JSON.parse(response.user) },
            type: 'LOGIN_SUCCESS',
        });
    } else {
        yield put({
            payload: { error: response.error },
            type: 'LOGIN_FAILURE',
        });
    }
}

export function* signupAsync(payload) {
    const response = yield call(userAPI.requestSignup, payload.payload)
    console.log(response);
    if (response.success === true ) {
        yield put({
            type: 'SIGNUP_SUCCESS',
        });
    } else {
        yield put({
            payload: { error: response.error },
            type: 'SIGNUP_FAILURE',
        });
    }
}

