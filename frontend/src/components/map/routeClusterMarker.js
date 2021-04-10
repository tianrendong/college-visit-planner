import React from 'react'
import './index.css'
import { connect, useDispatch } from 'react-redux';
import { ReactComponent as ClusterIcon } from '../../assets/mapsSVG/flag.svg'

const RouteClusterMarker = (props) => {
    const dispatch = useDispatch();
    const { index,
    } = props;
    
    const handleClickCluster = () => {
        if (props.routesUpdated.includes(props.index)) {{
            console.log("heyy")
            dispatch({
                payload: {
                    clusterIndex: props.index,
                },
                type: 'UPDATE_ROUTE'
            })
        }} else {
            console.log("bbbb")
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
    ({ rUser: { user, route, clusterUpdated, routesUpdated } }) => 
    ({ user, route, clusterUpdated, routesUpdated });

export default connect(mapStateToProps)(RouteClusterMarker);