export const typeDefs = `#graphlql
type Listing {
    id: ID!
    title: String!
    description: String!
    price: Int!
    category: String!
    address: String!
    zipcode: String!
    location: String!
    datePosted: String!
    dateLastEdited: String!
    user: User!
  }
  type User {
    id: ID!
    name: String!
    email: String!
    phone: String!
    password: String!
    listings: [Listing!]
  }

  type Query {
    getUsers: [User]
    getListings: [Listing]

    getUser(id: ID!): User
    getListing(id: ID!): Listing
    loginUser(email: String!, password: String!): User

    getListingsByCategory(category: String!): [Listing]
    getListingsByFilters(category: String, location: String): [Listing!]!
    getListingsByFiltersLists(category: String, locations: [String]): [Listing!]!
    getListingsByTitle(input: titleSearchInput): [Listing!]!}
  
  type Mutation {
    createUser(name: String!, email: String!, phone: String!, password: String!): User!
    createListing(title: String!, description: String!, price: Int!, 
        category: String!, address: String!, zipcode: String!, location: String!, datePosted: String!, user: String!): Listing!
    updateUser(id: ID!, name: String, email: String, phone: String, password: String): User!
    updateListing(id: ID!, title: String, description: String, price: Int, 
        category: String, location: String, dateLastEdited: String!): Listing!
  }

  input createUserInput {
    name: String!
    email: String!
    phone: String!
    password: String!
  }

  input createListingInput {
    title: String!
    description: String!
    price: Int!
    category: String!
    address: String!
    zipcode: String!
    location: String!
    datePosted: String!
    user: String!
  }

  input updateUserInput {
    name: String
    email: String
    phone: String
    password: String
  }

  input updateListingInput {
    title: String
    description: String
    price: Int
    category: String
    address: String
    zipcode: String
    location: String
    dateLastEdited: String!
  }

  input FilterInput {
    category: String
    location: [String]
  }

  input titleSearchInput {
    title: String!
  }
`;
