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


function Settings() {
    const dispatch = useDispatch();
    const [expanded, setExpanded] = React.useState(false);

    const handleChange = (panel) => (event, isExpanded) => {
        setExpanded(isExpanded ? panel : false);
    };

    const handleLogOut = () => {
        dispatch({
            type: 'LOGOUT',
        })
    }

    return (
        <div className="settingsContainer">
            <h1 className="leftPadd" >Settings</h1>
            <div className="settingsInnerContainer">
            <Typography variant="h6" className="leftPadd">
                Data Policy
            </Typography>
            <Accordion square expanded={expanded === 'panel1'} onChange={handleChange('panel1')}>
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

            </div>

            {/* <div className="logoutButtonContainer">
            <Button size="large" className="logoutButton2">
                Log Out 😦
                </Button>
        </div> */}

        </div>

    )

}

export default Settings;