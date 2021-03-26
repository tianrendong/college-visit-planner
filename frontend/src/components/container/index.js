import React, {useState} from 'react';
import Drawer from '@material-ui/core/Drawer';
import ChevronLeftIcon from '@material-ui/icons/ChevronLeft';
import { makeStyles, useTheme } from '@material-ui/core/styles';
import './index.css'
import clsx from 'clsx';
import useWindowSize from "../../hooks/useWindowSize";
import IconButton from '@material-ui/core/IconButton';
import PersonIcon from '@material-ui/icons/Person';

const Container = (props) => {
    const {mainView, sidebarView} = props;
    const { height, width } = useWindowSize();
    const [open, setOpen] = useState(false);

    const useStyles = makeStyles((theme) => ({
        content: {
            flexGrow: 1,
            // padding: theme.spacing(3),
            transition: theme.transitions.create('margin', {
                easing: theme.transitions.easing.sharp,
                duration: theme.transitions.duration.leavingScreen,
            }),
            marginLeft: 0,
        },
        contentShift: {
            transition: theme.transitions.create('margin', {
                easing: theme.transitions.easing.easeOut,
                duration: theme.transitions.duration.enteringScreen,
            }),
            marginLeft: width * 0.23,
        },
    }))

    const classes = useStyles();

    return (
        <div>
        <div className="page">
            <IconButton
                className="loginIcon"
                onClick={() => setOpen(true)}>
                <PersonIcon fontSize="large" />
            </IconButton>
            <Drawer variant="persistent"
                    anchor="left"
                    open={open}
                    docked='true'>
                <div className="drawerHeader">
                    <ChevronLeftIcon onClick={() => setOpen(false)}/>
                </div>
                <div className="sidebarInnerContainer">
                    {sidebarView}
                </div>
            </Drawer>
        </div>
        <div className={clsx(classes.content, {
            [classes.contentShift]: open
        })}>
            {mainView}
        </div>
        </div>
    )

}

export default Container;




