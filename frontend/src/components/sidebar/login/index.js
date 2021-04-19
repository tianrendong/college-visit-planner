import React, { useState, useEffect } from 'react';
import { connect, useDispatch } from 'react-redux';
import { routeActions } from '../../../actions/routeActions'
import Typography from '@material-ui/core/Typography';
import Button from '@material-ui/core/Button';
import TextField from '@material-ui/core/TextField';
import Link from '@material-ui/core/Link';
import Grid from '@material-ui/core/Grid';
import Container from '@material-ui/core/Container';
import Card from '@material-ui/core/Card';
import CardContent from '@material-ui/core/CardContent';
import { terms } from '../signup/terms'
import { makeStyles } from '@material-ui/core/styles';
import { useSnackbar } from 'notistack';
import './index.css'

const useStyles = makeStyles((theme) => ({
    cardRoot: {
        margin: '0 20px 5px 20px'
    },
    paper: {
        marginTop: theme.spacing(8),
        display: 'flex',
        flexDirection: 'column',
        alignItems: 'center',
        padding: '30px'
    },
    avatar: {
        margin: theme.spacing(1),
        backgroundColor: theme.palette.secondary.main,
    },
    form: {
        width: '100%',
        marginTop: theme.spacing(1),
    },
    submit: {
        margin: theme.spacing(3, 0, 2),
    },
}));

function Login(props) {
    const classes = useStyles();
    const [username, setUsername] = useState("");
    const [password, setPassword] = useState("");
    const dispatch = useDispatch();
    const { enqueueSnackbar } = useSnackbar();

    const handleSubmit = (e) => {
        e.preventDefault();
        if (username && password) {
            dispatch({
                payload: { username, password },
                type: 'LOGIN_REQUEST',
            })
        }
    }

    const handleNavigateSignup = () => {
        dispatch(routeActions.navigateSidebar('signup'));
    }

    useEffect(() => {
        if (props.error !== '') {
            enqueueSnackbar(props.error, { variant: 'error' });
        }
    }, [props.error])

    useEffect(() => {
        if (props.successMessage !== '') {
            enqueueSnackbar(props.successMessage, { variant: 'success' });
        }
    }, [props.successMessage])

    return (
            <div className={classes.paper}>
                <h1> Login </h1>
                <form className={classes.form} onSubmit={(e) => handleSubmit(e)}>
                    <TextField
                        variant="outlined"
                        margin="normal"
                        required
                        fullWidth
                        label="UserName"
                        autoFocus
                        value={username}
                        onChange={(e) => setUsername(e.target.value)}
                        helperText="username: collegetrip"
                    />
                    <TextField
                        variant="outlined"
                        margin="normal"
                        required
                        fullWidth
                        name="password"
                        label="Password"
                        type="password"
                        id="password"
                        value={password}
                        onChange={(e) => setPassword(e.target.value)}
                        helperText="password: Collegetrip2021"
                    />
                    <Button
                        type="submit"
                        fullWidth
                        variant="contained"
                        color="primary"
                        className={classes.submit}
                    >
                        Sign In
                    </Button>
                    <Grid container justify="flex-end">
                        <Grid item>
                            <Link href="#" variant="body2" onClick={handleNavigateSignup}>
                                {"Create your own account"}
                            </Link>
                        </Grid>
                    </Grid>
                            
                </form>
            </div>
    );
}


const mapStateToProps = ({ rUser: { loggedIn, user, loginForm, signedUp }, rRoute: { error, successMessage } }) =>
    ({ loggedIn, user, loginForm, signedUp, error, successMessage });

export default connect(mapStateToProps)(Login);