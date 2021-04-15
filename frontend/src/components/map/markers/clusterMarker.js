import React from 'react';
import clusterIcon from '../../../assets/mapsSVG/cluster.png'

class ClusterMarker extends React.PureComponent {
  state = {
    clusterFaceMarkers: this.props.points.slice(0, 1),
  };

  render() {
    return (
      <div className="clusterMarkerContainer">

        {this.state.clusterFaceMarkers.map(marker =>
          <clusterIcon className="collegeMarkerDefault"
            key={marker.id}
            lat={marker.lat}
            lng={marker.lng}
          />
          // "bbb"
        )}
        {/* <div className="collegeClusterLabel">{this.props.points.length}</div> */}
      </div>
    );
  }
}

export default ClusterMarker;
