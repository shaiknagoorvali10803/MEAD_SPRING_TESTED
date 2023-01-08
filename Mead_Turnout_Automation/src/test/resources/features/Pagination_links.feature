@Chrome
Feature: Turnout Asset-Pagination Links Functionality Validation

  Background: I should be able to validate the MEAD & Turnout pages titles
    Given I am on a "desktop" browser
    When I navigate to Mead Application
    Then I should be able to see the Title of the Page as "Master Engineering Asset Database"
    When I navigate to Turnout Asset Page
    Then I should be able to see the Subtitle of the page as "Turnout Search"

  @DataExist @Pagination @nagoor
  Scenario: I should be able to verify the Pagination Links Functionality
    Given For Pagination Links functionality, I use following values fetched from DB as my search criteria
     |Track State|Track Type|Track Status|
    When Press the search button
    Then Search results pagination links should work for navigating to the different pages
    And Number of records in Search results table should match with no.of records in dropdown
      | 10 | 20 | 25 | 35 | 45 | 60 |

   