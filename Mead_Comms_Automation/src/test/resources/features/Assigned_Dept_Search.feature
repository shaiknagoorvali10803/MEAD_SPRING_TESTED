@Chrome
Feature: Assigned Deaprtment Search Functionality Validation

  Background: I should be able to validate the MEAD & Communications pages titles
    Given I am on a "desktop" browser
    When I navigate to Mead Application
    Then I should see the Title of the Page as "Master Engineering Asset Database"
    When I navigate to Radio communications page
    Then I should be able to see the Subtitle of the page as "Master Engineering Asset Database (Communications)"

  @DataExist @AssignedDeptSingle @nagoor 
  Scenario: I should be able to verify the Assigned Department radio search functionalities with single Department
    Given For Assigned Department Search Functionality, I use following value fetched from DB for "single" Department search criteria
      | Assigned Departmet |
    When Press the search button
    Then Assigned Department Search Results should show values of selected criteria  for "single" deaprtment under "Assigned Department" Column

  @DataExist @AssignedDeptMulti @nagoor
  Scenario: I should be able to verify the Assigned Department radio search functionalities with multiple Department values
    Given For Assigned Department Search Functionality, I use following value fetched from DB for "multi" Department search criteria
      | Assigned Departmet |
    When Press the search button
    Then Assigned Department Search Results should show values of selected criteria  for "multi" deaprtment under "Assigned Department" Column
