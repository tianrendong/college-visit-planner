import React, { useState } from 'react';
import { connect } from 'react-redux';
import './index.css'

import { makeStyles } from '@material-ui/core/styles';
import useWindowSize from "../../hooks/useWindowSize";
import Login from "./login/index";
import SignUp from "./signup/index";
import Userhome from './userhome/index';
import CollegeInfo from './collegeInfo/index'

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
      position: 'absolute'
    },
  }));

const Sidebar = (props) => {
    const classes = useStyles();
    const { height, width } = useWindowSize();
    const [open, setOpen] = useState(false);

    return (
        <div className={classes.root}>
            <IconButton
                onClick={() => setOpen(true)}>
                <PersonIcon fontSize="large" />
            </IconButton>
            <Drawer variant="persistent"
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

const mapStateToProps = ({ rRoute: { sidebar } }) => ({ sidebar });

export default connect(mapStateToProps)(Sidebar);




