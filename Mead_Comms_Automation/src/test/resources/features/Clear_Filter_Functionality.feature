@Chrome
Feature: MEAD RADIO COMMUNICATIONS,Clear Filter Functionality Validation

  Background: I should be able to validate the MEAD & Communications pages titles
    Given I am on a "desktop" browser
    When I navigate to Mead Application
    Then I should see the Title of the Page as "Master Engineering Asset Database"
    When I navigate to Radio communications page
    Then I should be able to see the Subtitle of the page as "Master Engineering Asset Database (Communications)"

  @DataExist @ClearFilter @nagoor
  Scenario: I should be able to verify the Clear Filter functionality
    Given For Clear Filter Functionality ,I use following values fetched from DB as my search criteria
      | Status | Radio Type | Inventory Status |
    Then Current Filters should show the values of search criteria with corresponding column attributes
      | Status | Radio Type | Inventory Status |
    When Press the search button
    Then Search results should display records of selected criteria but not "No records found."
    When I hit  the Clear Filter button
    Then Current Filter should display default value "Status=Active"
    And Search results should display "No records found."
