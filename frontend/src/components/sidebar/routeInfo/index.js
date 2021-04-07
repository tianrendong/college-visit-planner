import React, { useEffect, useState } from 'react'
import './index.css'
import { connect } from 'react-redux'
import Typography from '@material-ui/core/Typography';
import { makeStyles } from "@material-ui/core";
import IconButton from '@material-ui/core/IconButton';
import NavigateNextIcon from '@material-ui/icons/NavigateNext';
import { ReactComponent as DirectionsIcon } from '../../../assets/mapsSVG/turn.svg'
// import { calculateRoute } from "../../map/directionsRenderer";

const useStyles = makeStyles((theme) => ({
    state: {
        fontSize: 19,
    }, 
    cardRoot: {
        minWidth: 275,
        margin: '10px 20px 15px 0',
        width: '100%',
    },
    cardContentRoot: {
        backgroundColor: '#fffefc',
    },
    navigateIcon: {
        margin: '3px',
    }
}));

const RouteInfo = (props) => {

    const classes = useStyles()
    const currentCluster = () => Object.values(Object.values(props.user.route)[props.selectedCluster])
    const [directionBoxes, setDirectionBoxes] = useState([]);
    const [display, setDisplay] = useState([])

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
        console.log(routes);
        const routesDisplay = await routes.map(r => <DirectionBox info={r} />)
        setDirectionBoxes(routesDisplay)
    }

    useEffect(() => { renderRoutes(currentCluster()) }, [props.user.route])

    useEffect(() => {
        if (directionBoxes.length !== 0) {
            for (let i = 0; i < currentCluster().length; i++) {
                console.log("c")
                const college = <CollegeBox college={currentCluster()[i]} />
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
                <h1 className="sidebarTitle">Route Information</h1>
            </div>
            {props.selectedCluster !== '' &&
                <div>{display}</div>}
            <div className="sidebarHeader">
                <Typography className={classes.title} gutterBottom>
                    Nearby Airports
                </Typography>
                {/* <h1 className="sidebarTitle">{college().name}</h1> */}
            </div>
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

const CollegeBox = (props) => {
    const { college } = props;
    const classes = useStyles();

    return (
        <div className="collegeCardContainer">
            <div className="collegeCardInnerContainer">
            <div className="collegeName">{college.name}</div>
            <Typography className={classes.state}>
                    city, state
            </Typography>
            </div>
            <IconButton size="large">
                        <NavigateNextIcon fontSize="middle" classes={classes.navigateIcon}/>
            </IconButton> 
        </div>

    )
}

const DirectionBox = (props) => {
    const { info} = props;
    const label = info.distance.text + " * " + info.duration.text
    return (
            <div class="separator">{label}</div>
    )
}


const mapStateToProps = ({ rUser: { user}, rMap: { selectedCluster} }) => ({ user, selectedCluster});

export default connect(mapStateToProps)(RouteInfo);