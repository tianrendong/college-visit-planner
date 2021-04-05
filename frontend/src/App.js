import './App.css';
import React, { useState, useEffect } from 'react';
import Sidebar from "./components/sidebar/index"
import Entrance from "./components/entrance"
import Infobar from "./components/infobar/index"
import Map from "./components/map/index"
import MuiAlert from '@material-ui/lab/Alert';
import IconButton from '@material-ui/core/IconButton';
import ArrowBackIosIcon from '@material-ui/icons/ArrowBackIos';
import ReplyIcon from '@material-ui/icons/Reply';
import { connect, useDispatch } from 'react-redux';
import { SnackbarProvider } from 'notistack';

function App(props) {
    const dispatch = useDispatch();
    
    const back = {
        'clusters': 'default',
        'zoomedIn': 'clusters',
        'default': 'default'
    }

    const handleGoBack = () => {
        dispatch({
            payload: {viewport: back[props.viewport], loggedIn: props.loggedIn},
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
                { ((props.viewport !== 'default') || (props.markerClicked !== {})) &&
                <div className="backButtonContainer">
                    <IconButton size="large" onClick={handleGoBack}>
                        <ReplyIcon fontSize="large" className="iconMargin"/>
                    </IconButton>
                </div>
                }    
            </SnackbarProvider>
        </>
    )
}

const mapStateToProps = ({ rUser: { loggedIn, user, error }, rMap : { viewport, markerClicked } }) => 
({ loggedIn, user, error, viewport, markerClicked });

export default connect(mapStateToProps)(App);