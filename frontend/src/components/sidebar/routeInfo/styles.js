import { makeStyles } from "@material-ui/core";

export const useStyles = makeStyles((theme) => ({
    state: {
        fontSize: 19,
    },
    cardRoot: {
        minWidth: 275,
        margin: '10px 20px 15px 0',
        width: '100%',
    },
    cardContentRoot: {
        backgroundColor: '#fffefc',
    },
    navigateIcon: {
    },
    navigateGPS: {
        padding: '0'
    },
    icon: {
        marginRight:'15px',
        color: '#000000',
        opacity: .8,
        alignSelf: 'center',

    }
}));