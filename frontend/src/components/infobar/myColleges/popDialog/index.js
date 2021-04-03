import React from 'react';
import { connect } from 'react-redux'
import Button from '@material-ui/core/Button';
import Dialog from '@material-ui/core/Dialog';
import DialogActions from '@material-ui/core/DialogActions';
import DialogContent from '@material-ui/core/DialogContent';
import DialogContentText from '@material-ui/core/DialogContentText';
import DialogTitle from '@material-ui/core/DialogTitle';
import SearchCollege from './searchCollege/index'
import './index.css'

const routes = {
    'searchCollege': <SearchCollege />,
}

const PopDialog = (props) => {
    const {open, handleClose = () => {}} = props;

    return (
        <div>
            <Dialog open={open} onClose={handleClose}>
                <div className="popDialogContainer">
                {routes[props.popDialog]}
                </div>
            </Dialog>
        </div>
    );
}

const mapStateToProps = ({ rRoute: { popDialog } }) => ({ popDialog });

export default connect(mapStateToProps)(PopDialog);

