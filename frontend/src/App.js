import './App.css';
import Map from './components/map/index'
import Container from "./components/container/index"
import InfoCard from "./components/infocard";
import Login from "./views/sidebar/login";
import SignUp from "./views/sidebar/signup";
import UserHome from "./views/sidebar/userhome";
import MyColleges from "./views/mainView/myColleges";

function App() {
    return (
        <div className="App">
            <Container sidebarView={<SignUp/>} mainView={<Map className="Map"/>}/>
            {/*<Container sidebarView={<UserHome/>} mainView={<MyColleges/>}/>*/}
            {/*<InfoCard title={"Brown University"}*/}
            {/*          description={"Lizards are a widespread group of squamate reptiles, " +*/}
            {/*          "with over 6,000 species, ranging across all continents except Antarctica"}> </InfoCard>*/}
        </div>
    );
}

export default App;

