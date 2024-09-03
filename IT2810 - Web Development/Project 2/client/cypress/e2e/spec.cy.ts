/// <reference types="cypress" />

describe("Loading page", () => {
  it("Loading the project", () => {
    cy.visit("http://localhost:5173/project2/");
  });
});

describe("Search from landing page", () => {
  it("Search from landing page", () => {
    cy.visit("http://localhost:5173/project2/");
    cy.get("#search-form__input").type("test");
    cy.get("#search-form__button").click();
    cy.get('[data-listing-id="656352e4ffd483244b0bf6b5"]', {
      timeout: 10000,
    }).should("exist"); // Finding the posting with title "Test Annonse" which has the listing id "656352e4ffd483244b0bf6b5".
  });
  it("Search from landing page with no results", () => {
    cy.visit("http://localhost:5173/project2/");
    cy.get("#search-form__input").type(
      "DetteErEnSåSpesikkTestTekstSomIkkeKommerTilÅFinneNoenResultater"
    );
    cy.get("#search-form__button").click();
    cy.get("#result-counter", { timeout: 10000 }).should(
      "contain",
      'Søk etter "DetteErEnSåSpesikkTestTekstSomIkkeKommerTilÅFinneNoenResultater" ga 0 resultater',
      { timeout: 10000 }
    ); // Finding the counter for the search results, wich should not yield any results.
    cy.get("#no-results").should("exist"); // Finding the header "Ingen resultater"
  });
});

describe("Category", () => {
  it("Check Tjenester", () => {
    cy.visit("http://localhost:5173/project2/");
    cy.contains("p", "Tjenester").click({ force: true }); // Clicking the category "Tjenester"
    cy.get('[data-listing-id="655f6a23ffd483244b0bf284"]', {
      timeout: 10000,
    }).should("exist"); // Finding the posting with title "Gressklipper" which has the listing id "655f6a23ffd483244b0bf284".
  });
});

describe("Search from results page", () => {
  it("Search from results page", () => {
    cy.visit("http://localhost:5173/project2/");
    cy.contains("p", "Tjenester").click({ force: true }); // Clicking the category "Tjenester"
    cy.get("#search-form__input").type("båt"); // Searching for "båt"
    cy.get("#search-form__button").click();
    cy.get('[data-listing-id="655f7f93ffd483244b0bf374"]', {
      timeout: 10000,
    }).should("exist"); // Finding the posting with title "Båtnaust på Femund" which has the listing id "655f7f93ffd483244b0bf374".
  });
});

describe("Find all listings and change page with paginator", () => {
  it("Find all listings and change page with paginator", () => {
    cy.visit("http://localhost:5173/project2/");
    cy.contains("p", "Tjenester").click({ force: true }); // Clicking the category "Tjenester"
    cy.contains("button", "Se Alle Annonser").click({ force: true }); // Clicking the button "Se Alle Annonser"
    cy.contains("button", "2").click({ force: true }); // Clicking the paginator button "2"
  });
});
