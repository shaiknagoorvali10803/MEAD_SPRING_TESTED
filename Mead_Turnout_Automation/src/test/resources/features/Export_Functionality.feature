@Chrome @nagoor
Feature: Turnout Asset-Search Results Export Functinality Validation

  Background: I should be able to validate the MEAD & Turnout pages titles
    Given I am on a "desktop" browser
    When I navigate to Mead Application
    Then I should be able to see the Title of the Page as "Master Engineering Asset Database"
    When I navigate to Turnout Asset Page
    Then I should be able to see the Subtitle of the page as "Turnout Search"

  @DataExist @ExportReport @nagoor
  Scenario: I should be able to verify the Export Functionality
    Given For Export Functionality,I use following values fetched from DB as my search criteria
      | Track State | Track Type | Track Status |
    When Press the search button
    Then Search Results should be displayed and add below columns to the result table
      | Track State | Track Type | Track Status |
    When I Press the Export button on the UI page
    Then Search results should be exported to a file and Saved in root directory
    And Validate the Exported results against the search Criteria Under Respective Columns Names
      | Track State | Track Type | Track Status |
