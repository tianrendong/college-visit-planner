import React, { useState, useEffect } from 'react';
import { connect, useDispatch } from 'react-redux';
import './index.css'
import { makeStyles } from '@material-ui/core/styles';
import Login from "./login/index";
import SignUp from "./signup/index";
import Userhome from './userhome/index';
import CollegeInfo from './collegeInfo/index'
import RouteInfo from './routeInfo/index';
import { routeActions } from "../../actions/routeActions";
import Drawer from '@material-ui/core/Drawer';
import IconButton from '@material-ui/core/IconButton';
import PersonIcon from '@material-ui/icons/Person';
import ReplyIcon from '@material-ui/icons/Reply';
import CloseIcon from '@material-ui/icons/Close';

const routes = {
    'login': <Login />,
    'signup': <SignUp />,
    'userhome': <Userhome />,
    'collegeInfo': <CollegeInfo />,
    'routeInfo': <RouteInfo/>
}

const back = {
    'clusters': 'default',
    'zoomedIn': 'clusters',
    'default': 'default'
}

const useStyles = makeStyles((theme) => ({
    root: {
      zIndex:10,
      position: 'absolute',
    },
    paper: { 
       background: '#FBFAF8'
    },
  }));

const Sidebar = (props) => {
    const classes = useStyles();
    const dispatch = useDispatch();

    const handleGoBack = () => {
        dispatch({
            payload: {viewport: back[props.viewport], loggedIn: props.loggedIn},
            type: 'NAVIGATE_BACK',
        })
    }

    return (
        <div className={classes.root} >
            <Drawer 
                classes={{paper: classes.paper}}
                variant="persistent"
                anchor="left"
                open={true}
                docked='true'>
                <div className="drawerHeader">
                   { ((props.viewport !== 'default') || (props.currentCollege !== null)) && 
                    <IconButton size="large" onClick={handleGoBack}>
                        <ReplyIcon fontSize="large" className="iconMargin"/>
                    </IconButton>
                }    
                </div>
                <div className="sidebarInnerContainer">
                    {routes[props.sidebar]}
                </div>
               <div style={{height: '20px'}}></div>
            </Drawer>
        </div>
    )
}

const mapStateToProps = ({ rUser: { loggedIn }, rRoute: { sidebar, sidebarOpen, currentCollege }, rMap : { viewport } }) => 
({ loggedIn, sidebar, sidebarOpen, currentCollege, viewport});

export default connect(mapStateToProps)(Sidebar);




