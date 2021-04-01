import './index.css'
import { connect } from 'react-redux'
import Typography from '@material-ui/core/Typography';

const RouteInfo = (props) => {

    const route = Object.values(Object.values(props.user.route)[props.selectedCluster])

    return (
        <div>
            <Typography className="routeInfoTitle" component="h1" variant="h5">
                    Nearby Airports
            </Typography>
            <Typography className="routeInfoTitle" component="h1" variant="h5">
                    Colleges on this Route
                </Typography>
            {route.map(college => (
            <div>{college.name}</div>))}
        </div>
    );
}


const mapStateToProps = ({ rUser: { user }, rMap: { selectedCluster } }) => ({ user, selectedCluster });

export default connect(mapStateToProps)(RouteInfo);