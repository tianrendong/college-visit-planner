import React, { useState, useEffect } from 'react';
import { useDispatch } from 'react-redux';
import Drawer from '@material-ui/core/Drawer';
import './index.css'
import IconButton from '@material-ui/core/IconButton';
import { makeStyles } from '@material-ui/core/styles';
import { connect } from 'react-redux';
import CloseIcon from '@material-ui/icons/Close';
import MyColleges from './myColleges/index'
import Settings from './settings/index'
import {routeActions} from '../../actions/routeActions'

const routes = {
    'myColleges': <MyColleges />,
    'settings': <Settings />
}

const useStyles = makeStyles(() => ({
    paper: { 
        zIndex: 5, 
        display: 'flex',
        flexDirection: 'row',
        background: '#FBFAF8',
    },
  }));

const Infobar = (props) => {
    const dispatch = useDispatch();
    const classes = useStyles();

    return (
            <Drawer 
                classes={{paper: classes.paper}}
                variant="persistent"
                anchor="left"
                open={props.infobar !== ''}
                docked='true'>
                <div className="infobarPlaceholder"/>
                <div className="infobarInnerContainer">
                <div className="drawerHeader">
                    <IconButton
                        onClick={() => dispatch(routeActions.navigateInfobar(''))}>
                        <CloseIcon className="closeIcon" />
                    </IconButton>
                </div>
                    {routes[props.infobar]}
                </div>
            </Drawer>
    )
}

const mapStateToProps = ({ rRoute: { infobar } }) => ({ infobar });

export default connect(mapStateToProps)(Infobar);




