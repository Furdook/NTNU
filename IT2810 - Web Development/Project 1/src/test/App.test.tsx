import { describe, test, beforeEach, expect } from "vitest";
import { QueryClient } from "@tanstack/react-query";
import { render } from "react-dom";
import App from "../App";

describe("App test", () => {
  let queryClient: QueryClient;

  beforeEach(() => {
    queryClient = new QueryClient();
  });

  test("API connection", async () => {
    const API = await fetch(
      "https://musicbrainz.org/ws/2/artist/?query=area:norway&fmt=json"
    );
    expect(API.status).toBe(200);
  });

  test("App renders", () => {
    it("renders without crashing", () => {
      render(<App />, document.createElement("div"));
      expect(document.getElementById("artist")).toBeDefined();
    });
  });

  test("Data is appended to DOM", async () => {
    it("appends data to DOM", async () => {
      render(<App />, document.createElement("div"));
      expect(document.getElementById("name")).toEqual("Edvard Grieg");
    });
  });
});
