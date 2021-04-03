import React from 'react';
import './index.css'
import Typography from '@material-ui/core/Typography';
// import SettingsIcon from '@material-ui/icons/Settings';
import ExitToAppIcon from '@material-ui/icons/ExitToApp';
import Button from '@material-ui/core/Button';
import { routeActions } from '../../../actions/routeActions';
import { connect, useDispatch } from 'react-redux';
import { ReactComponent as RouteIcon } from '../../../assets/mapsSVG/route.svg'
import { ReactComponent as MyCollegeIcon } from '../../../assets/mapsSVG/school.svg'
import { ReactComponent as SettingsIcon } from '../../../assets/mapsSVG/settings.svg'


const Userhome = (props) => {

    const dispatch = useDispatch();

    const handleNavigateColleges = () => {
        if (props.infobar === 'myColleges') {
            dispatch(routeActions.closeInfobar());
        } else {
            dispatch(routeActions.navigateMyColleges());
        }

    }

    const handleNavigateRoute = () => {
        // dispatch(routeActions.navigateRoute());
        dispatch({
            type: 'REQUEST_UPDATE_ROUTE',
        })
    }

    const handleNavigateSettings = () => {
        if (props.infobar === 'settings') {
            dispatch(routeActions.closeInfobar());
        } else {
            dispatch(routeActions.navigateSettings());
        } 
    }

    return (
        <div className="userContainer">
            <div className="userHeader">
                <h1 className="userTitle">Welcome,</h1>
                {/* <h1 className="userTitle Name">{props.user.firstname}!</h1> */}
            </div>
            <button className="optionButton" onClick={handleNavigateColleges}>
                <MyCollegeIcon className="userhomeIcons" style={{ width: 55, height: 55 }}/>
                <p className="optionText" >Colleges</p>
            </button>
            <button className="optionButton" onClick={handleNavigateRoute}>
                <RouteIcon className="userhomeIcons" style={{ width: 50, height: 50 }}/>
                <p className="optionText">Route</p>
            </button>
            <button className="optionButton" onClick={handleNavigateSettings}>
                <SettingsIcon className="userhomeIcons" style={{ width: 45, height: 45 }}/>
                <p className="optionText">Settings</p>
            </button>
            {/* <Icon style={{ width: 50, height: 50 }} /> */}
        </div>
    );

}

const mapStateToProps = ({ rRoute: { infobar }, rUser: { user } }) => ({ infobar, user });

export default connect(mapStateToProps)(Userhome);