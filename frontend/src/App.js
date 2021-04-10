import './App.css';
import React from 'react';
import Sidebar from "./components/sidebar/index"
import Entrance from "./components/entrance"
import Infobar from "./components/infobar/index"
import Map from "./components/map/index"
import { connect, useDispatch } from 'react-redux';
import { SnackbarProvider } from 'notistack';
import Lottie from 'react-lottie';
import Backdrop from '@material-ui/core/Backdrop';
import mapAnimation from './assets/mapAnimation.json';
import { makeStyles } from '@material-ui/core/styles';

const useStyles = makeStyles((theme) => ({
    backdrop: {
      zIndex: theme.zIndex.drawer + 1,
      color: '#fff',
    },
  }));

function App(props) {
    const classes = useStyles();

    const animationOptions = {
        loop: true,
        autoplay: true,
        animationData: mapAnimation,
        rendererSettings: {
            preserveAspectRatio: "xMidYMid slice"
        }
    };

    return (
        <>
            <SnackbarProvider maxSnack={1}>
                
                {/* <Entrance/> */}

                <Backdrop className={classes.backdrop} open={props.updatingRoute}>
                    <Lottie 
                        options={animationOptions}
                        height={400}
                        width={400}
                    />
                </Backdrop>

                <div className="mapPageContainer">
                    <Map />
                </div>
                <Infobar />
                <Sidebar />
            </SnackbarProvider>
        </>
    )
}

const mapStateToProps = ({ rUser: { loggedIn, user, error, updatingRoute } }) => ({ loggedIn, user, error, updatingRoute });

export default connect(mapStateToProps)(App);