import React from 'react';
import { connect, useDispatch } from 'react-redux';
import Card from '@material-ui/core/Card';
import CardActions from '@material-ui/core/CardActions';
import CardContent from '@material-ui/core/CardContent';
import { makeStyles } from '@material-ui/core/styles';
import Button from '@material-ui/core/Button';
import Typography from '@material-ui/core/Typography';
import './index.css'

const useStyles = makeStyles(() => ({
    root: {
        width: '200px',
        display: 'flex',
        flexDirection: 'column',
        zIndex: 99,
        padding: 0
    },
    contentRoot: {
        padding: '15px',
        paddingBottom: '15px',
    },
    title: {
        fontSize: 18,
        lineHeight: '20px',
    }
  }));

const InfoCard = (props) =>  {
    const classes = useStyles();
    const college = props.markerClicked.content;

    return (
        <Card className={classes.root}>
            {/* <CardMedia
                component="img"
                height="140"
                image="/static/images/cards/contemplative-reptile.jpg"
            /> */}
            <CardContent className={classes.contentRoot}>
                <Typography gutterBottom variant="h6" className={classes.title}>
                    {college.name}
                </Typography>
                <Typography variant="body2" color="textSecondary" component="p">
                    {college.city}, {college.state}
                </Typography>
                <Button size="small" href={college.url} target="_blank">
                    Visit Website
                </Button>
            </CardContent>
        </Card>
    );
}

const mapStateToProps = ({ rMap: { markerClicked} }) => ({ markerClicked });

export default connect(mapStateToProps)(InfoCard);