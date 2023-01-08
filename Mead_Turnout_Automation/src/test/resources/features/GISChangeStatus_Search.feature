@Chrome  @nagoor
Feature: Turnout Asset- GIS Change Status Field Search Functionality Validation

  Background: I should be able to validate the MEAD & Turnout pages titles
    Given I am on a "desktop" browser
    When I navigate to Mead Application
    Then I should be able to see the Title of the Page as "Master Engineering Asset Database"
    When I navigate to Turnout Asset Page
    Then I should be able to see the Subtitle of the page as "Turnout Search"

  @DataExist @GIS_Status @nagoor
  Scenario: I should be able to verify the GIS Change Status search functionality
    Given For Prefix Search Functionality, I use following values fetched from DB as my search criteria
      | GIS Conflated Status |
    When Press the search button
    Then Search results grid should show selected "GIS Conflated Status" value under Respective Column
