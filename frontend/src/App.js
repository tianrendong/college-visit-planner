import './App.css';
import React, { useState, useEffect } from 'react';
import Sidebar from "./components/sidebar/index"
import Entrance from "./components/entrance"
import Infobar from "./components/infobar/index"
import Map from "./components/map/index"
import { connect } from 'react-redux';
import { SnackbarProvider } from 'notistack';
import Lottie from 'react-lottie';
import Backdrop from '@material-ui/core/Backdrop';
import mapAnimation from './assets/mapAnimation.json';
import { withStyles, makeStyles } from '@material-ui/core/styles';
import HelpIcon from '@material-ui/icons/Help';
import IconButton from '@material-ui/core/IconButton';
import Tooltip from '@material-ui/core/Tooltip';

const useStyles = makeStyles((theme) => ({
    backdrop: {
      zIndex: theme.zIndex.drawer + 1,
      color: '#fff',
    },
    tooltipButton: {
        position: 'absolute',
        zIndex: 4,
        padding: '3px',
        margin: '14px'
    },
    tooltip: {
        zIndex:4,
      },
  }));

  const tooltip = {
      "default": "Click on a building to view college information, or zoom in to see more",
      "clusters": "Click on a flag to see colleges inside the cluster",
      "zoomedIn": "Hover over the markers to see each location"
}

const MyTooltip = withStyles(() => ({
    tooltip: {
      maxWidth: 220,
      fontSize: '15px',
      padding: '10px 5px 10px 18px',
      marginTop: '10px',
      zIndex: 4
    },
  }))(Tooltip);

function App(props) {
    const classes = useStyles();
    const [showTooltip, setShowToolTip] = useState(false);

    useEffect(() => {
        setShowToolTip(!props.tooltip.includes(props.viewport) && !props.updatingRoute);
    }, [props.updatingRoute, props.viewport, props.tooltip])

    const handleCloseTooltip = () => {
        setShowToolTip(false);
      };
    
      const handleShowTooltip = () => {
        setShowToolTip(true);
      };

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
                <Entrance/>
                <Backdrop className={classes.backdrop} open={props.updatingRoute}>
                    <Lottie 
                        options={animationOptions}
                        height={400}
                        width={400}
                    />
                </Backdrop>

                <div className="mapPageContainer">
                <MyTooltip open={showTooltip} onOpen={handleShowTooltip} onClose={handleCloseTooltip} 
                title={tooltip[props.viewport]} placement="right" classes={{popper: classes.tooltip}}>
                    <IconButton size="large" className={classes.tooltipButton}>
                        <HelpIcon fontSize="large"/>
                    </IconButton>
                </MyTooltip>
                    
                    <Map />
                </div>
                <Infobar />
                <Sidebar />
            </SnackbarProvider>
        </>
    )
}

const mapStateToProps = ({ rMap: { viewport }, 
    rUser: { updatingRoute }, rRoute: { tooltip } }) => 
({ viewport, updatingRoute, tooltip });

export default connect(mapStateToProps)(App);