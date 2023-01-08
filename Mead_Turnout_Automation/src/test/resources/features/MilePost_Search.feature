@Chrome  @nagoor
Feature: Turnout Asset-Mile Post Fields Search Functionality Validation

  Background: I should be able to validate the MEAD & Turnout pages titles
    Given I am on a "desktop" browser
    When I navigate to Mead Application
    Then I should be able to see the Title of the Page as "Master Engineering Asset Database"
    When I navigate to Turnout Asset Page
    Then I should be able to see the Subtitle of the page as "Turnout Search"

  @DataExist @FromMilepost @nagoor
  Scenario Outline: I should be able to verify the From Milepost search functionality
    Given For "From Milepost" Search Functionality, I enter "<From Milepost>" and "<To Milepost>" values in the respecrive input fileds
    When Press the search button
    Then Milepost search results grid should show selected value of search criteria under "MilePost" Column

    Examples:
      | From Milepost | To Milepost |
      |             1 |             |

  @DataExist @ToMilepost @nagoor
  Scenario Outline: I should be able to verify the To Milepost search functionality
    Given For "To Milepost" Search Functionality, I enter "<From Milepost>" and "<To Milepost>" values in the respecrive input fileds
    When Press the search button
    Then Milepost search results grid should show selected value of search criteria under "MilePost" Column

    Examples:
      | From Milepost | To Milepost |
      |               |         2 |

  @DataExist @Milepost @nagoor
  Scenario Outline: I should be able to verify the Milepost search functionality
    Given For "Milepost" Search Functionality, I enter "<From Milepost>" and "<To Milepost>" values in the respecrive input fileds
    When Press the search button
    Then Milepost search results grid should show selected value of search criteria under "MilePost" Column

    Examples:
      | From Milepost | To Milepost |
      |           1 |         2 |
