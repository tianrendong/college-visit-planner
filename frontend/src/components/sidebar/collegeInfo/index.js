import React, { useEffect } from 'react';
import Typography from '@material-ui/core/Typography';
import Divider from '@material-ui/core/Divider';
import './index.css'
import ExitToAppIcon from '@material-ui/icons/ExitToApp';
import { makeStyles } from '@material-ui/core/styles';
import Button from '@material-ui/core/Button';
import { routeActions } from '../../../actions/routeActions';
import { connect, useDispatch } from 'react-redux';
import { useSnackbar } from 'notistack';
import { ReactComponent as RouteIcon } from '../../../assets/mapsSVG/route.svg'
import { ReactComponent as MyCollegeIcon } from '../../../assets/mapsSVG/school.svg'
import { ReactComponent as SettingsIcon } from '../../../assets/mapsSVG/settings.svg'

const useStyles = makeStyles({
    title: {
        fontSize: 20,
    },
    state: {
        fontSize: 20,
        marginTop: '10px',
    }
});

const CollegeInfo = (props) => {
    const classes = useStyles();
    const dispatch = useDispatch();

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

    const college = () => {return props.markerClicked.content}

    return (
        <div className="collegeInfoContainer">
            <div className="sidebarHeader">
                <Typography className={classes.title} color="textSecondary" gutterBottom>
                    College Information
                </Typography>
                <h1 className="sidebarTitle">{college().name}</h1>
                <Typography className={classes.state} color="textSecondary" gutterBottom>
                    {college().city}, {college().state}
                </Typography>
            </div>

            <div className="collegeInfoContent"> 
                <Typography variant="body2" color="textSecondary" component="p">
                    {college().description}
                </Typography>
                
            </div>

            <div className="collegeInfoFooter">
                <Button size="small" href={college().url} target="_blank">Learn More</Button>
            </div>
            

            


        </div>
    );

}

const mapStateToProps = ({ rUser: { user, error }, rMap: { markerClicked } }) => ({ user, error, markerClicked });

export default connect(mapStateToProps)(CollegeInfo);
