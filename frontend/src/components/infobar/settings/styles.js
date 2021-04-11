import MuiAccordion from '@material-ui/core/Accordion';
import MuiAccordionSummary from '@material-ui/core/AccordionSummary';
import MuiAccordionDetails from '@material-ui/core/AccordionDetails';
import { withStyles } from '@material-ui/core/styles';

export const Accordion = withStyles({
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

export const AccordionSummary = withStyles({
    root: {
        backgroundColor: 'transparent',
        marginBottom: -1,
        padding: '0 16px',
        minHeight: 30,
        // lineHeight: '8px'
        // minHeight: 56,
        // '&$expanded': {
        //     minHeight: 56,
        // },
    },
    content: {
        '&$expanded': {
            margin: '5px 0',
        },
    },
    expanded: {},
})(MuiAccordionSummary);

export const AccordionDetails = withStyles((theme) => ({
    root: {
        padding: '0 0 16px 16px',
    },
}))(MuiAccordionDetails);