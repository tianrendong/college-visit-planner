import './App.css';
import React, { useState, useEffect } from 'react';
import Sidebar from "./views/sidebar/index"
import Entrance from "./views/entrance/index"
import Infobar from "./views/infobar/index"
import Map from "./views/map/index"
import Snackbar from '@material-ui/core/Snackbar';
import MuiAlert from '@material-ui/lab/Alert';
import { routeActions } from './actions/routeActions';
import { connect, useDispatch } from 'react-redux';

function Alert(props) {
    return <MuiAlert elevation={6} variant="filled" {...props} />;
}

function App(props) {
    const [alertOpen, setAlertOpen] = useState(false);
    const [errorMsg, setErrorMsg] = useState('');
    const [successMsg, setSuccessMsg] = useState('');

    useEffect(() => {
        if (props.signedUp) {
            setSuccessMsg('Signed up')
        }
    }
        , [props.signedUp])

    useEffect(() => {
        setErrorMsg(props.error);
        setAlertOpen(true);
    }
        , [props.error])

    console.log(props.error);

    const handleClose = (event, reason) => {
        if (reason === 'clickaway') { return; }
        setAlertOpen(false);
    };

    return (
        <>
            {/* <Entrance/> */}
            <Map />
            <Infobar />
            <Sidebar />
            {(props.error !== '') &&
            <Snackbar open={alertOpen} autoHideDuration={1800} onClose={handleClose}>
                <Alert onClose={handleClose} severity="error">
                    {props.error}
                </Alert>
            </Snackbar>}

        </>
    )
}

const mapStateToProps = ({ rUser: { loggedIn, user, error } }) => ({ loggedIn, user, error });

export default connect(mapStateToProps)(App);