import './index.css'
import React from "react";
import { connect, useDispatch } from 'react-redux'
import { makeStyles } from '@material-ui/core/styles';
import Card from '@material-ui/core/Card';
import CardContent from '@material-ui/core/CardContent';
import SearchCollegeDialog from "./searchCollegeDialog";
import IconButton from '@material-ui/core/IconButton';
import AddIcon from '@material-ui/icons/Add';
import Button from '@material-ui/core/Button';
import Typography from '@material-ui/core/Typography';
import { routeActions } from '../../../actions/routeActions';
import ClearIcon from '@material-ui/icons/Clear';

const useStyles = makeStyles({
    root: {
        minWidth: 275,
        margin: '10px 20px 15px 0',
    },
    cardRoot: {
        backgroundColor: '#fffefc',
        paddingBottom: '12px'
    },
    buttonRoot: {
        padding: '4px'
    }
});

const MyColleges = (props) => {
    const classes = useStyles();
    const dispatch = useDispatch();

    const handleClickAdd = () => {
        dispatch(routeActions.navigatePopDialog('searchCollege'));
    }

    const handleClose = () => {
        dispatch(routeActions.navigatePopDialog(''));
    }
    
    const handleDelete = (id) => {
        dispatch({
            payload: {
                username: props.user.username,
                collegeID: id,
            },
            type: 'REQUEST_DELETE_COLLEGE'
        })
    }

    const noCollege = () => {
        if (props.user.hasOwnProperty("colleges")) {
            return Object.values(props.user.colleges).length === 0;
        } else {
            return true;
        }
    }

    return (
    <div className="leftPadd">
        <div className="myCollegeHeader">
            <h1>My Colleges</h1>
            <IconButton onClick={handleClickAdd}>
                <AddIcon fontSize="large"/>
            </IconButton>
        </div>

        {!noCollege() &&
        <div className="collegeList">
            {Object.values(props.user.colleges).map(c =>
                <Card
                    classes={{ root: classes.root }}>
                    <CardContent
                        classes={{ root: classes.cardRoot }}>
                        <Typography variant="h6" component="h2">
                            {c.name}
                        </Typography>
                        <Typography className={classes.pos} color="textSecondary">
                            {c.city}, {c.state}
                        </Typography>
                        <div className="collegeListFoot">
                        <Button size="small" href={c.url} target="_blank">Learn More</Button>
                        <IconButton classes={{ root: classes.buttonRoot }} onClick={() => handleDelete(c.id)}>
                            <ClearIcon style={{width: '18px', height: '18px'}}/>
                        </IconButton>
                        </div>
                    </CardContent>
                </Card>
            )}
        </div>}


        {noCollege() && (
            <div className="noCollege">
                No college to visit yet 😞 <br />
                Click add to start planning your trip!
            </div>
        )}

        <SearchCollegeDialog open={props.popDialog === 'searchCollege'} handleClose={handleClose}>

        </SearchCollegeDialog>
    </div>)
}

const mapStateToProps = ({ rUser: { user }, rRoute: { popDialog } }) => ({ user, popDialog });

export default connect(mapStateToProps)(MyColleges);
