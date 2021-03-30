import RoomIcon from '@material-ui/icons/Room';

const CollegeMarker = (props) => {
    const { college = {},
        showInfocard = () => { }
    } = props;
    // console.log(college);

    return (
        // <div
        //     onClick={showInfocard()}
        // >
        //     {college.name}
        // </div>
        <RoomIcon />
    )
}

export default CollegeMarker;