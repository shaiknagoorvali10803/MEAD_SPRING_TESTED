@Chrome  @nagoor
Feature: Turnout Asset-Track Attributes Search Functionality Validation

  Background: I should be able to validate the MEAD & Turnout pages titles
    Given I am on a "desktop" browser
    When I navigate to Mead Application
    Then I should be able to see the Title of the Page as "Master Engineering Asset Database"
    When I navigate to Turnout Asset Page
    Then I should be able to see the Subtitle of the page as "Turnout Search"

  @DataExist @TrackAttributes @nagoor
  Scenario: I should be able to verify the Active Attributes search functionality
    Given For Track Attributes Search Functionality,I use following values fetched from DB as my search criteria
      | Prefix | Track Name |Track Status | Track Type | Track State |
    When Press the search button
    Then Track Attributes search results grid should show values of selected criteria Under below columns Respectively
    | Prefix | Track Name |Track Status | Track Type | Track State |