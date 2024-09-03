import { test, expect } from "vitest";
import { render, fireEvent } from "@testing-library/react";
import { MockedProvider } from "@apollo/client/testing";
import Results from "../src/views/Results";
import React from "react";
import { getListingsByFiltersLists } from "../src/utilities/schemas";
import { BrowserRouter as Router } from "react-router-dom";

// Mock data for four listings
// Mock data is a small selecting of data from the database, with a modification to the price of anit-plen
const mockdata = [
  {
    request: {
      query: getListingsByFiltersLists,
      variables: {
        category: "",
        locations: [""],
      },
    },
    result: {
      data: {
        getListingsByFiltersLists: [
          {
            id: "655f7cf5ffd483244b0bf343",
            title: "Tohånds Laksefiskestang",
            price: 2500,
            category: "Verktøy",
            location: "Troms og Finnmark",
            datePosted: "1700697600000",
          },
          {
            id: "655f7f13ffd483244b0bf370",
            title: "Anti-plen",
            price: 499999,
            category: "Eiendom",
            location: "Troms og Finnmark",
            datePosted: "1700697600000",
          },
          {
            id: "655f7f93ffd483244b0bf374",
            title: "Båtnaust på Femund",
            price: 50000,
            category: "Eiendom",
            location: "Innlandet",
            datePosted: "1698796800000",
          },
          {
            id: "65638366ffd483244b0bf71a",
            title: "Plate: Sydney... Olympic City",
            price: 190,
            category: "Fritid",
            location: "Innlandet",
            datePosted: "1701020517985",
          },
        ],
      },
    },
  },
];

// Mock data for loading
const mockdataLoading = [
  {
    request: {
      query: getListingsByFiltersLists,
      variables: {
        category: "",
        locations: [""],
      },
    },
    delay: 100,
    result: {
      data: {
        getListingsByFiltersLists: [],
      },
    },
  },
];

// Mock data for the error fetching listings
const mockdataError = [
  {
    request: {
      query: getListingsByFiltersLists,
      variables: {
        category: "",
        locations: [""],
      },
    },
    error: new Error("Not able to load listings from database"),
  },
];

// Test the loading state
test("Displays loading state", async () => {
  const { findByText } = render(
    <MockedProvider mocks={mockdataLoading} addTypename={false}>
      <Results />
    </MockedProvider>
  );

  // Check if loading text is displayed
  expect(await findByText("Loading...")).toBeDefined();
});

// Test the error state
test("Displays error state", async () => {
  const { findByText } = render(
    <MockedProvider mocks={mockdataError} addTypename={false}>
      <Results />
    </MockedProvider>
  );

  // Check if error text is displayed
  expect(
    await findByText("Error... Could not retrive data from database.")
  ).toBeDefined();
});

// Test not selecting sorting category, which should default to standard
test("Not selecting sorting category", async () => {
  const { findByTestId } = render(
    <MockedProvider mocks={mockdata} addTypename={false}>
      <Router>
        <Results />
      </Router>
    </MockedProvider>
  );

  const select = (await findByTestId("sort-options")) as HTMLSelectElement;
  expect(select.value).toBe("standard");
});

// Test selecting price-low-high sorting category
test("Selecting price-low-high sorting category", async () => {
  const { findByTestId } = render(
    <MockedProvider mocks={mockdata} addTypename={false}>
      <Router>
        <Results />
      </Router>
    </MockedProvider>
  );

  const select = (await findByTestId("sort-options")) as HTMLSelectElement;
  fireEvent.change(select, { target: { value: "price-low-high" } });
  expect(select.value).toBe("price-low-high");
});

// Test selecting price-high-low sorting category
test("Selecting price-high-low sorting category", async () => {
  const { findByTestId } = render(
    <MockedProvider mocks={mockdata} addTypename={false}>
      <Router>
        <Results />
      </Router>
    </MockedProvider>
  );

  const select = (await findByTestId("sort-options")) as HTMLSelectElement;
  fireEvent.change(select, { target: { value: "price-high-low" } });
  expect(select.value).toBe("price-high-low");
});

// Test selecting newest sorting category
test("Selecting newest sorting category", async () => {
  const { findByTestId } = render(
    <MockedProvider mocks={mockdata} addTypename={false}>
      <Router>
        <Results />
      </Router>
    </MockedProvider>
  );

  const select = (await findByTestId("sort-options")) as HTMLSelectElement;
  fireEvent.change(select, { target: { value: "newest" } });
  expect(select.value).toBe("newest");
});

// Test sorting by price-low-high
test("Sorting by price-low-high", async () => {
  const { findByTestId, findAllByTestId } = render(
    <MockedProvider mocks={mockdata} addTypename={false}>
      <Router>
        <Results />
      </Router>
    </MockedProvider>
  );

  // Sorting by price low to high
  const select = (await findByTestId("sort-options")) as HTMLSelectElement;
  fireEvent.change(select, { target: { value: "price-low-high" } });
  expect(select.value).toBe("price-low-high");

  // Get the rendered listings
  const listings = await findAllByTestId("listing-item");

  // Defines expected order
  const expectedOrder = [
    "65638366ffd483244b0bf71a", // Plate: Sydney... Olympic City
    "655f7cf5ffd483244b0bf343", // Tohånds Laksefiskestang
    "655f7f93ffd483244b0bf374", // Båtnaust på Femund
    "655f7f13ffd483244b0bf370", // Anti-plen
  ];

  // Get the IDs of the rendered listings
  const renderedOrder = listings.map((listing) =>
    listing.getAttribute("data-listing-id")
  );

  // Matches rendred order to what is expected
  expect(renderedOrder).toEqual(expectedOrder);
});

// Test sorting by price-high-low
test("Sorting by price-high-low", async () => {
  const { findByTestId, findAllByTestId } = render(
    <MockedProvider mocks={mockdata} addTypename={false}>
      <Router>
        <Results />
      </Router>
    </MockedProvider>
  );

  // Sorting by price low to high
  const select = (await findByTestId("sort-options")) as HTMLSelectElement;
  fireEvent.change(select, { target: { value: "price-high-low" } });
  expect(select.value).toBe("price-high-low");

  // Get the rendered listings
  const listings = await findAllByTestId("listing-item");

  // Defines expected order
  const expectedOrder = [
    "655f7f13ffd483244b0bf370", // Anti-plen
    "655f7f93ffd483244b0bf374", // Båtnaust på Femund
    "655f7cf5ffd483244b0bf343", // Tohånds Laksefiskestang
    "65638366ffd483244b0bf71a", // Plate: Sydney... Olympic City
  ];

  // Get the IDs of the rendered listings
  const renderedOrder = listings.map((listing) =>
    listing.getAttribute("data-listing-id")
  );

  // Matches rendred order to what is expected
  expect(renderedOrder).toEqual(expectedOrder);
});

// Test sorting by newest
test("Sorting by newest", async () => {
  const { findByTestId, findAllByTestId } = render(
    <MockedProvider mocks={mockdata} addTypename={false}>
      <Router>
        <Results />
      </Router>
    </MockedProvider>
  );

  // Sorting by newest
  const select = (await findByTestId("sort-options")) as HTMLSelectElement;
  fireEvent.change(select, { target: { value: "newest" } });
  expect(select.value).toBe("newest");

  // Get the rendered listings
  const listings = await findAllByTestId("listing-item");

  // Defines expected order
  const expectedOrder = [
    "65638366ffd483244b0bf71a", // Plate: Sydney... Olympic City
    "655f7cf5ffd483244b0bf343", // Tohånds Laksefiskestang
    "655f7f13ffd483244b0bf370", // Anti-plen
    "655f7f93ffd483244b0bf374", // Båtnaust på Femund
  ];

  // Get the IDs of the rendered listings
  const renderedOrder = listings.map((listing) =>
    listing.getAttribute("data-listing-id")
  );

  // Matches rendred order to what is expected
  expect(renderedOrder).toEqual(expectedOrder);
});

// Test sorting by date, then sorting by standard again to get the original order
test("Sorting by standard after sorting by newest", async () => {
  const { findByTestId, findAllByTestId } = render(
    <MockedProvider mocks={mockdata} addTypename={false}>
      <Router>
        <Results />
      </Router>
    </MockedProvider>
  );

  // Get the rendered listings
  const listings = await findAllByTestId("listing-item");

  // Get the IDs of the rendered listings the first time
  const firstRenderedOrder = listings.map((listing) =>
    listing.getAttribute("data-listing-id")
  );

  // Sorting by newest
  const select = (await findByTestId("sort-options")) as HTMLSelectElement;
  fireEvent.change(select, { target: { value: "newest" } });
  expect(select.value).toBe("newest");

  // Get the rendered listings when sorted by newest
  const newestListings = await findAllByTestId("listing-item");

  // Get the IDs of the rendered listings when sorting by newest
  const newestRenderedOrder = newestListings.map((listing) =>
    listing.getAttribute("data-listing-id")
  );

  // Defines expected order when sorting by newest
  const expectedOrder = [
    "65638366ffd483244b0bf71a", // Plate: Sydney... Olympic City
    "655f7cf5ffd483244b0bf343", // Tohånds Laksefiskestang
    "655f7f13ffd483244b0bf370", // Anti-plen
    "655f7f93ffd483244b0bf374", // Båtnaust på Femund
  ];

  // Matches rendred order with newst sorting to what is expected
  expect(newestRenderedOrder).toEqual(expectedOrder);

  // Sorting by standard again
  fireEvent.change(select, { target: { value: "standard" } });
  expect(select.value).toBe("standard");

  // Get the rendered listings when sorted by standard again
  const standardListings = await findAllByTestId("listing-item");

  // Get the IDs of the rendered listings when sorted by standard
  const standardRenderedOrder = standardListings.map((listing) =>
    listing.getAttribute("data-listing-id")
  );

  // Matches rendred order to what is expected
  expect(standardRenderedOrder).toEqual(firstRenderedOrder);
});
