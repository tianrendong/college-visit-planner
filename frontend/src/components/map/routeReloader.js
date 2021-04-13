import React, { useState, useEffect } from "react";
import { connect, useDispatch } from 'react-redux';
import IconButton from '@material-ui/core/IconButton';
import CachedIcon from '@material-ui/icons/Cached';
import BookmarkBorderIcon from '@material-ui/icons/BookmarkBorder';
import BookmarkIcon from '@material-ui/icons/Bookmark';
import { withStyles, makeStyles } from '@material-ui/core/styles';
import Tooltip from '@material-ui/core/Tooltip';
import { routeActions } from '../../actions/routeActions'


const MyTooltip = withStyles(() => ({
    tooltip: {
        maxWidth: 200,
        fontSize: '15px',
        padding: '10px 15px',
        marginTop: '10px'
    },
}))(Tooltip);

const tooltip = "Refresh if the route is not optimal, or bookmark to remember it"

const RouteReloader = (props) => {
    const dispatch = useDispatch();
    const [showTooltip, setShowToolTip] = useState(false);

    const handleReloadRoute = () => {
        const currentCluster = props.route[props.selectedCluster];
        // if (!props.tooltip.includes("slider")) {
        //     dispatch(routeActions.addTooltipShowed("bookmark"))
        //   }
        dispatch({
            payload: {
                clusterIndex: props.selectedCluster,
                colleges: currentCluster.map(c => c.content).splice(1, currentCluster.length),
            },
            type: 'REQUEST_UPDATE_ROUTE'
        })
    }

    const toggleBookmark = () => {
        // if (!props.tooltip.includes("slider")) {
        //     dispatch(routeActions.addTooltipShowed("bookmark"))
        //   }
        dispatch({
            payload: {
                clusterIndex: props.selectedCluster,
            },
            type: 'TOGGLE_BOOKMARK_ROUTE'
        })
    }

    useEffect(() => {
        setShowToolTip(!props.tooltip.includes("bookmark") && !props.updatingRoute);
    }, [props.updatingRoute, props.tooltip])

    const handleCloseTooltip = () => {
        setShowToolTip(false);
    };

    const handleShowTooltip = () => {
        setShowToolTip(true);
    };

    return (
        <div>
            <IconButton size="large" onClick={handleReloadRoute} onMouseOver={handleShowTooltip} onMouseLeave={handleCloseTooltip}
            disabled={props.routesUpdated.includes(props.selectedCluster)}>
                <CachedIcon fontSize="large" />
            </IconButton>
            <MyTooltip open={showTooltip} onOpen={handleShowTooltip} onClose={handleCloseTooltip}
                title={tooltip} placement="right-end">
                <IconButton size="large" onClick={toggleBookmark}>
                    {props.routesUpdated.includes(props.selectedCluster) ?
                        <BookmarkIcon fontSize="large" /> :
                        <BookmarkBorderIcon fontSize="large" />}
                </IconButton>
            </MyTooltip>

        </div>
    )
}

const mapStateToProps = ({ rMap: { selectedCluster, viewport },
    rUser: { route, routesUpdated, updatingRoute }, rRoute: { tooltip } }) =>
    ({ selectedCluster, viewport, route, routesUpdated, updatingRoute, tooltip });

export default connect(mapStateToProps)(RouteReloader);
