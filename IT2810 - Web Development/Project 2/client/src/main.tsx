import ReactDOM from "react-dom/client";
import App from "./App.tsx";
import "./index.css";
import client from "./utilities/api.ts";
import { ApolloProvider } from "@apollo/client";
import { SearchProvider, UserProvider } from "./utilities/context.tsx";

ReactDOM.createRoot(document.getElementById("root")!).render(
  <ApolloProvider client={client}>
    <UserProvider>
      <SearchProvider>
        <App />
      </SearchProvider>
    </UserProvider>
  </ApolloProvider>,
);
