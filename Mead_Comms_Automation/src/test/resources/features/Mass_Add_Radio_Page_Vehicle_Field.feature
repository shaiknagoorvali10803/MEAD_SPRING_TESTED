@Chrome
Feature: MEAD RADIO COMMUNICATIONS,Radio Mass Page Vehicle Field funcionality Validation

  Background: I should be able to validate the MEAD & Communications pages titles
    Given I am on a "desktop" browser
    When I navigate to Mead Application
    Then I should see the Title of the Page as "Master Engineering Asset Database"
    When I navigate to Radio communications page
    Then I should be able to see the Subtitle of the page as "Master Engineering Asset Database (Communications)"

  @DataExist @RadioMassAddVehicleField  @nagoor
  Scenario: I should be able to verify the Functionality of 'Vehicle'Field
    Given I should navigate to Radio Mass Add Page by clicking Mass Radio option from Actions menu
    When I Navigated to "Radio Mass Add" page
    And I Select the "Mobile Radio" From Radio Type dropdown
    Then "Assigned To" field with Vehicle radio button on UI Page should be displayed
    When I clicked the vehicle Radio button
    Then "Vehicle #" field should be displayed on UI page,enter "VM1234" in input field
