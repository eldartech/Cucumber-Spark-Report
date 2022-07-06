Feature: List of all orders Excel extraction

  Scenario: Validate all orders are extracted to excel sheet
    Given user in WebOrder
    And user logs in with "Tester" username and "test" password
    When user extracts all orders to excel sheet "Sheet1"
    Then user validates last record in excel sheet