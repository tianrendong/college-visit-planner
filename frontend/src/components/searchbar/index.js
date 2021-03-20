import './index.css'
import {Divider, IconButton, InputBase, Paper} from "@material-ui/core";
import {Component} from "react";
import MenuIcon from "@material-ui/icons/Menu";

class DirectionsIcon extends Component {
    render() {
        return null;
    }
}

class SearchIcon extends Component {
    render() {
        return null;
    }
}

const SearchBar = (props) => {
    return (
    <Paper component="form" className="root">
        <IconButton className="iconButton" aria-label="menu">
            <MenuIcon/>
        </IconButton>
        <InputBase
            className="input"
            placeholder="Search College"
            inputProps={{'aria-label': 'search college'}}
        />
        <IconButton type="submit" className="iconButton" aria-label="search">
            <SearchIcon/>
        </IconButton>
        <Divider className="divider" orientation="vertical"/>
        <IconButton color="primary" className="iconButton" aria-label="directions">
            <DirectionsIcon/>
        </IconButton>
    </Paper>
    )
}

export default SearchBar;