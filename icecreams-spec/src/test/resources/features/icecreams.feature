Feature: Application icecreams

  Scenario: Register a new ice cream
    Given I have an ice cream payload
    When I POST it to the /ice-creams endpoint
    Then I receive a 201 status code

  Scenario: Edit an ice cream
    Given I have an edited ice cream payload
    When I PUT it to the /ice-creams endpoint
    Then I receive a 200 status code

  Scenario: Delete an ice cream
    Given I have an id for the ice i want to delete
    When I DELETE it to the /ice-creams endpoint
    Then I receive a 204 status code
