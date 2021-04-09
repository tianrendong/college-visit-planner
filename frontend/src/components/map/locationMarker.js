
import React, { useState } from 'react'; 
import { connect, useDispatch } from 'react-redux';
import Infocard from './infocard.js'
import { routeActions } from '../../actions/routeActions'
import './index.css'
import { ReactComponent as LocationIcon } from '../../assets/mapsSVG/placeholder.svg'


const LocationMarker = (props) => {
    const dispatch = useDispatch();
    const { college = {},} = props;

    console.log(college);

    const clicked = () => {
        return (props.markerClicked.type === 'defaultMarker') && (props.markerClicked.content.id === college.id)
    };


    const handleClick = () => {
        // dispatch(mapActions.clickMarker('locationMarker', college))
        // dispatch(routeActions.navigateSidebar('collegeInfo'))
    }

    return (
        <div className="collegeMarkerContainer" onClick={handleClick}>
            <LocationIcon style={{width: '20px', height: '20px'}}/>
            <div className={ clicked() ? "collegeMarkerLabelClicked" : "collegeMarkerLabel"}>{college.name}</div>
        </div>
    )
}

const mapStateToProps = ({ rMap: { markerClicked} }) => ({ markerClicked });

export default connect(mapStateToProps)(LocationMarker);