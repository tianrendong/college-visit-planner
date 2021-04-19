import React, { useEffect } from 'react';
import Typography from '@material-ui/core/Typography';
import './index.css'
import { makeStyles } from '@material-ui/core/styles';
import Button from '@material-ui/core/Button';
import { connect, useDispatch } from 'react-redux';
import AirportInfo from './airportInfo'
import { routeActions } from '../../../actions/routeActions' 
import { useSnackbar } from 'notistack';

const useStyles = makeStyles({
    title: {
        fontSize: 17,
    },
    subtitle: {
        fontSize: 17,
        margin: 0,
    },
    button: {
        margin: '0 8px'
    }
});

const CollegeInfo = (props) => {
    const classes = useStyles();
    const dispatch = useDispatch();
    const { enqueueSnackbar } = useSnackbar();

    const college = () => { return props.currentCollege.college }
    const inVisitList = () => { 
        return props.user.colleges.some(c => c.id === college().id);
    }

    const hasNearbyAirport = () => { return (props.currentCollege.nearbyAirport !== null) }
    const nearbyAirport = () => { return props.currentCollege.nearbyAirport }

    const hasNearbyColleges = () => { return (props.currentCollege.nearbyColleges !== null) }
    const nearbyColleges = () => { return props.currentCollege.nearbyColleges }

    const handleAddDeleteCollege = () => {
        if (props.loggedIn === true) {
            if (inVisitList()) {
                dispatch({
                    payload: {
                        username: props.user.username,
                        collegeID: college().id
                    },
                    type: "REQUEST_DELETE_COLLEGE"
                })
            } else {
                dispatch({
                    payload: {
                        username: props.user.username,
                        collegeID: college().id
                    },
                    type: "REQUEST_ADD_COLLEGE"
                })
            }
        } else {
            enqueueSnackbar('Please log in first', {variant: 'error'});
        }
    }

    useEffect(() => {
        (props.successMessage !== '') && enqueueSnackbar(props.successMessage, {variant: 'success'});   
    }, [props.successMessage])

    return (
        <div className="collegeInfoContainer">
            <div className="sidebarHeader">
                <Typography className={classes.title} color="textSecondary" gutterBottom>
                    College Information
                </Typography>
                <h1>{college().name}</h1>
                <Typography className={classes.subtitle} color="textSecondary" gutterBottom>
                    {college().city}, {college().state}
                </Typography>
            </div>

            <div className="collegeInfoButtonContainer"> 
                <Button size="small" href={college().url} target="_blank" className={classes.button}>Visit Website</Button>
                
                <Button size="small" onClick={handleAddDeleteCollege} className={classes.button}>
                    { (props.loggedIn && inVisitList()) ?  "Remove from visit list" : "Add to visit list"}
                    </Button>
            </div>

            <div className="collegeInfoContent"> 
                <Typography variant="body2" color="textSecondary" component="p">
                    {college().description}
                </Typography>
                
            </div>
            
            {hasNearbyAirport() && 
            <>
                <div className="sidebarHeader">
                    <Typography className={classes.subtitle} color="textSecondary" gutterBottom>
                    Nearby Airports
                    </Typography>
                </div>
                <AirportInfo airport={nearbyAirport()}/>
                </>
            }

            {hasNearbyColleges() && 
            <>
            <div className="sidebarHeader">
                <Typography className={classes.subtitle} color="textSecondary" gutterBottom>
                    Nearby Colleges
                </Typography>
            </div>
            {nearbyColleges().map((c, index) => (index < 3) && <AirportInfo airport={c}/>)}
            </>}

            <div style={{height: '30px'}}/>

            
        </div>
    );

}

const mapStateToProps = ({ rUser: { user, loggedIn }, rRoute: { error, currentCollege, successMessage } }) => 
({ user, loggedIn, error, currentCollege, successMessage });

export default connect(mapStateToProps)(CollegeInfo);
