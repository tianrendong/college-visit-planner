import './App.css';
import Map from './components/map/index'
import Container from "./components/container/index"
import InfoCard from "./components/infocard";
import Login from "./views/login";

function App() {
  return (
    <div className="App">
        <Container sidebarView={<Login/>} mainView={<Map className="Map"/>}/>
        {/*<Map className="Map"/>*/}
        {/*<InfoCard title={"Brown University"}*/}
        {/*          description={"Lizards are a widespread group of squamate reptiles, " +*/}
        {/*          "with over 6,000 species, ranging across all continents except Antarctica"}> </InfoCard>*/}
    </div>
  );
}

export default App;

