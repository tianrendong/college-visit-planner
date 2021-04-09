import React, { useState } from 'react';
import { connect, useDispatch } from 'react-redux'
import { makeStyles } from '@material-ui/core/styles';
import { withStyles } from '@material-ui/core/styles';
import MuiAccordion from '@material-ui/core/Accordion';
import MuiAccordionSummary from '@material-ui/core/AccordionSummary';
import MuiAccordionDetails from '@material-ui/core/AccordionDetails';
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
import './index.css'

const Accordion = withStyles({
    root: {
        // border: '1px solid rgba(0, 0, 0, .125)',
        border: 'none',
        background: 'transparent',
        boxShadow: 'none',
        '&:not(:last-child)': {
            borderBottom: 0,
        },
        '&:before': {
            display: 'none',
        },
        '&$expanded': {
            margin: 'auto',
        },
    },
    expanded: {},
})(MuiAccordion);

const AccordionSummary = withStyles({
    root: {
        backgroundColor: 'transparent',
        marginBottom: -1,
        padding: '0 16px',
        minHeight: 56,
        '&$expanded': {
            minHeight: 56,
        },
    },
    content: {
        '&$expanded': {
            margin: '12px 0',
        },
    },
    expanded: {},
})(MuiAccordionSummary);

const AccordionDetails = withStyles((theme) => ({
    root: {
        padding: '0 0 16px 16px',
        // padding: 0,
        // marginBottom: theme.spacing(2),
    },
}))(MuiAccordionDetails);


function Settings(props) {
    const dispatch = useDispatch();
    const [expanded, setExpanded] = useState(false);
    const [deleteDataDialogOpen, setDeleteDataDialogOpen] = useState(false);

    const [open, setOpen] = React.useState(false);

  const handleOpenDialog = () => {
    setDeleteDataDialogOpen(true);
  };

  const handleCloseDialog = () => {
    setDeleteDataDialogOpen(false);
  };

    const handleExpand = (panel) => (event, isExpanded) => {
        setExpanded(isExpanded ? panel : false);
    };

    const handleLogOut = () => {
        dispatch({
            type: 'LOGOUT',
        })
    }

    const handleDeleteData = () => {
        setDeleteDataDialogOpen(false);
        dispatch({
            payload: props.user.username,
            type: 'REQUEST_DELETE_DATA'
        })
    }

    return (
        <div className="settingsContainer">
            <h1 className="leftPadd" >Settings</h1>
            <div className="settingsInnerContainer">
                <Typography variant="h6" className="leftPadd">
                    Data Policy
                </Typography>
                <Accordion square expanded={expanded === 'panel1'} onChange={handleExpand('panel1')}>
                    <AccordionSummary aria-controls="panel1d-content" id="panel1d-header">
                        <Typography>Collapsible Group Item #1</Typography>
                    </AccordionSummary>
                    <AccordionDetails>
                        <Typography>
                            Lorem ipsum dolor sit amet, consectetur adipiscing elit. Suspendisse malesuada lacus ex,
                            sit amet blandit leo lobortis eget. Lorem ipsum dolor sit amet, consectetur adipiscing
                            elit. Suspendisse malesuada lacus ex, sit amet blandit leo lobortis eget.
                    </Typography>
                    </AccordionDetails>
                </Accordion>
            </div>

            <div>
                <Typography variant="h6" className="leftPadd">
                    My Account
            </Typography>

                <List component="nav" aria-label="Device settings">
                    <ListItem
                        button
                        aria-haspopup="true"
                        aria-controls="lock-menu"
                        onClick={handleOpenDialog}
                    >
                        <ListItemText primary="Clear data" secondary="Clear all trip and collge information on your account" />
                    </ListItem>
                    <ListItem
                        button
                        aria-haspopup="true"
                        aria-controls="lock-menu"
                        onClick={handleLogOut}
                    >
                        <ListItemText primary="Log Out" secondary="Log out of current account" />
                    </ListItem>
                </List>

                <Dialog
                    open={deleteDataDialogOpen}
                    onClose={handleCloseDialog}
                    aria-labelledby="alert-dialog-title"
                    aria-describedby="alert-dialog-description"
                >
                    <DialogTitle id="alert-dialog-title">Clear your data?</DialogTitle>
                    <DialogContent>
                        <DialogContentText id="alert-dialog-description">
                            If you click confirm, we will remove your account and all associated data from our database. You will not be able to recover from this action.
                        </DialogContentText>
                    </DialogContent>
                    <DialogActions>
                        <Button onClick={handleCloseDialog} color="primary">
                            Cancel
          </Button>
                        <Button onClick={handleDeleteData} color="primary" autoFocus>
                            Confirm
          </Button>
                    </DialogActions>
                </Dialog>

            </div>

        </div>

    )

}

const mapStateToProps = ({ rUser: { user } }) => ({ user });

export default connect(mapStateToProps)(Settings);