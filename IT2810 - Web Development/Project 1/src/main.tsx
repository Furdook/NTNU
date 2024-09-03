import React from "react";
import ReactDOM from "react-dom/client";
import App from "./App.tsx";
import {
  createBrowserRouter,
  createRoutesFromElements,
  Route,
  RouterProvider,
} from "react-router-dom";
import { QueryClient, QueryClientProvider } from "@tanstack/react-query";
import Artist from "./Artist.tsx";
import Likes from "./Likes.tsx";
import "./styles.css";

const router = createBrowserRouter(
  createRoutesFromElements(
    <>
      <Route path="project1/likes" element={<Likes />} />
      <Route path="project1/artist/:id" element={<Artist />} />
      <Route path="project1/*" element={<App />} />
    </>
  )
);

const queryClient = new QueryClient();

ReactDOM.createRoot(document.getElementById("root")!).render(
  <React.StrictMode>
    <QueryClientProvider client={queryClient}>
      <RouterProvider router={router} />
    </QueryClientProvider>
  </React.StrictMode>
);
