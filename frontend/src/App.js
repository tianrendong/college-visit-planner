import './App.css';
import React, { useState, useEffect } from 'react';
import Sidebar from "./components/sidebar/index"
import Entrance from "./components/entrance"
import Infobar from "./components/infobar/index"
import Map from "./components/map/index"
import MuiAlert from '@material-ui/lab/Alert';
import IconButton from '@material-ui/core/IconButton';
import ArrowBackIosIcon from '@material-ui/icons/ArrowBackIos';
import { connect, useDispatch } from 'react-redux';
import { SnackbarProvider } from 'notistack';

function Alert(props) {
    return <MuiAlert elevation={6} variant="filled" {...props} />;
}

function App(props) {
    const dispatch = useDispatch();
    
    const back = {
        'clusters': 'default',
        'zoomedIn': 'clusters'
    }

    const handleGoBack = () => {
        dispatch({
            payload: {viewport: back[props.viewport]},
            type: 'NAVIGATE_BACK',
        })
    }

    return (
        <>
            <SnackbarProvider maxSnack={1}>
                {/* <Entrance/> */}
                <Map />
                <Infobar />
                <Sidebar />
                { (props.viewport !== 'default') &&
                <div className="backButtonContainer">
                    <IconButton onClick={handleGoBack}>
                        <ArrowBackIosIcon className="closeIcon" />
                    </IconButton>
                </div>
                }    
            </SnackbarProvider>
        </>
    )
}

const mapStateToProps = ({ rUser: { loggedIn, user, error }, rMap : { viewport } }) => ({ loggedIn, user, error, viewport });

export default connect(mapStateToProps)(App);