import "./styles/Buttons.css";
import { useNavigate } from "react-router-dom";

function ButtonNewProfile() {
  const navigate = useNavigate();

  return (
    <button
      className="button new-profile-button"
      onClick={() => {
        navigate("/new-profile");
      }}
    >
      <img src="https://img.icons8.com/ios/25/add-user-male.png" alt="" />
      <span>Ny Profil</span>
    </button>
  );
}

export default ButtonNewProfile;
