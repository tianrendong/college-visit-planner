import React, { useState } from 'react';
import "./index.css";
import { makeStyles } from '@material-ui/core/styles';
import Dialog from '@material-ui/core/Dialog';
import { terms } from '../sidebar/signup/terms'

const useStyles = makeStyles(() => ({
    paper: {
        width: '100vw',
        overflowX: 'hidden',
        // height: '50vh',
        padding: '40px 45px 60px 60px'
    },
}));

function Entrance() {
    const [open, setOpen] = useState(true);
    const classes = useStyles();

    const handleClose = () => {
        setOpen(false);
    };

    return (
        // <Dialog fullScreen open={open} onClose={handleClose}>
        //     <div class="mainContainer">
        //         <div class="textContainer">
        //             <h1 className="title">
        //                 Design<br />Your<br />College<br />Visits!</h1>
        //             <p className="subtitle">
        //                 We help you design<br />
        //                 road trips to visit<br /> 
        //                 your favorite schools
        //             </p>
        //             <button className="enterButton" onClick={handleClose}>
        //                 Start Exploring
        //             </button>
        //         </div>
        //     </div>
        // </Dialog>
        <Dialog open={open} onClose={handleClose} classes={{ paper: classes.paper }} scroll='paper'>
                <div class="textContainer">
                    <h1 className="title2">
                        Design Your College Visit</h1>
                    {terms.Entrance()}
                    {/* <button className="enterButton2" onClick={handleClose}>
                        Start Exploring
                    </button> */}
                </div>
    </Dialog>
    )
}

export default Entrance;