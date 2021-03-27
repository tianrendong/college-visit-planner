import './App.css';
import Sidebar from "./views/containers/sidebar/index"
import Entrance from "./views/views/Entrance"
import Infobar from "./views/containers/infobar/index"
import Map from "./components/map/index"

import React from 'react';
// import Entrance from './views/mainView/Entrance/index'
import { connect } from 'react-redux';


function App(props) {
    return (
        <>
            <Entrance/>
            <Map/>
            {/* <Infobar/> */}
            <Sidebar className="sidebar"/>
        </>
    )
}

export default App;