import './index.css'
import { ReactComponent as College1 } from '../../../assets/collegeSVG/college1.svg'
import { ReactComponent as College2 } from '../../../assets/collegeSVG/college2.svg'
import { ReactComponent as College3 } from '../../../assets/collegeSVG/college3.svg'
import { ReactComponent as College4 } from '../../../assets/collegeSVG/college4.svg'
import { ReactComponent as College5 } from '../../../assets/collegeSVG/college5.svg'
import { ReactComponent as College6 } from '../../../assets/collegeSVG/college6.svg'

const icons = [
<College1 style={{width: 30, height: 30}}/>, 
<College2 style={{width: 30, height: 30}}/>, 
<College3 style={{width: 30, height: 30}}/>, 
<College4 style={{width: 30, height: 30}}/>, 
<College5 style={{width: 30, height: 30}}/>, 
<College6 style={{width: 30, height: 30}}/>]

const CollegeMarker = (props) => {
    const { college = {},
        showInfocard = () => { },
        index,
    } = props;

    const handleClick = () => {
        console.log("You clicked on it");
      };

    const getIcon = (i) => { return icons[i % icons.length]}

    return (
        <div className="collegeMarkerContainer" onClick={handleClick}>
            <div>{getIcon(index)}</div>
            <div className="collegeMarkerLabel">{college.name}</div>

        </div>
    )
}

export default CollegeMarker;