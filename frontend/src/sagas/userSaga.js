import { actionChannel, call, put, all } from 'redux-saga/effects';
import { userAPI } from '../api/userAPI'

export const UserSaga = {
    loginAsync,
    signupAsync,
    updateRouteAsync,
    updateClustersAsync,
    addCollegeAsync,
    deleteCollegeAsync,
    deleteDataAsync,
    deleteAccountAsync,
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
    yield put({
        type: 'CLEAR_ERROR',
    });
    const response = yield call(userAPI.requestSignup, payload.payload)
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

export function* updateRouteAsync(payload) {
    const response = yield call(userAPI.requestUpdateRoute, payload.payload.colleges)
    yield put({
        payload: { 
            clusterIndex: payload.payload.clusterIndex,
            route: response, 
        },
        type: 'UPDATE_ROUTE',
    });
}

export function* updateClustersAsync(payload) {
    const response = yield call(userAPI.requestUpdateClusters, payload.payload)
    yield put({
        payload: { clusters: response },
        type: 'UPDATE_CLUSTERS',
    });
}


export function* addCollegeAsync(payload) {
    yield put({
        type: 'CLEAR_ERROR',
    });
    const response = yield call(userAPI.requestAddCollege, payload.payload)
    console.log(response);
    if (response.hasOwnProperty("newCollege")) {
        yield all([put({
            payload: { 
                newCollege: JSON.parse(response.newCollege)
            },
            type: 'ADD_COLLEGE',
        }),
        yield put({
            payload: { 
                msg: "Added college"
            },
            type: 'SUCCESS_MESSAGE',
        })]);
    } else {
        yield put({
            payload: { 
                error: response.error,
            },
            type: 'ERROR',
        });
    } 
}

export function* deleteCollegeAsync(payload) {
    const response = yield call(userAPI.requestDeleteCollege, payload.payload)
    if (response.hasOwnProperty("deletedCollegeID")) {
        yield all([put({
            payload: { 
                deletedCollegeID: JSON.parse(response.deletedCollegeID)
            },
            type: 'DELETE_COLLEGE',
        }),
        yield put({
            payload: { 
                msg: "Deleted college"
            },
            type: 'SUCCESS_MESSAGE',
        })]);
    } else {
        yield put({
            payload: { 
                error: response.error,
            },
            type: 'ERROR',
        });
    } 
}


export function* deleteDataAsync(payload) {
    const response = yield call(userAPI.requestDeleteData, payload.payload)
    if (response === true) {
        yield put({
            type: 'DELETE_DATA',
        });
    } 
}

export function* deleteAccountAsync(payload) {
    const response = yield call(userAPI.requestDeleteAccount, payload.payload)
    if (response === true) {
        yield put({
            type: 'LOGOUT',
        });
    } 
}

