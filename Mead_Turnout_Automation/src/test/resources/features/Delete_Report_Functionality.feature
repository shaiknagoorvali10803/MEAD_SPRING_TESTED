@Chrome
Feature: Turnout Asset-Delet Report Functionality Validation

  Background: I should be able to validate the MEAD & Turnout pages titles
    Given I am on a "desktop" browser
    When I navigate to Mead Application
    Then I should be able to see the Title of the Page as "Master Engineering Asset Database"
    When I navigate to Turnout Asset Page
    Then I should be able to see the Subtitle of the page as "Turnout Search"

  @DataExist @DeleteReport @nagoor
  Scenario Outline: I should be able to verify the Delete Report Functionality
    Given For Delete Report Functionality, I use following values fetched from DB as my search criteria
      | Track Type | Track Status | Track State |
    When Press the search button
    Then Save the Search results with Report name "<Report Title>"
    When I click on Delete Shared Report option from the Actions menu
    Then Delete Shared Report Window Should show the Saved Report "<Report Title>"
    When I click on the Delete Icon for the report to be deleted
    Then Selected Report should be deleted ,Deleted Report should not be displayed Saved Reports dropdown.

    Examples: 
      | Report Title                |
      | Active Radios Delete Report |
