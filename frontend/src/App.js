import './App.css';
import React, { useState, useEffect } from 'react';
import Sidebar from "./components/sidebar/index"
import Entrance from "./components/entrance"
import Infobar from "./components/infobar/index"
import Map from "./components/map/index"
import MuiAlert from '@material-ui/lab/Alert';
import IconButton from '@material-ui/core/IconButton';
import ArrowBackIosIcon from '@material-ui/icons/ArrowBackIos';
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
                <div className="backButtonContainer">
                    <IconButton>
                        <ArrowBackIosIcon className="closeIcon" />
                    </IconButton>
                </div>
            </SnackbarProvider>
        </>
    )
}

const mapStateToProps = ({ rUser: { loggedIn, user, error } }) => ({ loggedIn, user, error });

export default connect(mapStateToProps)(App);