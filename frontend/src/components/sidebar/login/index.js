import React, { useState, useEffect } from 'react';
import { connect, useDispatch } from 'react-redux';
import { routeActions } from '../../../actions/routeActions'

import Button from '@material-ui/core/Button';
import TextField from '@material-ui/core/TextField';
import Link from '@material-ui/core/Link';
import Grid from '@material-ui/core/Grid';
import Typography from '@material-ui/core/Typography';
import Container from '@material-ui/core/Container';
import { makeStyles } from '@material-ui/core/styles';
import { useSnackbar } from 'notistack';
import './index.css'

const useStyles = makeStyles((theme) => ({
    paper: {
        marginTop: theme.spacing(8),
        display: 'flex',
        flexDirection: 'column',
        alignItems: 'center',
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
        dispatch(routeActions.navigateSignup());
    }
    // const handleFormChange = ((item, value) =>
    //     dispatch({
    //         payload: { item, value },
    //         type: 'LOGIN_FORM_CHANGE',
    //     }))

    useEffect(() => {
        // variant could be success, error, warning, info, or default
        if (props.error !== '') {
            enqueueSnackbar(props.error, {variant: 'error'});
        }
    }, [props.error])

    useEffect(() => {
        if (props.signedUp) {
            enqueueSnackbar('Signed Up!', {variant: 'success'});
        }
    }, [props.signedUp])
    
    return (
        <Container component="main" maxWidth="xs">
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
                    // onChange={(e) => handleFormChange('username', e.target.value)}
                    />
                    {/* // value={props.loginForm.username}
                    //     onChange={(e) => handleFormChange('username', e.target.value)} */}

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
                                {"Don't have an account? Sign Up"}
                            </Link>
                        </Grid>
                    </Grid>
                </form>
            </div>
        </Container>
    );
}


const mapStateToProps = ({ rUser: { loggedIn, user, error, loginForm, signedUp } }) => 
({ loggedIn, user, error, loginForm, signedUp });

export default connect(mapStateToProps)(Login);