@Chrome
Feature: Active Radios By Shipdate Search Functionality Validation

  Background: I should be able to validate the MEAD & Communications pages titles
    Given I am on a "desktop" browser
    When I navigate to Mead Application
    Then I should see the Title of the Page as "Master Engineering Asset Database"
    When I navigate to Radio communications page
    Then I should be able to see the Subtitle of the page as "Master Engineering Asset Database (Communications)"

  @DataExist @ActiveRadiosShipDate @nagoor
  Scenario Outline: I should be able to verify the Active Radios By Shipdate search functionality
    Given For Active Radios Search Functionality,I use following values fetched from DB between given "<Ship_From_Date>" and "<Ship_To_Date>" as my search criteria
      | Status | Power (in Watts) | Assigned Department | Radio Shop | Ship_From_Date | Ship_To_Date |
    When Press the search button
    Then Active Radio Search results should show values of selected criteria Under following Columns Respectively.
      | Status | Power (in Watts) | Assigned Department | Radio Shop | Ship Date |

    Examples: 
      | Ship_From_Date | Ship_To_Date |
      | 01/01/2004     | 11/01/2021   |
