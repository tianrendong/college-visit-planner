import React, { useEffect } from 'react';
import Typography from '@material-ui/core/Typography';
import './index.css'
import { makeStyles } from '@material-ui/core/styles';
import Button from '@material-ui/core/Button';
import { connect, useDispatch } from 'react-redux';
import AirportInfo from './airportInfo'

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

    console.log(props.markerClicked.airport)
    console.log(typeof(props.markerClicked.airport))

    const college = () => {return props.markerClicked.content}
    const hasNearbyAirport = () => { return (typeof(props.markerClicked.airport) !== 'undefined') }
    const nearbyAirport = () => { return props.markerClicked.airport }

    const hasNearbyColleges = () => { return (typeof(props.markerClicked.nearbyColleges) !== 'undefined') }
    const nearbyColleges = () => { return props.markerClicked.nearbyColleges }

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
                <Button size="small" href={college().url} target="_blank">Visit Website</Button>
            </div>
            
            {hasNearbyAirport() && 
            <>
                <div className="sidebarHeader">
                    <Typography className={classes.title} color="textSecondary" gutterBottom>
                    Nearby Airports
                    </Typography>
                </div>
                <AirportInfo airport={nearbyAirport()}/>
                </>
            }

            <div className="sidebarHeader">
                <Typography className={classes.title} color="textSecondary" gutterBottom>
                    Nearby Colleges
                </Typography>
            </div>

            {hasNearbyColleges() && nearbyColleges().map(c => <AirportInfo airport={c}/>)}
        </div>
    );

}

const mapStateToProps = ({ rUser: { user, error }, rMap: { markerClicked } }) => ({ user, error, markerClicked });

export default connect(mapStateToProps)(CollegeInfo);
