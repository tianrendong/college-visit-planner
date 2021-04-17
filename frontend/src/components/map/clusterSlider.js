import React, { useState, useEffect } from 'react';
import { connect, useDispatch } from 'react-redux';
import { withStyles, makeStyles } from '@material-ui/core/styles';
import Slider from '@material-ui/core/Slider';
import Tooltip from '@material-ui/core/Tooltip';
import { routeActions } from '../../actions/routeActions'

const MySlider = withStyles({
  root: {
    color: '#309157',
    height: 8,
  },
  thumb: {
    height: 24,
    width: 24,
    backgroundColor: '#fff',
    border: '2px solid currentColor',
    marginTop: -8,
    marginLeft: -12,
    '&:focus, &:hover, &$active': {
      boxShadow: 'inherit',
    },
  },
  active: {},
  valueLabel: {
    left: 'calc(-50% + 4px)',
  },
  track: {
    height: 8,
    borderRadius: 4,
  },
  rail: {
    height: 8,
    borderRadius: 4,
  },
})(Slider);

const MyTooltip = withStyles(() => ({
  tooltip: {
    maxWidth: 200,
    fontSize: '15px',
    padding: '10px 15px',
    marginTop: '10px'
  },
}))(Tooltip);

const tooltip = "Click around to see clusters of different radius (in km)"

const ClusterSlider = (props) => {
  const dispatch = useDispatch();
  const [showTooltip, setShowToolTip] = useState(false);

  useEffect(() => {
    setShowToolTip(!props.tooltip.includes("slider") && !props.updatingRoute);
  }, [props.updatingRoute, props.tooltip])

  const handleCloseTooltip = () => {
    setShowToolTip(false);
  };

  const handleShowTooltip = () => {
    setShowToolTip(true);
  };

  const handleSliderChange = (event, value) => {
    if (!props.tooltip.includes("slider")) {
      dispatch(routeActions.addTooltipShowed("slider"))
    }
    dispatch({
      payload: {
        colleges: props.user.colleges,
        radius: value,
      },
      type: 'REQUEST_UPDATE_CLUSTERS'
    });
  }

  return (
    <MyTooltip open={showTooltip} onOpen={handleShowTooltip} onClose={handleCloseTooltip}
      title={tooltip} placement="right-end">
      <div className="sliderContainer">
        <MySlider
          valueLabelDisplay="auto"
          step={100} default={props.sliderValue} min={350} max={550}
          value={props.sliderValue} 
          onChangeCommitted={handleSliderChange} />
      </div>
    </MyTooltip>

  )
}

const mapStateToProps = ({ rUser: { user, updatingRoute }, rRoute: { tooltip }, rMap: { sliderValue } }) =>
  ({ user, updatingRoute, tooltip, sliderValue });

export default connect(mapStateToProps)(ClusterSlider);