import { defineConfig } from "vitest/config";
import react from "@vitejs/plugin-react";

// https://vitejs.dev/config/
export default defineConfig({
  plugins: [react()],
  server: {
    open: true,
  },
  base: "/project2/",
  test: {
    globals: true,
    environment: "jsdom",
  },
});
