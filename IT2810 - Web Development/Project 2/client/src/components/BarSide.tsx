import { useNavigate } from "react-router";
import "./styles/BarSide.css";
import "./styles/Buttons.css";
import uriParser from "../utilities/uriParser";
import { useEffect, useLayoutEffect, useState } from "react";
import ButtonProfile from "./ButtonProfile";
import ButtonNewListing from "./ButtonNewListing";
import ButtonNewProfile from "./ButtonNewProfile";
import ButtonLogin from "./ButtonLogin";
import { useUser } from "../utilities/context";

function BarSide() {
  const navigate = useNavigate();
  const { user } = useUser();
  const categories = [
    "Tjenester",
    "Møbler",
    "Verktøy",
    "Biler",
    "Elektronikk",
    "Eiendom",
    "Klær",
    "Fritid",
    "Alle Kategorier",
  ];

  const areas = [
    "Oslo",
    "Rogaland",
    "Møre og Romsdal",
    "Nordland",
    "Viken",
    "Innlandet",
    "Vestfold og Telemark",
    "Agder",
    "Vestland",
    "Trøndelag",
    "Troms og Finnmark",
  ];
  const [selectedAreas, setSelectedAreas] = useState<string[]>([]);
  const [selectedCategory, setSelectedCategory] = useState<string>(
    localStorage.getItem("selectedCategory") || ""
  );

  useEffect(() => {
    localStorage.setItem("selectedAreas", JSON.stringify(selectedAreas));
    localStorage.setItem(
      "selectedCategory",
      decodeURIComponent(selectedCategory)
    );
  }, [selectedAreas, selectedCategory]);

  useLayoutEffect(() => {
    const selectedAreas = JSON.parse(
      localStorage.getItem("selectedAreas") || "[]"
    );

    const selectedCategory = localStorage.getItem("selectedCategory") || "";
    const areas = document.querySelectorAll("input[name=area]");
    areas.forEach((area) => {
      if (selectedAreas.includes(area.id)) {
        area.setAttribute("checked", "true");
      }
    });

    const categories = document.querySelectorAll("input[name=category]");
    categories.forEach((category) => {
      if (category.id === selectedCategory) {
        category.setAttribute("checked", "true");
      }
    });
  }, []);

  return (
    <aside id="filters">
      {!user && <ButtonLogin />}
      {!user && <ButtonNewProfile />}
      {user && <ButtonNewListing />}
      {user && <ButtonProfile />}
      

      <form
        action=""
        id="filters__form"
        onChange={() => {
          let category = document.querySelector(
            'input[name="category"]:checked'
          )?.id;
          if (category === undefined && document.URL.includes("category")) {
            category = document.URL.split("category=")[1].split("&")[0];
          }

          const areas: string[] = [""];
          document
            .querySelectorAll("input[name=area]:checked")
            .forEach((area) => {
              areas.push(area.id);
            });

          setSelectedAreas(areas);
          if (category) {
            setSelectedCategory(category);
          }

          navigate(
            `${uriParser(
              undefined,
              category,
              areas.length > 0 ? areas : undefined
            )}`
          );

          window.location.reload();
        }}
      >
        <h3>Kategori</h3>
        <hr />
        <ul>
          {categories.map((category) => {
            return (
              <li key={category}>
                <input type="radio" id={category} name="category" />
                <label htmlFor={category}>{category}</label>
              </li>
            );
          })}
        </ul>
        <h3>Område</h3>
        <hr />
        <ul>
          {areas.map((area) => {
            return (
              <li key={area}>
                <input type="checkbox" id={area} name="area" />
                <label htmlFor={area}>{area}</label>
              </li>
            );
          })}
        </ul>
        <button type="submit" className="button filter-button">
          Filtrer annonser
        </button>

        <button
          onClick={() => {
            localStorage.setItem("selectedAreas", JSON.stringify([""]));
            localStorage.setItem("selectedCategory", "");
          }}
          className="button filter-button-all"
        >
          Se Alle Annonser
        </button>
      </form>
    </aside>
  );
}

export default BarSide;
