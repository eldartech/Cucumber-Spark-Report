Feature: i-Tunes Search Engine

  Scenario Outline: Validate Search Result
    Given endpoint with query parameter of "<artist>"
    Then validate status code is 200
    And validate content type is "text/javascript; charset=utf-8"
    Examples:
      | artist     |
      | Pink       |
      | Pink Floyd |
      | AC/DC      |
      | Bon Jovi   |