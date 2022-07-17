Feature: Sending/Deleting Message
  Scenario: Validate Sending/Deleting of message
    Given "slackSendMessage" endpoint with token "slackBearer" post message as a user "Eldar"
    """
    Groovy comes with a path expression language called GPath.
    """
    Then validate user is "Eldar"
    When delete message
    Then validate deleted message attribute "ok"

