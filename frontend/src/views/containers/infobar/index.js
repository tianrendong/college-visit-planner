import React, { useState } from 'react';
import Drawer from '@material-ui/core/Drawer';
import './index.css'
import useWindowSize from "../../../hooks/useWindowSize";
import IconButton from '@material-ui/core/IconButton';
import { connect } from 'react-redux';
import CloseIcon from '@material-ui/icons/Close';
import MyColleges from '../../views/myColleges/index'
import Settings from '../../views/settings/index'

const routes = {
     'myColleges': <MyColleges/>,
     'settings': <Settings/>
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
                        {routes[props.infobar]}
                    </div>
                </Drawer>
            </div>
        </div>
    )
}

const mapStateToProps = ({rRoute : { infobar }}) => ({ infobar });

export default connect(mapStateToProps)(Infobar);




