import { ApolloClient, InMemoryCache } from "@apollo/client";

const client = new ApolloClient({
  uri: "http://it2810-57.idi.ntnu.no:4000/project2_server/",
  //   uri: "http://localhost:4000/", //for running on local
  cache: new InMemoryCache(),
});

export default client;
