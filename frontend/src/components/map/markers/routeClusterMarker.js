import React from 'react'
import '../index.css'
import { connect, useDispatch } from 'react-redux';
import { ReactComponent as ClusterIcon } from '../../../assets/mapsSVG/flag.svg'
import { routeActions } from '../../../actions/routeActions'
import PropTypes from "prop-types";

/**
 * Marker for the clusters in route view.
 */
const RouteClusterMarker = (props) => {
    const dispatch = useDispatch();
    const { index } = props;
    
    const handleClickCluster = () => {
        if (!props.tooltip.includes("clusters")) {
            dispatch(routeActions.addTooltipShowed("clusters"))
        }
        if (props.routesUpdated.includes(props.index)) {{
            dispatch({
                payload: {
                    clusterIndex: props.index,
                },
                type: 'UPDATE_ROUTE'
            })
        }} else {
            console.log("aa")
            const currentClusterItems = props.route[props.index];
            let colleges = currentClusterItems.filter((loc) => loc.type !== 'airport');
            colleges = colleges.map(c => c.hasOwnProperty("content") ? c.content : c);
            dispatch({
                payload: {
                    clusterIndex: props.index,
                    colleges: colleges,
                },
                type: 'REQUEST_UPDATE_ROUTE'
            })
        }
    }

    return (
        <ClusterIcon style={{width: 40, height: 40}} onClick={handleClickCluster}/>
    )
}

RouteClusterMarker.propTypes = {
    index: PropTypes.number, // clusterIndex
}

const mapStateToProps =
    ({ rUser: { user, route, clusterUpdated, routesUpdated }, rRoute: { tooltip } }) => 
    ({ user, route, clusterUpdated, routesUpdated, tooltip });

export default connect(mapStateToProps)(RouteClusterMarker);