import React, { useEffect } from 'react';
import './index.css'
import Typography from '@material-ui/core/Typography';
// import SettingsIcon from '@material-ui/icons/Settings';
import ExitToAppIcon from '@material-ui/icons/ExitToApp';
import Button from '@material-ui/core/Button';
import { routeActions } from '../../../actions/routeActions';
import { connect, useDispatch } from 'react-redux';
import { useSnackbar } from 'notistack';
import { ReactComponent as RouteIcon } from '../../../assets/mapsSVG/route.svg'
import { ReactComponent as MyCollegeIcon } from '../../../assets/mapsSVG/school.svg'
import { ReactComponent as SettingsIcon } from '../../../assets/mapsSVG/settings.svg'


const Userhome = (props) => {

    const dispatch = useDispatch();
    const { enqueueSnackbar } = useSnackbar();

    useEffect(() => {
        console.log(props.error);
        if (props.error !== '') {
            enqueueSnackbar(props.error, {variant: 'error'});
        }
    }, [props.error])

    const handleNavigateColleges = () => {
        if (props.infobar === 'myColleges') {
            dispatch(routeActions.navigateInfobar(''));
        } else {
            dispatch(routeActions.navigateInfobar("myColleges"));
        }

    }

    const handleNavigateRoute = () => {
        dispatch({
            type: 'REQUEST_UPDATE_ROUTE',
        })
    }

    const handleNavigateSettings = () => {
        if (props.infobar === 'settings') {
            dispatch(routeActions.navigateInfobar(''));
        } else {
            dispatch(routeActions.navigateInfobar('settings'));
        } 
    }

    return (
        <div className="userContainer">
            <div className="sidebarHeader">
                <h1 className="sidebarTitle">Welcome,</h1>
                <h1 className="sidebarTitle Name">{props.user.firstname}!</h1>
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

const mapStateToProps = ({ rRoute: { infobar }, rUser: { user, error } }) => ({ infobar, user, error });

export default connect(mapStateToProps)(Userhome);