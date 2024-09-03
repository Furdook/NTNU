import "./styles/Buttons.css";
import { useNavigate } from "react-router-dom";

function ButtonNewAd() {
  const navigate = useNavigate();

  return (
    <button
      className="button new-listing-button"
      onClick={() => {
        navigate("/new-posting");
      }}
    >
      <img src="https://img.icons8.com/ios/25/plus--v1.png" alt="" />
      <span>Ny Annonse</span>
    </button>
  );
}

export default ButtonNewAd;
