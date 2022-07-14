Feature: Pet Store Pet Creation

  Scenario: Validate Pet Creation
    Given user provides request specifications
    When user creates a pet
    Then user validates status code of 200
    And user validates response body