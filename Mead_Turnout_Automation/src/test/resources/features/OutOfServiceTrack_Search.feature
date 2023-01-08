@Chrome
Feature: Turnout Asset-OutOfService Track Search Functionality Validation

  Background: I should be able to validate the MEAD & Turnout pages titles
    Given I am on a "desktop" browser
    When I navigate to Mead Application
    Then I should be able to see the Title of the Page as "Master Engineering Asset Database"
    When I navigate to Turnout Asset Page
    Then I should be able to see the Subtitle of the page as "Turnout Search"

  @DataExist @OutOfService @nagoor 
 Scenario: I should be able to verify the OutOfService Track search functionality
    Given For "Out of Service" Track Search Functionality,I use following values fetched from DB as my search criteria
      |Track State|Track Type|Track Status|
    When Press the search button
    Then "Out of Service" Track search results grid should show values of selected criteria Under below columns Respectively
     |Track State|Track Type|Track Status|
    