import "./index.css"
import Button from '@material-ui/core/Button';

function Entrance() {
    return (
        <div class="mainContainer">
        <div class="textContainer">
            <h1 className="title">
                Design<br/>Your<br/>College<br/>Visits!</h1>
            <p className="subtitle">
                We help you create<br/>
                customized road trips<br/>
                to visit your favorite schools
            </p>
            <Button
                variant="contained"
                class="enterButton"
                style={{
                    fontSize: '20px',
                    fontFamily: "'Inter', sans-serif'",
                    fontWeight:'600px',}}>
                Start Exploring
            </Button>
        </div>
        </div>
        )
}

export default Entrance;