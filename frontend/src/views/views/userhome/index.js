import React from 'react';
import './index.css'
import Typography from '@material-ui/core/Typography';
import SettingsIcon from '@material-ui/icons/Settings';
import ExitToAppIcon from '@material-ui/icons/ExitToApp';
import Button from '@material-ui/core/Button';
import RouteIcon from "../../../assets/route.svg";
import { routeActions } from '../../../actions/routeActions';
import { connect, useDispatch } from 'react-redux';
import { ReactComponent as Icon } from '../../../assets/way.svg';

const Userhome = (props) => {

    const infobarClosed = (props.infobar === '');

    const dispatch = useDispatch();

    const handleNavigateColleges = () => {
        if (infobarClosed) {
            dispatch(routeActions.navigateMyColleges());
        } else {
            dispatch(routeActions.closeInfobar());
        }

    }

    const handleNavigateRoute = () => {
        // dispatch(routeActions.navigateRoute());
    }

    const handleNavigateSettings = () => {
        if (infobarClosed) {
            dispatch(routeActions.navigateSettings());
        } else {
            dispatch(routeActions.closeInfobar());
        }
    }

    return (
        <div className="userContainer">
            <div className="userHeader">
                <h1 className="userTitle">Welcome,</h1>
                <h1 className="userTitle">Jenny Yu</h1>
            </div>
            <button className="optionButton" onClick={handleNavigateColleges}>
                <SettingsIcon />
                <p className="optionText">Colleges</p>
            </button>
            <button className="optionButton" onClick={handleNavigateRoute}>
                <SettingsIcon />
                <p className="optionText">Route</p>
            </button>
            <button className="optionButton" onClick={handleNavigateSettings}>
                <SettingsIcon />
                <p className="optionText">Settings</p>
            </button>
            {/* <Icon style={{ width: 50, height: 50 }} /> */}
        </div>
    );

}

const mapStateToProps = ({ rRoute: { infobar } }) => ({ infobar });

export default connect(mapStateToProps)(Userhome);