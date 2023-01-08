@Chrome
Feature: Turnout Asset-Clear Filter Functionality Validation

  Background: I should be able to validate the MEAD & Turnout pages titles
    Given I am on a "desktop" browser
    When I navigate to Mead Application
    Then I should be able to see the Title of the Page as "Master Engineering Asset Database"
    When I navigate to Turnout Asset Page
    Then I should be able to see the Subtitle of the page as "Turnout Search"

  @DataExist @ClearFilter @nagoor
  Scenario: I should be able to verify the Clear Filter functionality
    Given For Clear Filter Functionality ,I use following values fetched from DB as my search criteria
      | Track State | Track Type | Track Status |
    Then Current Filters should show the values of search criteria with corresponding column attributes
      | Track State | Track Type | Track Status |
    When Press the search button
    Then Search results should display records of selected criteria but not "No records found."
    When I hit  the Clear Filter button
    Then Current Filter should display default value "Asset Status=Active"
    And Search results should display "No records found."
