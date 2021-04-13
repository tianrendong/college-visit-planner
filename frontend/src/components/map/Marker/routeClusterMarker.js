import React from 'react'
import '../index.css'
import { connect, useDispatch } from 'react-redux';
import { ReactComponent as ClusterIcon } from '../../../assets/mapsSVG/flag.svg'
import { routeActions } from '../../../actions/routeActions'

const RouteClusterMarker = (props) => {
    const dispatch = useDispatch();
    const { index,
    } = props;
    
    const handleClickCluster = () => {
        if (!props.tooltip.includes("clusters")) {
            dispatch(routeActions.addTooltipShowed("clusters"))
        }
        if (props.routesUpdated.includes(props.index)) {{
            console.log("bbbb")
            console.log(props.route)
            console.log(props.index)
            dispatch({
                payload: {
                    clusterIndex: props.index,
                },
                type: 'UPDATE_ROUTE'
            })
        }} else {
            dispatch({
                payload: {
                    clusterIndex: props.index,
                    colleges: props.route[props.index],
                },
                type: 'REQUEST_UPDATE_ROUTE'
            })
        }
    }

    return (
        <ClusterIcon style={{width: 40, height: 40}} onClick={handleClickCluster}/>
    )
}

const mapStateToProps =
    ({ rUser: { user, route, clusterUpdated, routesUpdated }, rRoute: { tooltip } }) => 
    ({ user, route, clusterUpdated, routesUpdated, tooltip });

export default connect(mapStateToProps)(RouteClusterMarker);