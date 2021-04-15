
import React from 'react';
import { connect, useDispatch } from 'react-redux';
import PropTypes from 'prop-types';
import { routeActions } from '../../../actions/routeActions'
import './index.css'
import { ReactComponent as College1 } from '../../../assets/collegeSVG/college1.svg'
import { ReactComponent as College2 } from '../../../assets/collegeSVG/college2.svg'
import { ReactComponent as College3 } from '../../../assets/collegeSVG/college3.svg'
import { ReactComponent as College4 } from '../../../assets/collegeSVG/college4.svg'
import { ReactComponent as College5 } from '../../../assets/collegeSVG/college5.svg'
import { ReactComponent as College6 } from '../../../assets/collegeSVG/college6.svg'


const CollegeMarker = (props) => {
    const dispatch = useDispatch();
    const { collegeID } = props;

    const clicked = () => {
        return ( props.currentCollege !== {} ) && (props.currentCollege.id === collegeID)
    };

    const icons = [
        <College1 className="collegeMarkerDefault"/>, 
        // <College1 className={clicked() ? "collegeMarkerOnClick" : "collegeMarkerDefault"}/>, 
        <College2 className="collegeMarkerDefault"/>, 
        <College3 className="collegeMarkerDefault"/>, 
        <College4 className="collegeMarkerDefault"/>, 
        <College5 className="collegeMarkerDefault"/>, 
        <College6 className="collegeMarkerDefault"/>]

    const getIcon = (i) => { return icons[i % icons.length]}

    const handleClick = () => {
        if (!props.tooltip.includes("default")) {
            dispatch(routeActions.addTooltipShowed("default"))
        }
        dispatch({
            payload: collegeID,
            type: 'REQUEST_COLLEGE_INFO'
        })
    }

    return (
        <div className="collegeMarkerContainer" onClick={handleClick}>
            <div>{getIcon(collegeID)}</div>
            {/* <div className={ clicked() ? "collegeMarkerLabelClicked" : "collegeMarkerLabel"}>{props.currentCollege.name}</div> */}
        </div>
    )
}

CollegeMarker.propTypes = {
    collegeID: PropTypes.number,
}

const mapStateToProps = ({ rRoute: { currentCollege, tooltip } }) => ({ currentCollege, tooltip });

export default connect(mapStateToProps)(CollegeMarker);