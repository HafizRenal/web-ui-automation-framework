Feature: Login functionality

  @positive
  Scenario: Login with valid credentials
    Given I open the login page
    When I enter valid username and password
    Then I should see the secure area

  @negative
  Scenario: Login with invalid username
    Given I open the login page
    When I enter invalid username and valid password
    Then I should see an error message

  @negative
  Scenario: Login with invalid password
    Given I open the login page
    When I enter valid username and invalid password
    Then I should see an error message

  @boundary
  Scenario: Login with empty username
    Given I open the login page
    When I enter empty username and password
    Then I should see an error message

  @boundary
  Scenario: Login with empty password
    Given I open the login page
    When I enter username and empty password
    Then I should see an error message

  @boundary
  Scenario: Login with very long username
    Given I open the login page
    When I enter a very long username
    Then I should see an error message