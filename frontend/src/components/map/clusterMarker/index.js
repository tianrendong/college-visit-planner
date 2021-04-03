import React from 'react'
import RoomIcon from '@material-ui/icons/Room';
import './index.css'
import { ReactComponent as ClusterIcon } from '../../../assets/mapsSVG/flag.svg'

import { connect, useDispatch } from 'react-redux';

const ClusterMarker = (props) => {
    const dispatch = useDispatch();
    const { college = {},
        onClick = () => {},
        showInfo = () => { },
        index,
    } = props;
    
    const handleClickCluster = () => {
        console.log(props.index)
        dispatch({
            payload: {clusterIndex: props.index},
            type: 'EXPAND_CLUSTER',
        })
    }

    return (
        // <div>a</div>
        // <ClusterIcon style={{width: 40, height: 40}} onClick={handleClickCluster()}/>
        <ClusterIcon style={{width: 40, height: 40}} onClick={handleClickCluster}/>
    )
}

export default ClusterMarker;