@Chrome
Feature: Turnout Asset-Track Type Field Search Functionality Validation

  Background: I should be able to validate the MEAD & Turnout pages titles
    Given I am on a "desktop" browser
    When I navigate to Mead Application
    Then I should be able to see the Title of the Page as "Master Engineering Asset Database"
    When I navigate to Turnout Asset Page
    Then I should be able to see the Subtitle of the page as "Turnout Search"

  @DataExist @TrackTypeSingle @nagoor 
 Scenario: I should be able to verify the Track Type search functionality with single value
    Given For Track Type Search Functionality, I use following values fetched from DB for track status "single" value search criteria
      | Track Type |
    When Press the search button
    Then Search results grid should show selected "single" "Track Type" values under Respective Column

  @DataExist @TrackTypeMultiple @nagoor
  Scenario: I should be able to verify the Track Type search functionality with multiple values
    Given For Track Type Search Functionality, I use following values fetched from DB for track status "multiple" value search criteria
       | Track Type |
    When Press the search button
    Then Search results grid should show selected "multiple" "Track Type" values under Respective Column
    
    
    