import { actionChannel, call, put } from 'redux-saga/effects';
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
    console.log(response);
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

export function* updateRouteAsync(payload) {
    const response = yield call(userAPI.requestUpdateRoute, payload.payload.colleges)
    console.log(response);
    yield put({
        payload: { 
            clusterIndex: payload.payload.clusterIndex,
            route: response, 
        },
        type: 'UPDATE_ROUTE',
    });
}

export function* updateClustersAsync(payload) {
    console.log(payload)
    const response = yield call(userAPI.requestUpdateClusters, payload.payload)
    console.log(response);
    yield put({
        payload: { clusters: response },
        type: 'UPDATE_CLUSTERS',
    });
}


export function* addCollegeAsync(payload) {
    const response = yield call(userAPI.requestAddCollege, payload.payload)
    console.log(response);
    if (response.hasOwnProperty("newCollege")) {
        yield put({
            payload: { 
                newCollege: JSON.parse(response.newCollege)
            },
            type: 'ADD_COLLEGE',
        });
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
    console.log(payload);
    const response = yield call(userAPI.requestDeleteCollege, payload.payload)
    console.log(response);
    if (response.hasOwnProperty("deletedCollegeID")) {
        yield put({
            payload: { 
                deletedCollegeID: JSON.parse(response.deletedCollegeID)
            },
            type: 'DELETE_COLLEGE',
        });
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
    console.log(payload);
    const response = yield call(userAPI.requestDeleteData, payload.payload)
    console.log(response);
    if (response === true) {
        yield put({
            type: 'DELETE_DATA',
        });
    } 
}

export function* deleteAccountAsync(payload) {
    console.log(payload);
    const response = yield call(userAPI.requestDeleteAccount, payload.payload)
    console.log(response);
    if (response === true) {
        yield put({
            type: 'LOGOUT',
        });
    } 
}

