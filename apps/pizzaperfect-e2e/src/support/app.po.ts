export const getHeading = () => cy.get('h1');

export const getName = () => cy.get('input[type="text"]:first');
export const getAddress = () => cy.get('input[type="text"]:last');

export const getBeginOrderButton = () => cy.get('button');

export const getAlternateLink = () => cy.get('a');
