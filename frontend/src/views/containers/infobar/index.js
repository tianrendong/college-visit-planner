import React, { useState, useEffect } from 'react';
import { useDispatch } from 'react-redux';
import Drawer from '@material-ui/core/Drawer';
import './index.css'
import IconButton from '@material-ui/core/IconButton';
import { connect } from 'react-redux';
import CloseIcon from '@material-ui/icons/Close';
import MyColleges from '../../views/myColleges/index'
import Settings from '../../views/settings/index'
import {routeActions} from '../../../actions/routeActions'

const routes = {
    'myColleges': <MyColleges />,
    'settings': <Settings />
}

const Infobar = (props) => {
    const [open, setOpen] = useState(false);
    const dispatch = useDispatch();

    useEffect(() => {
        if (routes.hasOwnProperty(props.infobar)) {
            setOpen(true);
        } else {
            setOpen(false);
        }
    }, [props.infobar])

    return (
        <div className="infobarPage">
            <Drawer 
                variant="persistent"
                anchor="left"
                open={open}
                docked='true'>
                <div className="infobarPlaceholder"/>
                <div className="infobarInnerContainer">
                    {routes[props.infobar]}
                </div>
            </Drawer>
        </div>
    )
}

const mapStateToProps = ({ rRoute: { infobar } }) => ({ infobar });

export default connect(mapStateToProps)(Infobar);




