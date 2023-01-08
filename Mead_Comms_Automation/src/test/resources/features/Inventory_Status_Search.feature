@Chrome
Feature: MEAD RADIO COMMUNICATIONS,Inventory Status Search Functionality Validation

  Background: I should be able to validate the MEAD & Communications pages titles
    Given I am on a "desktop" browser
    When I navigate to Mead Application
    Then I should see the Title of the Page as "Master Engineering Asset Database"
    When I navigate to Radio communications page
    Then I should be able to see the Subtitle of the page as "Master Engineering Asset Database (Communications)"

  @DataExist @InventoryStatusSingle @nagoor
  Scenario: I should be able to verify the Inventory Status search functionality with single inventory value
    Given For Inventory Status Search Functionality, I use following value fetched from DB for "single" Inventory search criteria
      | Inventory Status |
    When Press the search button
    Then Inventory Status Search Results should show values of selected criteria  for "single" inventory under "Inventory Status" Column

  @DataExist @InventoryStatusMulti @nagoor
  Scenario: I should be able to verify the Inventory Status search functionality with multiple Inventory values
    Given For Inventory Status Search Functionality, I use following value fetched from DB for "multi" Inventory search criteria
      | Inventory Status |
    When Press the search button
    Then Inventory Status Search Results should show values of selected criteria  for "multi" inventory under "Inventory Status" Column
