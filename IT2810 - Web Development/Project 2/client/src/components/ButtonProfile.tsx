import { useNavigate } from "react-router-dom";
import "./styles/Buttons.css";

function ButtonProfile() {
  const navigate = useNavigate();

  return (
    <button
      className="button profile-button"
      onClick={() => {
        navigate("/profile");
      }}
    >
      <img src="https://img.icons8.com/ios/25/user-male-circle--v1.png" alt="" />
      <span>Profile</span>
    </button>
  );
}

export default ButtonProfile;
