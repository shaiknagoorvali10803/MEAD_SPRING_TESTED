@Chrome
Feature: MEAD RADIO COMMUNICATIONS,Mass Add Radios with Missing Madatory Fields Functionality Validation

  Background: I should be able to validate the MEAD & Communications pages titles
    Given I am on a "desktop" browser
    When I navigate to Mead Application
    Then I should see the Title of the Page as "Master Engineering Asset Database"
    When I navigate to Radio communications page
    Then I should be able to see the Subtitle of the page as "Master Engineering Asset Database (Communications)"

  @DataExist @RadioMassAddMissingMandatoryFields @nagoor
  Scenario Outline: I should be able to verify the Mass Add Radios with Missing  Madatory Fields
    Given I should navigate to Radio Mass Add Page by clicking Mass Radio option from Actions menu
    When I Navigated to "Radio Mass Add" page
    Then I Enter the Data in with "<Field>" Missed
    And Press Save to Add the New Radios to the Inventory
    Then System Should the Warning Message "Please enter all the fields marked (*)"

    Examples: 
      | Field           |
      | RadioType       |
      | NoOfRadios      |
      | Status          |