import './App.css';
import Map from './components/map/index'
import Sidebar from "./components/sidebar/index"

function App() {
  return (
    <div className="App">
        <Sidebar className="SideBar"/>
        <Map className="Map"/>
    </div>
  );
}

export default App;

