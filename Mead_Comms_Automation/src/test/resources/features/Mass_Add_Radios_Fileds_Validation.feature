@Chrome
Feature: MEAD RADIO COMMUNICATIONS,Mass Add Radios Page Editable Fields Validation

  Background: I should be able to validate the MEAD & Communications pages titles
    Given I am on a "desktop" browser
    When I navigate to Mead Application
    Then I should see the Title of the Page as "Master Engineering Asset Database"
    When I navigate to Radio communications page
    Then I should be able to see the Subtitle of the page as "Master Engineering Asset Database (Communications)"

  @DataExist @RadioMassAddFieldsValidation  @nagoor
  Scenario: I should be able to verify All the Fields of Radio Mass Add UI Page
    Given I should navigate to Radio Mass Add Page by clicking Mass Radio option from Actions menu
    When I Navigated to "Radio Mass Add" page
    Then Validate the following Editable text Fileds
      | Number of Radios to be added | Purchase Order Number | Shipment Tracking Number | Max Project Id |
    And Validate the Following Drops Down fields
      | Model | Radio Type | Status | Inventory Status | Manufacturer | Assigned Department | Radio Shop |
    And Validte the Following Date Fields
      | Ship Date | Received Date | Purchase Order Date |
