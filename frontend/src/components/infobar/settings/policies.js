import React from 'react'
import Typography from '@material-ui/core/Typography';
import Box from '@material-ui/core/Box';
import { withStyles } from '@material-ui/core/styles';

export const dataPolicy = {
    collectionAndUsage,
    dataProtectionRights,
    contactUs,
    aboutTheApp
};

const Boxed = withStyles({
    root: {
        margin: "8px 0 10px 0"
    },
})(Box);

const BoxedSpaceAfter = withStyles({
    root: {
        margin: "0 0 10px 0"
    },
})(Box);

const BoxedSpaceBefore = withStyles({
    root: {
        margin: "10px 0 0 0"
    },
})(Box);

function collectionAndUsage() {
    return(
        <Typography>
            <BoxedSpaceAfter>
                Our Company collects your personal identification information and your travel information (colleges that you want to visit) 
            </BoxedSpaceAfter>

            <Boxed>
                You directly provide us with these data. We collect and process them
                when you create an account or when you use our website via your browser’s cookies.
            </Boxed>

            <BoxedSpaceBefore>
                We collect your data so that we remember your identifications and the trip information that you keep. 
                We process the colleges you enter using our algorithms to help you design your college trip. 
                Whenever you add or delete a college, your data is immediately stored into our encrypted database. 
                Since the only purpose of this website is to generate optimized trips, you cannot restrict or 
                object data processing--if you have any concerns, please contact us.
            </BoxedSpaceBefore>
        </Typography>
)}

function dataProtectionRights() {
    return(
        
        <Typography>
            
            <BoxedSpaceAfter>
                We would like to make sure that you are fully aware of your data protection rights. Every user is entitled to the following:
            </BoxedSpaceAfter>
        
            <Boxed>
                <Box fontWeight="fontWeightBold">The right to access</Box>
                You have the right to request our team for copies of your personal data.
            </Boxed>
            
            <Boxed>
                <Box fontWeight="fontWeightBold">The right to rectification</Box>
                You have the right to request that our team correct any incorrect or incomplete information.
            </Boxed>

            <Boxed>
                <Box fontWeight="fontWeightBold">The right to erasure</Box>
                <BoxedSpaceAfter>
                    If you wish to erase your trip information, go to <i> Settings &gt;&gt; My Account &gt;&gt; Clear Data </i>. <br/>
                    We will delete your trip data from our database, but your account and personal identification information will be kept. 
                </BoxedSpaceAfter>
                <BoxedSpaceAfter>
                    If you wish to erase your entire account, go to <i> Settings &gt;&gt; My Account &gt;&gt; Delete Account </i>s. <br/>
                    You will be automatically logged out after you confirm. 
                </BoxedSpaceAfter>
                
                You cannot recover from either action as the deletion happens immediately. Think carefully before you confirm.
                
            </Boxed>

</Typography>
)}

function contactUs() {
    return(
        <Typography>
            <Boxed>
            If you have any questions about our app, 
            the data we hold on you, or your data protection rights, <br/>
            please do not hesitate to contact us at any of the emails below.
            </Boxed>

            <Boxed>
                jiaqi_su@brown.edu
            </Boxed>
            <Boxed>
                jenny_yu2@brown.edu
            </Boxed>
            <Boxed>
                tianren_dong@brown.edu
            </Boxed>
            <Boxed>
                ashley_oelrich@brown.edu
            </Boxed>

        </Typography>
)}


function aboutTheApp() {
    return(
        <Typography>
            <BoxedSpaceAfter>
                Hi, welcome to College Trip Planner! ✈️
            </BoxedSpaceAfter>

            <Boxed>
                This website is created as the term project for Brown University's CS32 course. 
                Because we are collecting and processing your information in this website, we would like you to understand the data usage involved in this website before you proceed. 🧐
            </Boxed>

            <Boxed>
                As you signed up, you have provided us with the consent to collect and store your personal identification information and your travel information.
            </Boxed>

            <Boxed>
                Whenever you add or delete a college, your data is immediately stored into our encrypted database. 
                We process the colleges you enter using our algorithms to help you design your college trip. 
                Since the only purpose of this website is to generate optimized trips, you cannot restrict or 
                object data processing.
            </Boxed>
            
            <Boxed>
                In the My Account section below, you will be able to erase all your trip information by using Clear Data.
                You can also remove your account by using Delete Account. You cannot recover from either of those actions, so think carefully before you proceed!
            </Boxed>

            <Boxed>
                Hope you have a nice time exploring with our app! 👻
            </Boxed>

</Typography>
)}

