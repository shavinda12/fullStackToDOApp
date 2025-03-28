// cypress.config.ts
const { defineConfig } = require('cypress');
const vitePreprocessor = require('cypress-vite');

module.exports = defineConfig({
  e2e: {
    setupNodeEvents(on) {
      on('file:preprocessor', vitePreprocessor());
    },
    baseUrl: "http://localhost:5173/", // Your Vite dev server URL
    supportFile: false,
    specPattern: "cypress/integration/**/*.ts", // Update this as per your test structure
    video: false, // Optional: Disable video recording for tests
  },
});