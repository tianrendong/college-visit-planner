import React, { useState } from 'react';
import Drawer from '@material-ui/core/Drawer';
import './index.css'
import useWindowSize from "../../../hooks/useWindowSize";
import IconButton from '@material-ui/core/IconButton';
import PersonIcon from '@material-ui/icons/Person';
import { connect } from 'react-redux';

import Login from "../../views/Login";
import SignUp from "../../views/SignUp";
import CloseIcon from '@material-ui/icons/Close';

const routes = {
    'login': <Login />,
    'signup': <SignUp />,
}

const Sidebar = (props) => {
    const { height, width } = useWindowSize();
    const [open, setOpen] = useState(false);

    return (
        <div>
            <div className="page">
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
                            <CloseIcon className="closeIcon"/>
                        </IconButton>
                    </div>
                    <div className="sidebarInnerContainer">
                        {routes[props.sidebar]}
                    </div>
                </Drawer>
            </div>
        </div>
    )
}

const mapStateToProps = ({ rRoute: { sidebar } }) => ({ sidebar });

export default connect(mapStateToProps)(Sidebar);




