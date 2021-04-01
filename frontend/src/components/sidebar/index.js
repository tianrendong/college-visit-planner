import React, { useState, useEffect } from 'react';
import { connect, useDispatch } from 'react-redux';
import './index.css'

import { makeStyles } from '@material-ui/core/styles';
import Login from "./login/index";
import SignUp from "./signup/index";
import Userhome from './userhome/index';
import CollegeInfo from './collegeInfo/index'
import { routeActions } from "../../actions/routeActions";

import Drawer from '@material-ui/core/Drawer';
import IconButton from '@material-ui/core/IconButton';
import PersonIcon from '@material-ui/icons/Person';
import CloseIcon from '@material-ui/icons/Close';

const routes = {
    'login': <Login />,
    'signup': <SignUp />,
    'userhome': <Userhome />,
    'collegeInfo': <CollegeInfo />
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
    const [open, setOpen] = useState(false);

    // const toggleSidebarOpen = () => {
    //     dispatch({
    //         type: 'TOGGLE_SIDEBAR',
    //     });
    //     dispatch(routeActions.closeInfobar);
    // }

    useEffect(() => {
        if ((props.sidebar !== '') && routes.hasOwnProperty(props.sidebar)) { 
            setOpen(true);
        } else {
            setOpen(false);
        }
    }, [props.sidebar])

    // useEffect(() => {
    //     if (props.sidebarOpen === true) { 
    //         setOpen(true);
    //     } else {
    //         setOpen(false);
    //     }
    // }, [props.sidebar])

    return (
        <div className={classes.root} >
            <IconButton
                onClick={() => setOpen(true)}>
                <PersonIcon fontSize="large" />
            </IconButton>
            <Drawer 
                classes={{paper: classes.paper}}
                variant="persistent"
                anchor="left"
                open={open}
                docked='true'>
                <div className="drawerHeader">
                    <IconButton
                        onClick={() => setOpen(false)}>
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




