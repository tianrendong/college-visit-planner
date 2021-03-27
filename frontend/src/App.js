import './App.css';
import Container from "./components/container/index"


import React from 'react';
//import { BrowserRouter as Router, Route, Switch, Redirect, Link } from 'react-router-dom';
import { connect } from 'react-redux';
//import { AddAlarmSharp } from '@material-ui/icons';

function App(props) {
    return (
        <Container />
    )
}

const mapStateToProps = (state) => {
    return {
        //loggedIn: state.loggedIn,
        //user: state.user,
    }
}


export default connect(mapStateToProps)(App);