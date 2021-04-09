import React from 'react';
import PropTypes from 'prop-types';
import { List } from 'immutable';
import { ReactComponent as ClusterIcon } from '../../../assets/mapsSVG/placeholder.svg'

class ClusterMarker extends React.PureComponent {
  // eslint-disable-line react/prefer-stateless-function
  state = {
    clusterFaceMarkers: this.props.points.slice(0, 1),
  };

  render() {
    return (
      <div className="clusterMarkerContainer">

        {this.state.clusterFaceMarkers.map(marker =>
          // <ClusterIcon className="collegeMarkerDefault"
          //   key={marker.id}
          //   lat={marker.lat}
          //   lng={marker.lng}
          // />
          "bbb"
        )}
        <div className="collegeClusterLabel">{this.props.points.length}</div>
      </div>
    );
  }
}

ClusterMarker.propTypes = {
  points: PropTypes.array,
  users: PropTypes.instanceOf(List),
  selected: PropTypes.bool,
};

export default ClusterMarker;
