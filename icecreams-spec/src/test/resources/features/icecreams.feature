Feature: Application icecreams

Scenario: Register a new ice cream
Given I have an ice cream payload
When I POST it to the /ice-creams endpoint
Then I receive a 201 status code
