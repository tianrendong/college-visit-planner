import React, { useEffect } from 'react';
import './index.css'
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
        if (props.error !== '') {
            enqueueSnackbar(props.error, { variant: 'error' });
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
        if (props.clusterUpdated === true) {
            dispatch({
                type: 'UPDATE_CLUSTERS',
            })
        } else {
            dispatch({
                payload: {
                    colleges: props.user.colleges,
                    radius: 350,
                },
                type: 'REQUEST_UPDATE_CLUSTERS',
            })
        }
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
                <h1 className="userHomeHeader">Welcome,</h1>
                <h1 className="Name userHomeHeader">{props.user.firstname} {props.user.lastname}!</h1>
            </div>
            <button className="optionButton" onClick={handleNavigateColleges}>
                <MyCollegeIcon className="userhomeIcons" style={{ width: 52, height: 52 }} />
                <p className="optionText" >Colleges</p>
            </button>
            <button className="optionButton" onClick={handleNavigateRoute}>
                <RouteIcon className="userhomeIcons" style={{ width: 47, height: 47 }} />
                <p className="optionText">Route</p>
                {/* {props.updatingRoute && <CircularProgress style={{ margin: 20, width: 30, height: 30 }} />} */}
            </button>
            <button className="optionButton" onClick={handleNavigateSettings}>
                <SettingsIcon className="userhomeIcons" style={{ width: 43, height: 43 }} />
                <p className="optionText">Settings</p>
            </button>
            <div style={{height: '5px'}}/>
        </div>
    );

}

const mapStateToProps =
    ({ rRoute: { infobar, error }, rUser: { user, updatingRoute, clusterUpdated } }) => 
    ({ error, infobar, user, updatingRoute, clusterUpdated });

export default connect(mapStateToProps)(Userhome);