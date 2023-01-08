@Chrome
Feature: MEAD RADIO COMMUNICATIONS,Inventory Status Field Restriction Validation

  Background: I should be able to validate the MEAD & Communications pages titles
    Given I am on a "desktop" browser
    When I navigate to Mead Application
    Then I should see the Title of the Page as "Master Engineering Asset Database"
    When I navigate to Radio communications page
    Then I should be able to see the Subtitle of the page as "Master Engineering Asset Database (Communications)"

  @DataExist @RadioMassAddInventoryField @nagoor
  Scenario: I should be able to verify Restriction on Inventory Status Field
    Given I should navigate to Radio Mass Add Page by clicking Mass Radio option from Actions menu
    When I Navigated to "Radio Mass Add" page
    And I Select the "Active" staus from drop down
    Then Inventory status drop down should show below options
      | Assigned | Admin In Process | Inventory New | Inventory Used | Spare |
    And I Select the "Out Of Service" staus from drop down
    Then Inventory status drop down should show below options
      | Lost | Retired | Stolen |
