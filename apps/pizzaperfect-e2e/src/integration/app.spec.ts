import {
	getAddress,
	getAlternateLink,
	getBeginOrderButton,
	getHeading,
	getName,
} from '../support/app.po';

describe('Perform actions on home page', () => {
	beforeEach(() => cy.visit('/'));

	it('should display welcome message', () => {
		getHeading().contains('Welcome to Pizza Perfect! 🍕');
	});

	it('should show name and address', () => {
		getName().should('be.visible');
		getAddress().should('be.visible');
	});

	it('should have a disabled button', () => {
		getBeginOrderButton().should('be.visible');
		getBeginOrderButton().should('be.disabled');
	});

	it('should have an alternate link', () => {
		getAlternateLink().contains(
			`...alternatively, click here if you're a delivery driver`
		);
	});

	it('should enter text and navigate to the order', () => {
		getName().type('John Dough');
		getAddress().type('123 Example Drive, New Plymouth');
		getBeginOrderButton().click();
		cy.url().should('include', '/make-order');
	});
});
