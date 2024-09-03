import { useState } from "react";
import uriParser from "../utilities/uriParser";
import "./styles/BarSearch.css";
import { useNavigate } from "react-router-dom";

function BarSearch({ onSearch }: { onSearch: (searchTerm: string) => void }) {
  const navigate = useNavigate();
  const [inputValue, setInputValue] = useState("");
  const handleSubmit = (event: React.MouseEvent<HTMLButtonElement>) => {
    event.preventDefault();
    onSearch(inputValue);
    setInputValue("");
    const currentURI = document.URL;

    // gets the input element from the searchbar
    const input = document.getElementById(
      "search-form__input"
    ) as HTMLInputElement;

    let url = document.URL;
    if (url.includes("search")) {
      url = url.split("search")[0];
    }

    let category = url.split("/")[4];

    if (category?.includes("&")) {
      category = category.split("&")[0];
    }

    /**
     * Handles the URI based filtered searches using the searchbar
     */

    if (currentURI.endsWith("project2/")) {
      navigate(`${uriParser(input.value, "Alle%20Kategorier", [""])}`);
    } else {
      navigate(`${uriParser(input.value, undefined, undefined)}`);
    }
  };

  return (
    <form id="search-form">
      <input
        id="search-form__input"
        type="text"
        placeholder="Søk etter produkt"
        value={inputValue}
        onChange={(e) => {
          setInputValue(e.target.value);
        }}
      />
      <button id="search-form__button" onClick={handleSubmit}>
        Søk
      </button>
    </form>
  );
}

export default BarSearch;
