@Chrome
Feature: Turnout Asset-Save Report Functionality Validation

  Background: I should be able to validate the MEAD & Turnout pages titles
    Given I am on a "desktop" browser
    When I navigate to Mead Application
    Then I should be able to see the Title of the Page as "Master Engineering Asset Database"
    When I navigate to Turnout Asset Page
    Then I should be able to see the Subtitle of the page as "Turnout Search"

  @DataExist @SaveReport @nagoor
  Scenario Outline: I should be able to verify the Save Report Functionality
    Given For Save Report Functionality, I use following values fetched from DB as my search criteria
      | Track State | Track Type | Track Status |
    When Press the search button
    Then Search Results should be displayed and add below columns to the result table
      | Track State | Track Type | Track Status |
    When Click Save Report button from actions menu,Enter Report name as "<Report Title>" and press the Save button
    Then "Report Saved Successful" message should be displayed on the UI page
    And Saved report should we avaialable in Saved Report dropdown
    When I hit  the Clear Filter button
    Then Current Filters should display default value "Asset Status=Active"
    And Search results should be Cleared and display "No records found."in results table
    When I select "<Report Title>" under Saved Reports dropdown
    Then Save Report Functionality Search Results should show values of selected criteria Under respective columns
      | Track State | Track Type | Track Status |

    Examples: 
      | Report Title        |
      | Active Track Report |
