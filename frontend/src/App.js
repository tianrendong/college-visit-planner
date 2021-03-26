import './App.css';
import Map from './components/Map/index'
import Container from "./components/Container/index"
import InfoCard from "./components/InfoCard";
import Login from "./components/Login";
import SignUp from "./components/SignUp";
import UserHome from "./views/sidebar/userhome";
import MyColleges from "./views/mainView/myColleges";

import React from 'react';
import { BrowserRouter as Router, Route, Switch, Redirect, Link } from 'react-router-dom';
import { connect } from 'react-redux';
import { AddAlarmSharp } from '@material-ui/icons';

const routes = {
    'login' : <Login/>,
    'signup' : <SignUp/>,
    'initialMap' : <Map/>
}

function App(props) {
    console.log(props)
    return (
        <Container sidebarView={routes[props.sidebar]} mainView={routes[props.mainview]}/>
    )
}

const mapStateToProps = (state) => {
    console.log(state)
    return {
        //loggedIn: state.loggedIn,
        //user: state.user,
        sidebar: state.rRoute.sidebar,
        mainview: state.rRoute.mainview
    }
}


export default connect(mapStateToProps)(App);