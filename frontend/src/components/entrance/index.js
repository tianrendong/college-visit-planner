import React, { useState } from 'react';
import "./index.css";
import Button from '@material-ui/core/Button';
import Dialog from '@material-ui/core/Dialog';

function Entrance() {
    const [open, setOpen] = useState(true);

    const handleClose = () => {
        setOpen(false);
    };

    return (
        <Dialog fullScreen open={open} onClose={handleClose}>
            <div class="mainContainer">
                <div class="textContainer">
                    <h1 className="title">
                        Design<br />Your<br />College<br />Visits!</h1>
                    <p className="subtitle">
                        We help you design<br />
                        road trips to visit<br /> 
                        your favorite schools
                    </p>
                    <button className="enterButton" onClick={handleClose}>
                        Start Exploring
                    </button>
                </div>
            </div>
        </Dialog>
    )
}

export default Entrance;