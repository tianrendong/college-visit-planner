import React, { useState } from 'react';
import { connect } from 'react-redux';
import './index.css'

import useWindowSize from "../../../hooks/useWindowSize";
import Login from "../../views/login";
import SignUp from "../../views/signup";
import Userhome from '../../views/userhome/index'
import CollegeInfo from '../../views/collegeInfo/index'

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

const Sidebar = (props) => {
    const { height, width } = useWindowSize();
    const [open, setOpen] = useState(false);

    return (
        <div className="sidebarPage">
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




