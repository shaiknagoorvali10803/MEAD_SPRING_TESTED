@Chrome
Feature: MEAD RADIO COMMUNICATIONS,Save Report Functionality Validation

  Background: I should be able to validate the MEAD & Communications pages titles
    Given I am on a "desktop" browser
    When I navigate to Mead Application
    Then I should see the Title of the Page as "Master Engineering Asset Database"
    When I navigate to Radio communications page
    Then I should be able to see the Subtitle of the page as "Master Engineering Asset Database (Communications)"

  @DataExist @SaveReport @nagoor
  Scenario Outline: I should be able to verify the Save Report Functionality
    Given For Save Report Functionality, I use following values fetched from DB as my search criteria
      | Status | Radio Type | Inventory Status |
    When Press the search button
    Then Search Results should be displayed and Add given columns to the results table
      | Status | Radio Type | Inventory Status |
    When Click Save Report button from actions menu,Enter Report name as "<Report Title>" and press the Save button
    Then "Report Saved Successful" message should be displayed on the UI page
    When I hit  the Clear Filter button
    Then Current Filters should display default value "Status=Active"
    And Search results should be Cleared and display "No records found."in results table
    When I select "<Report Title>" under Saved Reports dropdown
    Then Save Report Functionality Search Results should show values of selected criteria Under respective columns
      | Status | Radio Type | Inventory Status |

    Examples: 
      | Report Title         |
      | Active Radios Report |
