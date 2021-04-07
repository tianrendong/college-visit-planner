import React, {useState} from 'react'
import './index.css'
import {useDispatch} from 'react-redux';
import DialogActions from '@material-ui/core/DialogActions';
import Button from '@material-ui/core/Button';
import {connect} from 'react-redux'
import TextField from "@material-ui/core/TextField";
import clsx from 'clsx';
import Typography from '@material-ui/core/Typography';
import {collegeAPI} from '../../../../../api/collegeAPI'
import Paper from '@material-ui/core/Paper';
import Card from '@material-ui/core/Card';
import CardContent from '@material-ui/core/CardContent';
import {makeStyles} from '@material-ui/core/styles';
import IconButton from '@material-ui/core/IconButton';
import ExpandMoreIcon from '@material-ui/icons/ExpandMore';
import Collapse from '@material-ui/core/Collapse';
import {routeActions} from '../../../../../actions/routeActions'
import ClearIcon from "@material-ui/icons/Clear";
import AddIcon from "@material-ui/icons/Add";


const useStyles = makeStyles((theme) => ({
    textFieldRoot: {
        width: '100%',
        margin: '20px 0 0 0',
    },
    cardRoot: {
        minWidth: 275,
        margin: '10px 20px 15px 0',
        width: '100%',
    },
    cardContentRoot: {
        backgroundColor: '#fffefc',
    },
    expand: {
        transform: 'rotate(0deg)',
        marginLeft: 'auto',
        transition: theme.transitions.create('transform', {
            duration: theme.transitions.duration.shortest,
        }),
    },
    expandOpen: {
        transform: 'rotate(180deg)',
    },
}));

const SearchCollege = (props) => {
    const classes = useStyles();

    const [input, setInput] = useState('');
    const [suggestions, setSuggestions] = useState([]);

    const handleOnChange = (e) => {
        setInput(e.target.value);
        collegeAPI.getAutocorrectColleges(e.target.value).then(resp => {
            setSuggestions(Object.values(resp))
        })
        // console.log(collegeAPI.getAutocorrectColleges(e.target.value));
        // setSuggestions(collegeAPI.getAutocorrectColleges(e.target.value));
    }

    return (
        <div>
            <Typography variant="h6">Add College</Typography>
            <TextField
                classes={{root: classes.textFieldRoot}}
                label="Search a College"
                variant="outlined"
                value={input}
                onChange={(e) => handleOnChange(e)}/>
            {(suggestions.length !== 0) &&
            suggestions.map(c => <CollegeCard username={props.user.username} college={c}></CollegeCard>)}

        </div>
    );
}

const mapStateToProps = ({rRoute: {popDialog}, rUser: {user}}) => ({popDialog, user});

export default connect(mapStateToProps)(SearchCollege);


const CollegeCard = (props) => {
    const {username, college} = props;
    const [expanded, setExpanded] = useState(false);
    const classes = useStyles();
    const dispatch = useDispatch();

    const handleExpandClick = () => {
        setExpanded(!expanded);
    };

    const handleAddCollege = () => {
        dispatch({
            payload: {
                username: username,
                collegeID: college.id
            },
            type: "REQUEST_ADD_COLLEGE"
        })
        dispatch(routeActions.navigatePopDialog(''));
    }

    return (
        <Card classes={{root: classes.cardRoot}}>
            <div className="collegeSearchResultInnerContainer">
                <CardContent
                    classes={{root: classes.cardContentRoot}}>
                    <Typography variant="h6" component="h2">
                        {college.name}
                    </Typography>
                    <Typography className={classes.pos} color="textSecondary">
                        {college.city}, {college.state}
                    </Typography>

                </CardContent>
                <IconButton classes={{ root: classes.buttonRoot }} onClick={handleAddCollege}>
                    <AddIcon style={{width: '18px', height: '18px'}}/>
                </IconButton>
                <IconButton
                    className={clsx(classes.expand, {
                        [classes.expandOpen]: expanded,
                    })}
                    onClick={handleExpandClick}
                    aria-expanded={expanded}
                    aria-label="show more"
                >
                    <ExpandMoreIcon/>
                </IconButton>
            </div>
            <Collapse in={expanded} timeout="auto" unmountOnExit>
                <CardContent>
                    <Typography paragraph>{college.description}</Typography>
                    <Button size="small" href={college.url} target="_blank">Learn More</Button>
                </CardContent>
            </Collapse>
        </Card>
    )
}
