Feature: Application icecreams

  Scenario: See icecreams
    Given I have the id of the ice cream I want
    When I GET it the /ice-creams/id endpoint
    Then I receive a 200 status code

  Scenario: Register a new ice cream
    Given I have an ice cream payload
    When I POST it to the /ice-creams endpoint
    Then I receive a 201 status code

  Scenario: Edit an ice cream
    Given I have an edited ice cream payload
    When I PUT it to the /ice-creams endpoint
    Then I receive a 200 status code

  Scenario: Partial edit of an ice cream that does not exist
    Given I have a partial edited ice cream payload
    When I PATCH it to the /ice-creams endpoint
    Then I receive a 200 status code

  Scenario: Delete an ice cream
    Given I have an id for the ice i want to delete
    When I DELETE it to the /ice-creams endpoint
    Then I receive a 204 status code
