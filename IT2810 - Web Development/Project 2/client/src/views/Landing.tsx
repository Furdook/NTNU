import BarSearch from "../components/BarSearch";
import { useNavigate } from "react-router-dom";
import "./styles/Landing.css";
import car from "../assets/car.svg";
import service from "../assets/service.svg";
import property from "../assets/property.svg";
import furniture from "../assets/furniture.svg";
import tools from "../assets/tools.svg";
import tech from "../assets/tech.svg";
import clothing from "../assets/clothing.svg";
import leisure from "../assets/leisure.svg";
import { useContext } from "react";
import { SearchContext } from "../utilities/context";

function Landing() {
  const navigate = useNavigate();
  const { setLandingSearchTerm } = useContext(SearchContext);
  const items = [
    {
      caption: "Tjenester",
      icon: service,
    },
    {
      caption: "Møbler",
      icon: furniture,
    },
    {
      caption: "Verktøy",
      icon: tools,
    },
    {
      caption: "Biler",
      icon: car,
    },
    {
      caption: "Elektronikk",
      icon: tech,
    },
    {
      caption: "Eiendom",
      icon: property,
    },
    {
      caption: "Klær",
      icon: clothing,
    },
    {
      caption: "Fritid",
      icon: leisure,
    },
  ];
  return (
    <>
      <h1 id="logoLanding">FUNN</h1>
      <BarSearch onSearch={setLandingSearchTerm} />
      <h2 id="categoryLabel">Kategorier</h2>
      <hr id="categorySeparator" />
      <ul id="categoriesLanding">
        {items.map((item) => {
          return (
            <li
              className="cardLanding"
              onClick={() => {
                localStorage.setItem("selectedCategory", item.caption || "");
                localStorage.setItem("selectedAreas", JSON.stringify([""]));
                navigate("./results/category=" + item.caption);
              }}
              key={item.caption}
            >
              <div tabIndex={1}>
                <img src={item.icon} alt="" />
                <p className="landingCategories">{item.caption}</p>
              </div>
            </li>
          );
        })}
      </ul>
    </>
  );
}

export default Landing;
