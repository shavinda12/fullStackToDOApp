/// <reference types="cypress" />

describe("MainScreen E2E Tests", () => {
    beforeEach(() => {

      // Visit the MainScreen page
      cy.visit("http://localhost:5173/",{ failOnStatusCode: false });
    });
  


    
    it("renders the task form with input fields", () => {
      cy.get("input[placeholder='Title']").should("be.visible");
      cy.get("textarea[placeholder='Description']").should("be.visible");
      cy.get("button").contains("Add").should("be.visible");
    });
  
    it("shows validation errors when submitting an empty form", () => {
      cy.get("button").contains("Add").click();
      cy.get("p").should("contain", "Title is required");
      cy.get("p").should("contain", "Description is required");
    });
  
    it("adds a new task successfully", () => {
      cy.get("input[placeholder='Title']").type("Test Task");
      cy.get("textarea[placeholder='Description']").type("This is a test description.");
      cy.get("button").contains("Add").click();
  
      // Check if success toast appears
      cy.contains("Task added successfully!").should("be.visible");
  
      // Ensure the task appears in the task list
      cy.contains("Test Task").should("be.visible");
      cy.contains("This is a test description.").should("be.visible");
    });

    it("verifies the task appears in the task list", () => {
      // Ensure the task appears in the task list
      cy.contains("Test Task").should("be.visible");
      cy.contains("This is a test description.").should("be.visible");
    });

    it("marks the task as done", () => {
      // Mark the task as done (adjust selector for the 'Done' button as per your implementation)
      cy.contains("Test Task")  // Find the task by title
        .parent()  // Traverse to the task container
        .find("button")  // Find the Done button inside the task container
        .contains("Done")  // Locate the button by its text
        .click();  // Click the button to mark the task as done


      cy.wait(4000);   
    
      // Check if the success update message appears
      cy.contains(/.*successfully updated !/).should("be.visible");
    
      // // Optionally, check if the task's "done" status is visually updated (e.g., by checking for a class or status text)
      // cy.contains("Test Task").should("have.class", "completed"); // Or check any visual indication of the task being done
    });
  
    

  });