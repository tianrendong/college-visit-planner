import React from 'react';
import './index.css'

/**
 * Component for cluster marker on initial map view.
 */
const ClusterMarker = (props) => {
  const { index, points } = props;

  return (
      <div class="outerCircle">
        <div class="innerCircle">{points.length}</div>
      </div>
  )
}

export default ClusterMarker;
