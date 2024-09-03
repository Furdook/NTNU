import { gql } from "@apollo/client";

export const getListings = gql`
  query GetListingsQuery {
    getListings {
      id
      title
      price
      category
      datePosted
      location
    }
  }
`;

export const getListingsByCategory = gql`
  query Query($category: String!) {
    getListings(category: $category) {
      id
      title
      price
      datePosted
      location
    }
  }
`;

export const getListingsByArea = gql`
  query Query($area: [String]!) {
    getListings(area: $area) {
      id
      title
      price
      datePosted
      location
    }
  }
`;

export const getListingsByFiltersLists = gql`
  query GetListingByFiltersListQuery(
    $category: String!
    $locations: [String]!
  ) {
    getListingsByFiltersLists(category: $category, locations: $locations) {
      id
      title
      price
      category
      datePosted
      location
    }
  }
`;

export const getListingsByTitle = gql`
  query GetListingsByTitleQuery($input: titleSearchInput!) {
    getListingsByTitle(input: $input) {
      id
      title
      price
      category
      datePosted
      location
    }
  }
`;

export const getUser = gql`
  query Query($getUserId: ID!) {
    getUser(id: $getUserId) {
      id
      name
      listings {
        id
      }
    }
  }
`;

export const getListing = gql`
  query GetListingIdQuery($getListingId: ID!) {
    getListing(id: $getListingId) {
      id
      title
      description
      price
      category
      datePosted
      location
      address
      zipcode
      user {
        name
        phone
      }
    }
  }
`;

export const addNewUser = gql`
  mutation AddNewUserMutation(
    $name: String!
    $email: String!
    $phone: String!
    $password: String!
  ) {
    createUser(name: $name, email: $email, phone: $phone, password: $password) {
      name
      email
      phone
      id
    }
  }
`;
