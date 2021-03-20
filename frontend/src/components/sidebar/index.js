import React, {useState} from 'react';
import Drawer from '@material-ui/core/Drawer';
import MenuIcon from '@material-ui/icons/Menu';
import ChevronLeftIcon from '@material-ui/icons/ChevronLeft';
import SearchBar from "../searchbar";
import './index.css'


const Sidebar = (props) => {
    const [open, setOpen] = useState(false);
    const [searchVal, setSearchVal] = useState('');
    return (
        <div className="sidebarContainer">
            <div>
                <MenuIcon
                    color="inherit"
                    aria-label="open drawer"
                    onClick={() => setOpen(true)}
                    edge="start"
                />
            </div>
            <div>
                <Drawer className="drawer"
                        variant="persistent"
                        anchor="left"
                        open={open}>
                    <div className="drawerHeader">
                        <ChevronLeftIcon onClick={() => setOpen(false)}/>
                    </div>
                </Drawer>
            </div>
        </div>
    )

}

export default Sidebar;




