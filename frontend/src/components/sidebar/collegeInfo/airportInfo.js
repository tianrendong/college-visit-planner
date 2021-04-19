import React, { useState } from 'react'
import './index.css'
import Typography from '@material-ui/core/Typography';
import Card from '@material-ui/core/Card';
import CardContent from '@material-ui/core/CardContent';
import { makeStyles } from '@material-ui/core/styles';


const useStyles = makeStyles((theme) => ({
    cardRoot: {
        margin: '0 20px 5px 20px'
    },
    cardContentRoot: {
        backgroundColor: '#fffefc',
    },
    name: {
        fontSize: '17px',
        fontWeight: '450'
    }
}));

const AirportInfo = (props) => {
    const { airport } = props;
    const classes = useStyles();

    return (
        <Card classes={{ root: classes.cardRoot }}>
            <CardContent
                classes={{ root: classes.cardContentRoot }}>
                <Typography className={classes.name}>
                    {airport.name}
                </Typography>
                <Typography className={classes.pos} color="textSecondary">
                    {airport.city}, {airport.state}
                </Typography>
            </CardContent>
        </Card>
    )
}

export default AirportInfo;