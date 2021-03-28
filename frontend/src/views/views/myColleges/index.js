import Button from "@material-ui/core/Button";
import React, {useState} from "react";
import PopOutDialog from "../../../components/PopOutDialog";

const MyColleges = () => {
    const [dialogOpen, setDialogOpen] = useState(false);

    const handleClickOpen = () => {
        setDialogOpen(true);
    };

    const handleClose = () => {
        setDialogOpen(false);
    };


    return (<div>
        <Button variant="outlined" color="primary" onClick={handleClickOpen}>
            Open alert dialog
        </Button>
        <PopOutDialog open={dialogOpen} handleClose={handleClose}></PopOutDialog>
    </div>)
}

export default MyColleges;