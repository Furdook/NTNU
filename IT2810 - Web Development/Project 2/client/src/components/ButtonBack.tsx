import "./styles/ButtonBack.css";

function ButtonBack() {
  return (
    <button
      id="buttonBack"
      onClick={() => {
        window.history.back();
      }}
    >
      Tilbake
    </button>
  );
}

export default ButtonBack;
