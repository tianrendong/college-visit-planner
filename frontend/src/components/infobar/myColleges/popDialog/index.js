import React from 'react';
import { connect, useDispatch } from 'react-redux'
import { makeStyles } from '@material-ui/core/styles';
import Button from '@material-ui/core/Button';
import Dialog from '@material-ui/core/Dialog';
import DialogActions from '@material-ui/core/DialogActions';
import DialogContent from '@material-ui/core/DialogContent';
import DialogContentText from '@material-ui/core/DialogContentText';
import DialogTitle from '@material-ui/core/DialogTitle';
import SearchCollege from './searchCollege/index'
import './index.css'
import { routeActions } from '../../../../actions/routeActions'

const routes = {
    'searchCollege': <SearchCollege />,
}

const useStyles = makeStyles((theme) => ({
    paper: {
        width: '40vw',
        overflowX: 'hidden',
    },
}));

const PopDialog = (props) => {
    const classes = useStyles();
    const dispatch = useDispatch();

    const handleClose = () => {
        dispatch(routeActions.navigatePopDialog(''));
    }

    return (
        <Dialog open={props.popDialog !== ''} onClose={handleClose}
            classes={{ paper: classes.paper }}>
            <div className="popDialogContainer">
                {routes[props.popDialog]}
            </div>
            <DialogActions className="dialogBottom">
                <Button onClick={handleClose} color="primary">Cancel</Button>
            </DialogActions>
        </Dialog>
    );
}
const mapStateToProps = ({ rRoute: { popDialog } }) => ({ popDialog });

export default connect(mapStateToProps)(PopDialog);

