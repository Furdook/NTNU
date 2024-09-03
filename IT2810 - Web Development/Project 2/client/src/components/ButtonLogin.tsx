import "./styles/Buttons.css";
import { useNavigate } from "react-router-dom";

function ButtonLogin() {
  const navigate = useNavigate();

  return (
    <button
      className="button login-button"
      onClick={() => {
        navigate("/login");
      }}
    >
      <img src="https://img.icons8.com/ios/25/enter-2.png" alt="" />
      <span>Login</span>
    </button>
  );
}

export default ButtonLogin;
