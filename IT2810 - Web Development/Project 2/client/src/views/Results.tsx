import BarSearch from "../components/BarSearch";
import Listing from "../components/Listing";
import "../components/styles/Paginator.css";
import "./styles/Results.css";
import { useCallback, useContext, useEffect, useState } from "react";
import Paginator from "../components/Paginator";
import BarSide from "../components/BarSide";
import { useLazyQuery, useQuery } from "@apollo/client";
import sortListings from "../utilities/sort";
import {
  getListingsByFiltersLists,
  getListingsByTitle,
} from "../utilities/schemas";
import { SearchContext, Listing as IListing } from "../utilities/context";

function Results() {
  // Get selected category from local storage
  const selectedCategory = () => {
    const waa = localStorage.getItem("selectedCategory") || "''";
    if (waa === "''") {
      return "";
    } else if (waa === "Alle Kategorier") {
      return "";
    } else {
      return waa;
    }
  };

  // Get selected areas from local storage
  const selectedAreas = () => {
    const hoo = JSON.parse(localStorage.getItem("selectedAreas") || '[""]');
    if (hoo && hoo.length === 0) {
      return [""];
    } else {
      return hoo;
    }
  };

  // Sets listings, chosenCategory and chosenLocation
  const [listings, setListings] = useState<IListing[]>([]);
  const [chosenCategory, setChosenCategory] = useState<string | null>(() =>
    selectedCategory()
  );
  const [chosenLocation, setChosenLocation] = useState<string[] | null>(() =>
    selectedAreas()
  );

  // Query by filters and assigning result to listings
  const { loading, error } = useQuery(getListingsByFiltersLists, {
    variables: {
      category: chosenCategory,
      locations: chosenLocation,
    },
    onCompleted: (data) => {
      // If listing is empty, and landingSearchTerm is not set, then set listing from query.
      if (listings.length === 0) {
        if (!landingSearchTerm) {
          setListings(data.getListingsByFiltersLists);
        }
      }
    },
  });

  // Pagination
  const [currentPage, setCurrentPage] = useState<number>(1);
  const itemsPerPage = 24;
  const start = (currentPage - 1) * itemsPerPage;
  const end = start + itemsPerPage;
  const handlePageChange = (pageNumber: number) => {
    setCurrentPage(pageNumber);
    window.scrollTo(0, 0);
  };

  // Search
  const [searchTerm, setSearchTerm] = useState("");
  const [activeSearch, setActiveSearch] = useState(false);
  const { landingSearchTerm, setLandingSearchTerm } = useContext(SearchContext);
  const [searchQuery, { data: searchData }] = useLazyQuery(getListingsByTitle);

  // Sets listings to search results when searchData is set.
  useEffect(() => {
    if (searchData) {
      setListings(searchData.getListingsByTitle);
    }
  }, [searchData]);

  // Does search on database with term given from searchbar
  const handleSearch = useCallback(
    async (term: string) => {
      setSearchTerm(term);
      setActiveSearch(true);
      searchQuery({
        variables: {
          input: {
            title: term,
          },
        },
      });
    },
    [searchQuery, setSearchTerm, setActiveSearch]
  );

  // Sets listings to allListings when landingSearchTerm is set.
  useEffect(() => {
    if (landingSearchTerm) {
      handleSearch(landingSearchTerm);
    }
    setLandingSearchTerm(null);
  }, [landingSearchTerm, handleSearch, setLandingSearchTerm]);

  // Handles changes in chosenCategory and chosenLocation
  useEffect(() => {
    if (
      Array.isArray(chosenLocation) &&
      chosenLocation.length > 1 &&
      chosenLocation[0] === ""
    ) {
      const updatedChosenLocation = chosenLocation.slice(1);
      setChosenLocation(updatedChosenLocation);
    } else {
      setChosenLocation(chosenLocation);
    }
    if (chosenCategory === "on") {
      setChosenCategory(null);
    } else {
      setChosenCategory(chosenCategory);
    }
  }, [chosenLocation, chosenCategory]);

  // Returns loading or error page if listings have not loaded
  if (loading) return <p>Loading...</p>;
  if (error) return <p>Error... Could not retrive data from database. </p>;

  return (
    <>
      <BarSearch onSearch={handleSearch} />
      <section id="result-info">
        <h3 id="result-counter">
          {activeSearch
            ? listings?.length === 1
              ? `Søk etter ${searchTerm} ga 1 resultat`
              : `Søk etter "${searchTerm}" ga ${
                  listings?.length || 0
                } resultater`
            : `${listings?.length || 0} resultater`}
        </h3>
        <h3 id="sort-label">Sorter</h3>
        <select
          name="sort"
          id="sort-options"
          data-testid="sort-options"
          onChange={() => {
            const selectedOption = document.getElementById(
              "sort-options"
            ) as HTMLSelectElement;
            if (listings) {
              setListings(sortListings(listings, selectedOption.value));
            }
          }}
        >
          <option value="standard">standard</option>
          <option value="price-low-high">laveste pris</option>
          <option value="price-high-low">høyest pris</option>
          <option value="newest">nyeste</option>
        </select>
      </section>

      <BarSide />
      <main id="results">
        {listings &&
          listings.slice(start, end).map((item: IListing) => {
            return <Listing {...item} key={item.id} />;
          })}
        {listings.length == 0 && <h1 id="no-results">Ingen resultater</h1>}
      </main>
      {listings.length != 0 && (
        <Paginator
          pageCount={Math.ceil(listings.length / itemsPerPage)}
          currentPage={currentPage}
          onPageChange={handlePageChange}
        />
      )}
    </>
  );
}

export default Results;
