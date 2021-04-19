import React, { useEffect, useState, useRef } from 'react'
import './index.css'
import { connect } from 'react-redux'
import Typography from '@material-ui/core/Typography';
import NavigateNextIcon from '@material-ui/icons/NavigateNext';
import IconButton from "@material-ui/core/IconButton";
import Tooltip from '@material-ui/core/Tooltip';
import DriveEtaIcon from '@material-ui/icons/DriveEta';
import LocalAirportIcon from '@material-ui/icons/LocalAirport';
import { useStyles } from './styles'
import { timeForHumans, distanceForHumans } from './timeDistanceConverter'

const RouteInfo = (props) => {

    const currentCluster = () => Object.values(Object.values(props.route)[props.selectedCluster])
    const [directionBoxes, setDirectionBoxes] = useState([]);
    const [display, setDisplay] = useState([])
    const travelDist = useRef(0); // in meters
    const travelTime = useRef(0); // in seconds
    
    useEffect(() => {
        setDirectionBoxes([]);
        setDisplay([]);
        travelDist.current = 0;
        travelTime.current = 0;
    },
    [props.route, props.selectedCluster])

    const renderRoutes = async (cluster) => {
        const start = new window.google.maps.LatLng(cluster[0].lat, cluster[0].lon);
        const end = new window.google.maps.LatLng(cluster[cluster.length - 1].lat, cluster[cluster.length - 1].lon);
        const waypts = [];
        for (let i = 1; i < currentCluster().length - 1; i++) {
            waypts.push({
                location: new window.google.maps.LatLng(currentCluster()[i].lat, currentCluster()[i].lon),
                stopover: true,
            });
        }
        const routes = await calculateRoute(start, end, waypts).then(res => res)
        
        for (let i = 0; i < routes.length; i++) {
            travelDist.current += routes[i].distance.value;
            travelTime.current += routes[i].duration.value;
        } 
        const routesDisplay = await routes.map(r => <DirectionBox info={r} />)
        setDirectionBoxes(routesDisplay)
    }


    useEffect(() => {
        renderRoutes(currentCluster())
    }, [props.route])

    useEffect(() => {
        if (directionBoxes.length !== 0) {
            for (let i = 0; i < currentCluster().length; i++) {
                const college = <LocationBox location={currentCluster()[i]} />
                setDisplay(display => [...display, college])
                if (i < currentCluster().length - 1) {
                    setDisplay(display => [...display, directionBoxes[i]])
                }
            }
        }
    }, [directionBoxes])

    return (
        <div className="routeInfoContainer">
            <div className="sidebarHeader">
                <h1>Route Information</h1>
            </div>

            <div className="sidebarHeader">
                <Typography>Total travel distance: {distanceForHumans(travelDist.current)}</Typography>
                <Typography>Total travel time: {timeForHumans(travelTime.current)}</Typography>
            </div>

            {props.selectedCluster !== '' && <div>{display}</div>}

            <div style={{height: '50px'}}/>
        </div>
    );
}

function calculateRoute(start, end, waypts) {
    const directionsService = new window.google.maps.DirectionsService();
    return new Promise((resolve, reject) => directionsService.route({
        origin: start,
        destination: end,
        waypoints: waypts,
        optimizeWaypoints: false,
        travelMode: window.google.maps.TravelMode.DRIVING,
    }, (response, status) => {
        if (status === window.google.maps.DirectionsStatus.OK) {
            resolve(response.routes[0].legs)
        } else {
            reject(`error fetching directions ${response}`)
            console.error(`error fetching directions ${response}`);
        }
    }));
}


const LocationBox = (props) => {
    const { location } = props;
    const classes = useStyles();
    return (
        <div className="collegeCardContainer">
            <div className="collegeCardInnerContainer">
            <Typography className={classes.collegeName}>{location.name} </Typography>
                <Typography className={classes.state}>
                    {location.content.city}, {location.content.state}
                </Typography>
            </div>
            { (location.type === "airport") ? <LocalAirportIcon fontSize="middle" className={classes.icon}/> : <></> }
        </div>

    )
}

const DirectionBox = (props) => {
    const classes = useStyles()
    const { info } = props;
    const startAddress = info.start_address.replace(/\s/g, '+');
    const endAddress = info.end_address.replace(/\s/g, '+');
    const GMapUrl = "https://www.google.com/maps/dir/?api=1&origin=" + startAddress + "&destination=" + endAddress + "&travelmode=driving"

    return (
        <div className="separatorContainer">
            <div className="infoContainer">
                <DriveEtaIcon className="infoText" style={{ marginRight:'15px'}} />
                <div className="infoText">{info.distance.text} &nbsp; &bull; &nbsp; {info.duration.text}</div>
            </div>

            <Tooltip title="Navigate using Google Maps" placement="right">
                <IconButton size="large" href={GMapUrl} target="_blank" style={{padding:'8px'}}>
                    <NavigateNextIcon fontSize="small" classes={classes.navigateGPS} />
                </IconButton>
            </Tooltip>
        </div>
    )
}

const mapStateToProps = ({ rUser: { user, route }, rMap: { selectedCluster } }) => ({ user, route, selectedCluster });

export default connect(mapStateToProps)(RouteInfo);