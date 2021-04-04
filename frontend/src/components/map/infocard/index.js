import React from 'react';
import Card from '@material-ui/core/Card';
import CardActions from '@material-ui/core/CardActions';
import CardContent from '@material-ui/core/CardContent';
import CardMedia from '@material-ui/core/CardMedia';
import Button from '@material-ui/core/Button';
import Typography from '@material-ui/core/Typography';
import './index.css'
import Link from "@material-ui/core/Link";

// <Link color="inherit" href="https://material-ui.com/">
//     College Visit Planner
// </Link>{' '}

const InfoCard = (props) => {
    const {
        title, description, image, learnMore
    } = props;

    return (
        <Card className="root">
            {/* <CardMedia
                component="img"
                height="140"
                image="/static/images/cards/contemplative-reptile.jpg"
            /> */}
            <CardContent style={{padding: "5px 11px"}}>
                aaa
                {/* <Typography gutterBottom variant="h5" component="h2">
                    {title}
                </Typography>
                <Typography variant="body2" color="textSecondary" component="p">
                    {description}
                </Typography> */}
            </CardContent>
            <CardActions style={{padding: "6px"}}>
                <Button size="small" color="primary">
                    Learn More
                </Button>
            </CardActions>
        </Card>
    );
}

export default InfoCard;