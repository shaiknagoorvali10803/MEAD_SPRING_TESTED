@Chrome  @nagoor
Feature: Turnout Asset-Track Prefix Field Search Functionality Validation

  Background: I should be able to validate the MEAD & Turnout pages titles
    Given I am on a "desktop" browser
    When I navigate to Mead Application
    Then I should be able to see the Title of the Page as "Master Engineering Asset Database"
    When I navigate to Turnout Asset Page
    Then I should be able to see the Subtitle of the page as "Turnout Search"

  @DataExist @PrefixMultiple @nagoor
  Scenario: I should be able to verify the Track Prefix search functionality with multiple values
    Given For Prefix Search Functionality, I use following values fetched from DB for Prefix "multiple" value search criteria
      | Prefix |
    When Press the search button
    Then Search results grid should show selected "multiple" "Prefix" values under Respective Column
    
    