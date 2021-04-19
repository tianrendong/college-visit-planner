import React, { useState, useEffect } from 'react';
import { connect, useDispatch } from 'react-redux'
import { makeStyles } from '@material-ui/core/styles';
import Button from '@material-ui/core/Button';
import Dialog from '@material-ui/core/Dialog';
import DialogActions from '@material-ui/core/DialogActions';
import DialogContent from '@material-ui/core/DialogContent';
import DialogTitle from '@material-ui/core/DialogTitle';
import Typography from '@material-ui/core/Typography';
import './index.css'
import { routeActions } from '../../../../actions/routeActions'
import { collegeAPI } from '../../../../api/collegeAPI'
import TextField from "@material-ui/core/TextField";
import CollegeCard from './collegeCard'

const useStyles = makeStyles(() => ({
    textFieldRoot: {
        width: '100%',
        margin: '20px 0 0 0',
    },
    paper: {
        width: '40vw',
        overflowX: 'hidden',
        height: '60vh',
        padding: '20px 20px 0 20px'
    },
}));

const SearchCollegeDialog = (props) => {
    const classes = useStyles();
    const dispatch = useDispatch();
    const [input, setInput] = useState('');
    const [suggestions, setSuggestions] = useState([]);

    const handleOnChange = (e) => {
        setInput(e.target.value);
        collegeAPI.getAutocorrectColleges(e.target.value).then(resp => {
            setSuggestions(Object.values(resp))
        })
    }

    const handleClose = () => {
        dispatch(routeActions.navigatePopDialog(''));
    }

    useEffect(() => {
        setInput('');
        setSuggestions('');
    }, [props.popDialog])

    return (
        <Dialog open={props.popDialog !== ''} onClose={handleClose}
            classes={{ paper: classes.paper }} scroll='paper'>
            <DialogTitle>
                <Typography variant="h6">Add College</Typography>
                <TextField
                    classes={{ root: classes.textFieldRoot }}
                    label="Search a College"
                    variant="outlined"
                    value={input}
                    onChange={(e) => handleOnChange(e)} />
            </DialogTitle>
            <DialogContent>
                {(suggestions.length !== 0) &&
                    suggestions.map(c => <CollegeCard username={props.user.username} college={c}></CollegeCard>)}
            </DialogContent>
            <DialogActions>
                <Button onClick={handleClose} color="primary">Cancel</Button>
            </DialogActions>
        </Dialog>
    );
}
const mapStateToProps = ({ rRoute: { popDialog }, rUser: { user } }) => ({ popDialog, user });

export default connect(mapStateToProps)(SearchCollegeDialog);
