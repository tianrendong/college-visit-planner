import React, { useState, useEffect } from "react";
import { connect, useDispatch } from 'react-redux';
import IconButton from '@material-ui/core/IconButton';
import CachedIcon from '@material-ui/icons/Cached';
import BookmarkBorderIcon from '@material-ui/icons/BookmarkBorder';
import BookmarkIcon from '@material-ui/icons/Bookmark';
import { withStyles, makeStyles } from '@material-ui/core/styles';
import Tooltip from '@material-ui/core/Tooltip';
import { routeActions } from '../../actions/routeActions'
import VisibilityIcon from '@material-ui/icons/Visibility';
import VisibilityOffIcon from '@material-ui/icons/VisibilityOff';


const MyTooltip = withStyles(() => ({
    tooltip: {
        maxWidth: 200,
        fontSize: '15px',
        padding: '10px 15px',
        marginTop: '10px'
    },
}))(Tooltip);

const reloadTooltipText = "Refresh if the route is not optimal, or bookmark to remember it"

const RouteReloader = (props) => {
    const dispatch = useDispatch();
    const [reloadTooltip, setReloadTooltip] = useState(false);
    const [routeTooltip, setRouteTooltip] = useState(false);


    const handleReloadRoute = () => {
        const currentCluster = props.route[props.selectedCluster];
        if (!props.tooltip.includes("bookmark")) {
            dispatch(routeActions.addTooltipShowed("bookmark"))
          }
        dispatch({
            payload: {
                clusterIndex: props.selectedCluster,
                colleges: currentCluster.map(c => c.content).splice(1, currentCluster.length),
            },
            type: 'REQUEST_UPDATE_ROUTE'
        })
    }

    const toggleBookmark = () => {
        if (!props.tooltip.includes("bookmark")) {
            dispatch(routeActions.addTooltipShowed("bookmark"))
          }
        dispatch({
            payload: {
                clusterIndex: props.selectedCluster,
            },
            type: 'TOGGLE_BOOKMARK_ROUTE'
        })
    }

    const toggleShowRoute = () => {
        if (!props.tooltip.includes("bookmark")) {
            dispatch(routeActions.addTooltipShowed("bookmark"))
          }
        dispatch({
            type: 'TOGGLE_SHOW_ROUTE'
        })
    }

    useEffect(() => {
        setReloadTooltip(!props.tooltip.includes("bookmark") && !props.updatingRoute);
    }, [props.updatingRoute, props.tooltip])

    const handleCloseReloadTooltip = () => {
        setReloadTooltip(false);
    };

    const handleShowReloadTooltip = () => {
        setReloadTooltip(true);
    };

    const handleCloseRouteTooltip = () => {
        setRouteTooltip(false);
    };

    const handleShowRouteTooltip = () => {
        setRouteTooltip(true);
    };

    return (
        <div>
            <MyTooltip open={routeTooltip} onOpen={handleShowRouteTooltip} onClose={handleCloseRouteTooltip}
                title={props.showRoute ? "hide route" : "show route"} placement="top">
                <IconButton size="large" onClick={toggleShowRoute}>
                    {props.showRoute ?
                        <VisibilityOffIcon fontSize="large"/> : <VisibilityIcon fontSize="large"/>}
                </IconButton>
            </MyTooltip>

            <IconButton size="large" onClick={handleReloadRoute} onMouseOver={handleShowReloadTooltip}
                onMouseLeave={handleCloseReloadTooltip}
                disabled={props.routesUpdated.includes(props.selectedCluster)}>
                <CachedIcon fontSize="large" />
            </IconButton>

            <MyTooltip open={reloadTooltip} onOpen={handleShowReloadTooltip} onClose={handleCloseReloadTooltip}
                title={reloadTooltipText} placement="right-end">
                <IconButton size="large" onClick={toggleBookmark}>
                    {props.routesUpdated.includes(props.selectedCluster) ?
                        <BookmarkIcon fontSize="large" /> :
                        <BookmarkBorderIcon fontSize="large" />}
                </IconButton>
            </MyTooltip>

        </div>
    )
}

const mapStateToProps = ({ rMap: { selectedCluster, viewport, showRoute },
    rUser: { route, routesUpdated, updatingRoute }, rRoute: { tooltip } }) =>
    ({ selectedCluster, viewport, showRoute, route, routesUpdated, updatingRoute, tooltip });

export default connect(mapStateToProps)(RouteReloader);
