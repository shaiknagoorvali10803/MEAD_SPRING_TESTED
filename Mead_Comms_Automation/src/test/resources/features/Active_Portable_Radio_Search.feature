@Chrome 
Feature: Active Portable Radios Search Functionality Validation

  Background: I should be able to validate the MEAD & Communications pages titles
    Given I am on a "desktop" browser
    When I navigate to Mead Application
    Then I should see the Title of the Page as "Master Engineering Asset Database"
    When I navigate to Radio communications page
    Then I should be able to see the Subtitle of the page as "Master Engineering Asset Database (Communications)"

  @DataExist @ActivePortableRadios @nagoor
  Scenario: I should be able to verify the Active Portable Radios search functionality
    Given For Active Portable Radio Search Functionality,I use following values fetched from DB as my search criteria
      | Status | Radio Type |
    When Press the search button
    Then Active Portable Radio Search Results should show values of selected criteria Under columns "Status" and "Radio Type" Respectively
