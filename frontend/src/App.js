import './App.css';
import React, { useState, useEffect } from 'react';
import Sidebar from "./views/sidebar/index"
import Entrance from "./views/entrance/index"
import Infobar from "./views/infobar/index"
import Map from "./views/map/index"
import MuiAlert from '@material-ui/lab/Alert';
import { connect } from 'react-redux';
import { SnackbarProvider } from 'notistack';

function Alert(props) {
    return <MuiAlert elevation={6} variant="filled" {...props} />;
}

function App(props) {
    return (
        <>
            <SnackbarProvider maxSnack={1}>
                {/* <Entrance/> */}
                <Map />
                <Infobar />
                <Sidebar />
            </SnackbarProvider>
        </>
    )
}

const mapStateToProps = ({ rUser: { loggedIn, user, error } }) => ({ loggedIn, user, error });

export default connect(mapStateToProps)(App);