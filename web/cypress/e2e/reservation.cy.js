describe('reservations app', () => {
    beforeEach(() => {
        // Cypress starts out with a blank slate for each test
        // so we must tell it to visit our website with the `cy.visit()` command.
        // Since we want to visit the same URL at the start of all our tests,
        // we include it in our beforeEach function so that it runs before each test
        cy.visit('http://localhost:5173')
    })

    it('displays two todo items by default', () => {
        // We use the `cy.get()` command to get all elements that match the selector.
        // Then, we use `should` to assert that there are two matched items,
        // which are the two default items.
        const navLinks = cy.get('[data-test-id="movie-link"]');
        navLinks.should('have.length', 2);
        cy.get('[data-test-id="movie-title"]').should('have.length', 2);
        navLinks.first().click();

        cy.get('[data-test-id="movie-date"]').select(1);
        cy.get('[data-test-id="movie-time"]').select(1);
        cy.get('[data-test-id="movie-seat"]').last().check();
        cy.get('[data-test-id="movie-payment-link"]').click();

        cy.get('[data-test-id="movie-card-type"]').select(1);
        cy.get('[data-test-id="movie-card-name"]').type("John Doe");
        cy.get('[data-test-id="movie-card-number"]').type("1234 1234 1234 1234");
        cy.get('[data-test-id="movie-card-cvv"]').type("123");
        cy.get('[data-test-id="movie-pay"]').click();

        cy.get('[data-test-id="movie-payment-success-title"]').contains("Successfully Reserved");
        cy.get('[data-test-id="movie-payment-success-message"]').contains("you ticket(s) for Avengers on 2023-01-20 at 2:00 PM are reserved. Your seat number are A1, A2");
    })
})