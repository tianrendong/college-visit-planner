import React, {useEffect, useState} from 'react'
import './index.css'
import {connect} from 'react-redux'
import Typography from '@material-ui/core/Typography';
import Divider from "@material-ui/core/Divider";
import List from "@material-ui/core/List";
import {ListItem, ListItemText, makeStyles} from "@material-ui/core";
// import { calculateRoute } from "../../map/directionsRenderer";

const RouteInfo = (props) => {

    const route = () => Object.values(Object.values(props.user.route)[props.selectedCluster])

    const useStyles = makeStyles((theme) => ({
        root: {
            width: '100%',
            maxWidth: '36ch',
            backgroundColor: theme.palette.background.paper,
        },
        inline: {
            display: 'inline',
        },
    }));

    const classes = useStyles()

    let [result, setResult] = useState([])


    useEffect(() => {
        console.log("useEffect")
        for (let i = 0; i < route().length; i++) {
            console.log("running")
            const college =
                <ListItem alignItems="flex-start">
                    <ListItemText
                        primary={route()[i].name}
                        secondary={
                            <React.Fragment>
                                <Typography
                                    component="span"
                                    variant="body2"
                                    className={classes.inline}
                                    color="textPrimary"
                                >
                                    City, State
                                </Typography>
                            </React.Fragment>
                        }
                    />
                </ListItem>
            setResult(result => [...result, college])
            console.log(college)
            if (i < route().length -1) {
                const start = route()[i]
                const end = route()[i + 1]
                const routeInfo = async() => {
                    // const result = await calculateRoute(start.lat, start.lon, end.lat, end.lon);
                    // return result;
                    const r = await calcRoute(start.lat, start.lon, end.lat, end.lon).then((res) => res[0])
                    console.log(r)
                    const between =
                        <ListItem alignItems="flex-start">
                            <ListItemText/>
                            {/*primary={routeInfo[0].distance.text}/>*/}
                        </ListItem>
                    setResult(result => [...result, between])
                    console.log(between)
                }
                routeInfo()
            }
            setResult(result => [...result, <Divider variant="middle"/>])
        }
    }, [props.user.route])

    return (
        <div>
            <Typography className="routeInfoTitle" component="h1" variant="h5">
                Nearby Airports
            </Typography>
            <Divider variant="middle"/>
            <Typography className="routeInfoTitle" component="h1" variant="h5">
                Colleges on this Route
            </Typography>
            {props.selectedCluster !== '' &&
            <List className={classes.root}>
                {result}
            </List>}
        </div>
    );
}


function calcRoute(startLat, startLon, endLat, endLon) {
    const directionsService = new window.google.maps.DirectionsService();
    const start = new window.google.maps.LatLng(startLat, startLon);
    const end = new window.google.maps.LatLng(endLat, endLon);
    // let result = 0
    return new Promise((resolve, reject) => {directionsService.route({
        origin: start,
        destination: end,
        travelMode: window.google.maps.TravelMode.DRIVING,
    }, (response, status) => {
        if (status === window.google.maps.DirectionsStatus.OK) {
            console.log(typeof(response.routes[0].legs))
            resolve(response.routes[0].legs)
        } else {
            reject(`error fetching directions ${response}`)
            console.error(`error fetching directions ${response}`);
        }
    });
        // return result
    })};

async function calculateRoute(startLat, startLon, endLat, endLon) {
    await calcRoute(startLat, startLon, endLat, endLon).then((res) => res.json())
}


const mapStateToProps = ({rUser: {user}, rMap: {selectedCluster}}) => ({user, selectedCluster});

export default connect(mapStateToProps)(RouteInfo);