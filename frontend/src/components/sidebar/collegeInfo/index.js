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

    const college = () => { return props.currentCollege.college }
    const hasNearbyAirport = () => { return (props.currentCollege.nearbyAirport !== null) }
    const nearbyAirport = () => { return props.currentCollege.nearbyAirport }

    const hasNearbyColleges = () => { return (props.currentCollege.nearbyColleges !== null) }
    const nearbyColleges = () => { return props.currentCollege.nearbyColleges }

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
            {nearbyColleges().map(c => <AirportInfo airport={c}/>)}
        </div>
    );

}

const mapStateToProps = ({ rUser: { user, error }, rRoute: { currentCollege } }) => ({ user, error, currentCollege });

export default connect(mapStateToProps)(CollegeInfo);
