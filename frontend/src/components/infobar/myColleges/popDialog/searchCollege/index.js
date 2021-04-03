import React from 'react'
import './index.css'
import PopDialog from '../index';
import DialogActions from '@material-ui/core/DialogActions';
import Button from '@material-ui/core/Button';
import { connect } from 'react-redux'
import TextField from '@material-ui/core/TextField';
import Autocomplete from '@material-ui/lab/Autocomplete';
import Typography from '@material-ui/core/Typography';

const SearchCollege = (props) => {

    const { open, handleClose = () => { } } = props;

    const [value, setValue] = React.useState(null);

    const handleFocus = () => {
        console.log("a")
    }
    return (
        <div style={{position: 'absolute'}}>
            <Typography variant="h6">Add College</Typography>
                <Autocomplete
                    options={top100Films}
                    getOptionLabel={(option) => option.title}
                    style={{ width: '80%', margin: '30px' }}
                    renderInput={(params) => <TextField 
                        margin="normal" 
                        variant="outlined" 
                        style={{ width: '100%' }}/>}
                />

                <div className="collegeInfoContainer">
                    <div>Brown University</div>
                    <div>Providence, RI</div>
                    <p className="description">Brown is a leading research university, home to world-renowned faculty and also an innovative educational institution where the curiosity, creativity and intellectual joy of students drives academic excellence.</p>
                </div>
       
            <DialogActions>
                <Button onClick={handleClose} color="primary" autoFocus>
                    Confirm
                    </Button>
                <Button onClick={handleClose} color="primary">
                    Cancel
                    </Button>
            </DialogActions>
        </div>
    );
}

const top100Films = [
    { title: 'The Shawshank Redemption', year: 1994 },
    { title: 'The Godfather', year: 1972 },
]

const mapStateToProps = ({ rRoute: { popDialog } }) => ({ popDialog });

export default connect(mapStateToProps)(SearchCollege);

