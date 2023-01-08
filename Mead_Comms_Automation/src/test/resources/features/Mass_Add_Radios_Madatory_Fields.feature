@Chrome
Feature: MEAD RADIO COMMUNICATIONS,Mass Add Radios with Madatory Fields Functionality Validation

  Background: I should be able to validate the MEAD & Communications pages titles
    Given I am on a "desktop" browser
    When I navigate to Mead Application
    Then I should see the Title of the Page as "Master Engineering Asset Database"
    When I navigate to Radio communications page
    Then I should be able to see the Subtitle of the page as "Master Engineering Asset Database (Communications)"

  @DataExist @RadioMassAddMandatoryFields  @nagoor
  Scenario Outline: I should be able to verify the Functionality of Radio Mass Add with Mandatory Fields
    Given I should navigate to Radio Mass Add Page by clicking Mass Radio option from Actions menu
    When I Navigated to "Radio Mass Add" page
    Then I Enter the Data in "<Radio Type>","<Nos>","<racfid>" and "<vehicleno>" in mandatory Fields
    And Press Save to Add the New Radios to the Inventory
    Then UI page should navigated to "Radio InlineEdit" page by displaying newly created Radios
    And Validate the created records in Radio InlineEdit UI page and inventory

    Examples: 
      | Radio Type                    | Nos | racfid | vehicleno |
      | Base Radio                    |   5 |        |           |
      | DP Repeater                   |   5 |        |           |
      | End of Train                  |   5 |        |           |
      | Mobile Radio                  |   5 |        | VM1234    |