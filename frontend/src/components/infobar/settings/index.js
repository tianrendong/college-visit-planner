import React, { useState } from 'react';
import { connect, useDispatch } from 'react-redux'
import { makeStyles } from '@material-ui/core/styles';
import { withStyles } from '@material-ui/core/styles';

import Checkbox from '@material-ui/core/Checkbox';
import FormControlLabel from '@material-ui/core/FormControlLabel';
import Typography from '@material-ui/core/Typography';
import ExpandMoreIcon from '@material-ui/icons/ExpandMore';
import Button from '@material-ui/core/Button';
import DeleteIcon from '@material-ui/icons/Delete';
import List from '@material-ui/core/List';
import ListItem from '@material-ui/core/ListItem';
import ListItemText from '@material-ui/core/ListItemText';
import Dialog from '@material-ui/core/Dialog';
import DialogActions from '@material-ui/core/DialogActions';
import DialogContent from '@material-ui/core/DialogContent';
import DialogContentText from '@material-ui/core/DialogContentText';
import DialogTitle from '@material-ui/core/DialogTitle';
import {dataPolicy} from './policies.js'
import Terms from '../../sidebar/signup/terms'
import './index.css'
import { Accordion, AccordionSummary, AccordionDetails } from './styles'

const useStyles = makeStyles(() => ({
    settingsControl: {
        padding: '0 16px'
    },
}));

const DIALOG_INFO = {
    'deleteData': {
        type: 'deleteData',
        title: 'Clear your data?',
        message: 'If you click confirm, we will delete all of your stored trip information from our database. You will not be able to recover from this action.',
    },
    'deleteAccount': {
        type: 'deleteAccount',
        title: 'Delete your account?',
        message: 'If you click confirm, we will remove your account and all associated data from our database. You will not be able to recover from this action.',
    },
}

function Settings(props) {
    const classes = useStyles();
    const dispatch = useDispatch();
    const [expanded, setExpanded] = useState([]);
    const [dialogOpen, setDialogOpen] = useState(false);
    const [dialogInfo, setDialogInfo] = useState({});

  const handleOpenDialog = (type) => {
    setDialogInfo(DIALOG_INFO[type]);
    setDialogOpen(true);
  };

  const handleCloseDialog = () => {
    setDialogOpen(false);
  };

    const handleExpand = (panel) => (event, isExpanded) => {
        setExpanded(isExpanded ? [...expanded, panel] : expanded.filter(item => item !== panel));
    };

    const handleLogOut = () => {
        dispatch({
            type: 'LOGOUT',
        })
    }

    const handleConfirm = () => {
        console.log(dialogInfo.type)
        if (dialogInfo.type === "deleteData") {
            dispatch({
                payload: props.user.username,
                type: 'REQUEST_DELETE_DATA'
            })
        }
        if (dialogInfo.type === "deleteAccount") {
            dispatch({
                payload: props.user.username,
                type: 'REQUEST_DELETE_ACCOUNT'
            }) 
        }
        setDialogOpen(false);
    }

    return (
        <div className="settingsContainer">
            <h1 className="leftPadd">Settings</h1>
            <div className="settingsInnerContainer">
                <Typography variant="h6" className="leftPadd">
                    About Our App
                </Typography>
                <div style={{marginLeft: '16px'}}>
                    {dataPolicy.aboutTheApp()}
                </div>
                {/* <Accordion square expanded={expanded.includes('panel1')} onChange={handleExpand('panel1')}>
                    <AccordionSummary aria-controls="panel1d-content" id="panel1d-header">
                        <Typography>How do we collect, use, and store your data?</Typography>
                    </AccordionSummary>
                    <AccordionDetails>
                        
                    </AccordionDetails>
                </Accordion> */}
                    
            </div>

            <div>
                <Typography variant="h6" className="leftPadd">
                    My Account
                </Typography>

                <List component="nav">
                    <ListItem
                        button
                        aria-haspopup="true"
                        aria-controls="lock-menu"
                        className="settingsControl"
                        onClick={() => handleOpenDialog('deleteData')}
                        classes={{root: classes.settingsControl}}
                    >
                        <ListItemText primary="Clear data" secondary="Clear all trip and collge information on your account" />
                    </ListItem>

                    <ListItem
                        button
                        aria-haspopup="true"
                        aria-controls="lock-menu"
                        onClick={() => handleOpenDialog('deleteAccount')}
                        classes={{root: classes.settingsControl}}
                    >
                        <ListItemText primary="Delete Account" secondary="Delete your entire account" />
                    </ListItem>

                    <ListItem
                        button
                        aria-haspopup="true"
                        aria-controls="lock-menu"
                        onClick={handleLogOut}
                        classes={{root: classes.settingsControl}}
                    >
                        <ListItemText primary="Log Out" secondary="Log out of current account" />
                    </ListItem>
                </List>

            </div>

            <Dialog
                    open={dialogOpen}
                    onClose={handleCloseDialog}
                    aria-labelledby="alert-dialog-title"
                    aria-describedby="alert-dialog-description"
                >
                    <DialogTitle id="alert-dialog-title">{dialogInfo.title}</DialogTitle>
                    <DialogContent>
                        <DialogContentText id="alert-dialog-description">
                            {dialogInfo.message}
                        </DialogContentText>
                    </DialogContent>
                    <DialogActions>
                        <Button onClick={handleCloseDialog} color="primary">
                            Cancel
                    </Button>
                        <Button onClick={handleConfirm} color="primary" autoFocus>
                            Confirm
                    </Button>
                    </DialogActions>
                </Dialog>

            <div className="settingsInnerContainer">
                <Typography variant="h6" className="leftPadd">
                    Contact Us
                </Typography>
                <div style={{marginLeft: '16px'}}>
                    {dataPolicy.contactUs()}
                </div>
            </div>

        </div>

    )

}

const mapStateToProps = ({ rUser: { user } }) => ({ user });

export default connect(mapStateToProps)(Settings);