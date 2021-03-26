import './App.css';
import Map from './components/Map/index'
import Container from "./components/Container/index"
import InfoCard from "./components/InfoCard";
import Login from "./views/sidebar/Login";
import SignUp from "./views/sidebar/SignUp";
import UserHome from "./views/sidebar/userhome";
import MyColleges from "./views/mainView/myColleges";
import Entrance from "./views/mainView/Entrance";

import React from 'react';
import { BrowserRouter as Router, Route, Switch, Redirect, Link } from 'react-router-dom';
import { connect } from 'react-redux';

const routes = {
    'login' : <Login/>,
    'signup' : <SignUp/>,
    'home' : <UserHome/>,
    'myColleges' : <MyColleges/>,
    'initialMap' : <Map/>
}

function App(props) {
    console.log(props)
    return (
        <Entrance/>
        // <Container sidebarView={routes[props.sidebar]} mainView={routes[props.mainview]}/>
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