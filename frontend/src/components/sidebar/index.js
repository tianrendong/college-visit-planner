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
import CloseIcon from '@material-ui/icons/Close';

const routes = {
    'login': <Login />,
    'signup': <SignUp />,
    'userhome': <Userhome />,
    'collegeInfo': <CollegeInfo />,
    'routeInfo': <RouteInfo/>
}

const useStyles = makeStyles((theme) => ({
    root: {
      zIndex:2,
      position: 'absolute',
    },
    paper: { 
       background: '#FBFAF8'
    },
  }));

const Sidebar = (props) => {
    const classes = useStyles();
    const dispatch = useDispatch();

    const toggleSidebarOpen = () => {
        dispatch({
            type: 'TOGGLE_SIDEBAR'
        });
        dispatch(routeActions.navigateInfobar(''));
    }

    return (
        <div className={classes.root} >
            <IconButton
                onClick={() => toggleSidebarOpen()}>
                <PersonIcon fontSize="large" className="iconMargin"/>
            </IconButton>
            <Drawer 
                classes={{paper: classes.paper}}
                variant="persistent"
                anchor="left"
                open={props.sidebarOpen}
                docked='true'>
                <div className="drawerHeader">
                    <IconButton
                        onClick={() => toggleSidebarOpen()}>
                        <CloseIcon className="closeIcon" />
                    </IconButton>
                </div>
                <div className="sidebarInnerContainer">
                    {routes[props.sidebar]}
                </div>
            </Drawer>
        </div>
    )
}

const mapStateToProps = ({ rRoute: { sidebar, sidebarOpen} }) => ({ sidebar, sidebarOpen});

export default connect(mapStateToProps)(Sidebar);




