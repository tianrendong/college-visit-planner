import { takeLatest, call, all, put } from 'redux-saga/effects';
import { userActions } from '../actions/userActions'
import { userAPI } from '../api/userAPI'

// The root saga is what we actually send to Redux's middleware. In here we fork
// each saga so that they are all "active" and listening.
// Sagas are fired once at the start of an app and can be thought of as processes running
// in the background, watching actions dispatched to the store.

function* loginAsync(payload) {
    const response = yield call(userAPI.requestLogin, payload.payload)
    console.log(response);
    console.log(response.user);
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

export function* watchAll() {
    yield all([
        takeLatest('LOGIN_REQUEST', loginAsync),
    ]);
}


export default watchAll;
