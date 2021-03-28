import React, { useState } from 'react';
import Drawer from '@material-ui/core/Drawer';
import './index.css'
import useWindowSize from "../../../hooks/useWindowSize";
import IconButton from '@material-ui/core/IconButton';
import PersonIcon from '@material-ui/icons/Person';
import { connect } from 'react-redux';

import CloseIcon from '@material-ui/icons/Close';

const routes = {
    
}

const Infobar = (props) => {
    const { height, width } = useWindowSize();
    const [open, setOpen] = useState(true);

    return (
        <div>
            <div className="page">
                <Drawer variant="persistent"
                    anchor="left"
                    open={open}
                    docked='true'>
                    <div className="drawerHeader">
                        <IconButton
                            onClick={() => setOpen(false)}>
                            <CloseIcon className="closeIcon"/>
                        </IconButton>
                    </div>
                    <div className="infobarInnerContainer">
                        {routes[props.sidebar]}
                    </div>
                </Drawer>
            </div>
        </div>
    )
}

const mapStateToProps = () => ({ });

export default connect(mapStateToProps)(Infobar);




