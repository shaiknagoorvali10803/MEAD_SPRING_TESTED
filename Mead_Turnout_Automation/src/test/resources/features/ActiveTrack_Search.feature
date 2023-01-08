@Chrome
Feature: Turnout Asset-Active Track Search Functionality Validation

  Background: I should be able to validate the MEAD & Turnout pages titles
    Given I am on a "desktop" browser
    When I navigate to Mead Application
    Then I should be able to see the Title of the Page as "Master Engineering Asset Database"
    When I navigate to Turnout Asset Page
    Then I should be able to see the Subtitle of the page as "Turnout Search"

  @DataExist @ActiveTrack @nagoor 
 Scenario: I should be able to verify the Active Track search functionality
    Given For "Active" Track Search Functionality,I use following values fetched from DB as my search criteria
      |Track State|Track Type|Track Status|
    When Press the search button
    Then "Active" Track search results grid should show values of selected criteria Under below columns Respectively
     |Track State|Track Type|Track Status|
    