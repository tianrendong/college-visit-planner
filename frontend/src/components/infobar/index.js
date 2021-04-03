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
        zIndex: 1, 
        display: 'flex',
        flexDirection: 'row',
    },
  }));

const Infobar = (props) => {
    const classes = useStyles();
    const [open, setOpen] = useState(false);

    useEffect(() => {
        if ((props.infobar !== '') && routes.hasOwnProperty(props.infobar)) { 
            setOpen(true);
        } else {
            setOpen(false);
        }
    }, [props.infobar])

    return (
            <Drawer 
                classes={{paper: classes.paper}}
                variant="persistent"
                anchor="left"
                open={open}
                docked='true'>
                <div className="infobarPlaceholder"/>
                <div className="infobarInnerContainer">
                    {routes[props.infobar]}
                </div>
            </Drawer>
    )
}

const mapStateToProps = ({ rRoute: { infobar } }) => ({ infobar });

export default connect(mapStateToProps)(Infobar);



