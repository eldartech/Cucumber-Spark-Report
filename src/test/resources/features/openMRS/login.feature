Feature: Open MRS Log In Test

  Scenario: Validate Login Functionality
    Given user is in OpenMRS login page
    When user logs in with valid credentials
      | admin | pass |
    Then user validates logged in user is "Billy Smith"