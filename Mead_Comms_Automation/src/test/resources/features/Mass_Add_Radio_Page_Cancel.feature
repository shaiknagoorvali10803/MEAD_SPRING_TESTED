@Chrome
Feature: MEAD RADIO COMMUNICATIONS,Radio Mass Add Page Cancel Operation Functionality Validation

  Background: I should be able to validate the MEAD & Communications pages titles
    Given I am on a "desktop" browser
    When I navigate to Mead Application
    Then I should see the Title of the Page as "Master Engineering Asset Database"
    When I navigate to Radio communications page
    Then I should be able to see the Subtitle of the page as "Master Engineering Asset Database (Communications)"

  @DataExist @RadioMassAddCancel @nagoor
  Scenario Outline: I should be able to verify the Functionality of Cancel operation on Radio Mass Add Page
    Given I should navigate to Radio Mass Add Page by clicking Mass Radio option from Actions menu
    When I Navigated to "Radio Mass Add" page
    Then I Enter the Data in "<Radio Type>","<Nos>","<racfid>" and "<vehicleno>" in mandatory Fields
    And Press Cancel button on the UI page
    Then User should Navigated to Radio Search page with Title "Radio Search"

    Examples: 
      | Radio Type     | Nos | racfid | vehicleno |
      | Portable Radio |   5 | Z3594  |           |
