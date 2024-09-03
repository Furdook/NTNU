import ButtonNewListing from "./ButtonNewListing";
import ButtonProfile from "./ButtonProfile";
import { useNavigate } from "react-router-dom";
import "./styles/BarNav.css";
import { useUser } from "../utilities/context";
import { useEffect, useState } from "react";
import ButtonLogin from "./ButtonLogin";
import ButtonNewProfile from "./ButtonNewProfile";

function BarNav() {
  const { user } = useUser();
  // breakpoint for which when the sidebar should be hidden
  const [menuOpen, setMenuOpen] = useState(
    window.innerWidth > 1350 ? true : false
  );
  // removes profile button and new listing button when the screen is too small
  const navigate = useNavigate();

  const changeNavState = () => {
    const input = document.getElementById("burger__input")! as HTMLInputElement;
    const logo = document.getElementById("logo")! as HTMLHeadingElement;

    if (input && logo) {
      if (window.innerWidth <= 1350 && document.URL.includes("results")) {
        logo.style.marginLeft = "3.5rem";
        input.disabled = false;
        setMenuOpen(false);
      } else {
        document;
        input.disabled = true;
        logo.style.marginLeft = "0";
        setMenuOpen(true);
      }
    }
  };

  // event listener on window resize to collapse sidebar when the screen is too small
  useEffect(() => {
    window.addEventListener("resize", changeNavState);
    return () => {
      window.removeEventListener("resize", changeNavState);
    };
  }, []);

  // animates the sidebar when the menuOpen state changes
  useEffect(() => {
    if (menuOpen) {
      document
        .getElementById("filters")
        ?.setAttribute("style", "transform: translateX(0);");
    } else {
      document
        .getElementById("filters")
        ?.setAttribute("style", "transform: translateX(-100%);");
    }
    return () => {};
  }, [menuOpen]);

  /**
   * Checks if the removeButtons state is false, returns profile and new listing buttons
   *
   * @returns the profile button and new listing button if the removeButtons state is false
   */

  return (
    <nav>
      {document.URL.includes("results") && (
        <label htmlFor="burger__input">
          <input
            id="burger__input"
            type="checkbox"
            checked={menuOpen}
            onChange={() => {
              setMenuOpen(!menuOpen);
            }}
            disabled={window.innerWidth > 1350 ? true : false}
          />
          <div id="burger">
            <span></span>
            <span></span>
            <span></span>
            <span></span>
          </div>
        </label>
      )}
      <h1
        id="logo"
        onClick={() => {
          navigate("./");
        }}
      >
        FUNN
      </h1>
      {!user && <ButtonLogin />}
      {!user && <ButtonNewProfile />}
      {user && <ButtonNewListing />}
      {user && <ButtonProfile />}
    </nav>
  );
}

export default BarNav;
