import React, { useState, useEffect } from 'react';
import Button from '@material-ui/core/Button';
import TextField from '@material-ui/core/TextField';
import Link from '@material-ui/core/Link';
import Grid from '@material-ui/core/Grid';
import { makeStyles } from '@material-ui/core/styles';
import Container from '@material-ui/core/Container';
import { routeActions } from "../../../actions/routeActions";
import Accordion from '@material-ui/core/Accordion';
import AccordionSummary from '@material-ui/core/AccordionSummary';
import AccordionDetails from '@material-ui/core/AccordionDetails';
import Checkbox from '@material-ui/core/Checkbox';
import FormControlLabel from '@material-ui/core/FormControlLabel';
import ExpandMoreIcon from '@material-ui/icons/ExpandMore';
import { useSnackbar } from 'notistack';
import { connect, useDispatch } from 'react-redux';
import {dataPolicy} from "../../infobar/settings/policies";
import DialogActions from "@material-ui/core/DialogActions";
import Dialog from "@material-ui/core/Dialog";

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
        width: '100%', // Fix IE 11 issue.
        marginTop: theme.spacing(3),
    },
    submit: {
        margin: theme.spacing(3, 0, 2),
    },
}));

const PASSWORD_ERROR = "Password needs to be at least 8 characters long with uppercase, lowercase, and numbers."
const PASSWORD_CONFIRM_ERROR = "Passwords must match"
function SignUp(props) {
    const dispatch = useDispatch();
    const classes = useStyles();
    const { enqueueSnackbar } = useSnackbar();

    const [firstname, setFirstname] = useState("");
    const [lastname, setLastname] = useState("");
    const [username, setUsername] = useState("");
    const [password, setPassword] = useState("");
    const [passwordIsValid, setPasswordIsValid] = useState("");
    const [confirmPassword, setConfirmPassword] = useState("");
    const [passwordConfirmation, setPasswordConfirmation] = useState(false)
    const [readTerms, setReadTerms] = useState(false)
    const [openTerms, setOpenTerms] = useState(false)

    const passwordFieldOnChange = (value) => {
        setPassword(value)
        setPasswordIsValid(value.match("^(?=.*?[A-Z])(?=.*?[a-z])(?=.*?[0-9]).{8,}$"))
        setPasswordConfirmation(value === confirmPassword)
    }

    const handleConfirmPasswordFieldOnChange = (value) => {
        setConfirmPassword(value)
        setPasswordConfirmation(value === password)
    }

    useEffect(() => {
        if (props.error !== '') {
            enqueueSnackbar(props.error, { variant: 'error' });
        }
    }, [props.error])

    const handleSubmit = (e) => {
        e.preventDefault();
        if (firstname && lastname && username && password) {
            dispatch({
                payload: { firstname, lastname, username, password },
                type: 'SIGNUP_REQUEST',
            });
        }
    }

    const handleNavigateLogin = () => {
        dispatch(routeActions.navigateSidebar('login'));
    }

    const handleCloseTerms = () => {
        setOpenTerms(false)
    }

    const handleOpenTerms = () => {
        setOpenTerms(true)
        setReadTerms(true)
    }

    return (
        <Container component="main" maxWidth="xs">
            <div className={classes.paper}>
                <h1> Sign Up </h1>
                <form className={classes.form} onSubmit={(e) => handleSubmit(e)}>
                    <Grid container spacing={2}>
                        <Grid item xs={12} sm={6}>
                            <TextField
                                variant="outlined"
                                required
                                fullWidth
                                id="firstName"
                                label="First Name"
                                value={firstname}
                                onChange={(e) => setFirstname(e.target.value)}
                            />
                        </Grid>
                        <Grid item xs={12} sm={6}>
                            <TextField
                                variant="outlined"
                                required
                                fullWidth
                                id="lastName"
                                label="Last Name"
                                value={lastname}
                                onChange={(e) => setLastname(e.target.value)}
                            />
                        </Grid>
                        <Grid item xs={12}>
                            <TextField
                                variant="outlined"
                                required
                                fullWidth
                                label="Username"
                                value={username}
                                onChange={(e) => setUsername(e.target.value)}
                            />
                        </Grid>
                        <Grid item xs={12}>
                            <TextField
                                variant="outlined"
                                required
                                fullWidth
                                name="password"
                                label="Create a New Password"
                                type="password"
                                id="password"
                                value={password}
                                helperText={(password !== '') && !passwordIsValid && PASSWORD_ERROR}
                                error={(password !== '') && !passwordIsValid}
                                onChange={(e) => passwordFieldOnChange(e.target.value)}
                            />
                        </Grid>
                        <Grid item xs={12}>
                            <TextField
                                variant="outlined"
                                required
                                fullWidth
                                name="verifyPassword"
                                label="Confirm Password"
                                type="password"
                                value={confirmPassword}
                                helperText={(confirmPassword !== '') && (passwordConfirmation === false) && PASSWORD_CONFIRM_ERROR}
                                error={(confirmPassword !== '') && (passwordConfirmation === false)}
                                onChange={(e) => handleConfirmPasswordFieldOnChange(e.target.value)}
                            />
                        </Grid>
                        <Grid item xs={12}>
                            <FormControlLabel
                                aria-label="Acknowledge"
                                onClick={(event) => event.stopPropagation()}
                                onFocus={(event) => event.stopPropagation()}
                                control={<Checkbox color="primary" required disabled={(!readTerms)}/>}
                                label="Click to read the terms and conditions."
                            />
                        </Grid>
                        <Grid item xs={12}>
                            <FormControlLabel
                                aria-label="Acknowledge"
                                onClick={(event) => event.stopPropagation()}
                                onFocus={(event) => event.stopPropagation()}
                                control={<Button onClick={handleOpenTerms}/>}
                                label="Click to read the terms and conditions."
                            />
                        </Grid>
                    </Grid>
                    <Dialog open={openTerms}
                            onClose={handleCloseTerms}
                            classes={{ paper: classes.paper }}>
                        <div className="popDialogContainer">
                            {dataPolicy.collectionAndUsage()}
                            {dataPolicy.dataProtectionRights()}
                        </div>
                    </Dialog>

                    <Button
                        type="submit"
                        fullWidth
                        variant="contained"
                        color="primary"
                        className={classes.submit}
                        disabled={(password === '') || (passwordConfirmation === false)}
                    >
                        Sign Up
                    </Button>
                    <Grid container justify="flex-end">
                        <Grid item>
                            <Link href="#" variant="body2" onClick={handleNavigateLogin}>
                                Already have an account? Sign in
                            </Link>
                        </Grid>
                    </Grid>
                </form>
            </div>

        </Container>
    );
}

const mapStateToProps = ({ rUser: { error } }) => ({ error });

export default connect(mapStateToProps)(SignUp);