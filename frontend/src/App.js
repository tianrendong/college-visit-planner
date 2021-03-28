import './App.css';
import Sidebar from "./views/sidebar/index"
import Entrance from "./views/entrance/index"
import Infobar from "./views/infobar/index"
import Map from "./views/map/index"

import React from 'react';
// import Entrance from './views/mainView/Entrance/index'
import { connect } from 'react-redux';


function App(props) {
    return (
        <>
            {/* <Entrance/> */}
            <Map/>
            <Infobar/>
            <Sidebar />
        </>
    )
}

export default App;