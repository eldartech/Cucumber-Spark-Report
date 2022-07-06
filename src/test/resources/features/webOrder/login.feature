#Feature: WebOrder Login Functionality
##  Rule: positive scenarios
#    Background: Navigate to WebOrder
#      Given user in WebOrder
#
##    Scenario: Login Positive Test
##      When user enters username "Tester"
##      And user enters password "test"
##      Then user validates the title is "Web Orders"
##      And user validates the url contains "weborders"
##      And user validates login info
##
##  Rule: negative scenarios
##    Background: Navigate to WebOrder
##      Given user in WebOrder
#
#    Scenario: Login With Incorrect Credentials
#      When user enters username "test"
#      And user enters password "ttest"
#      Then user validates the error message "Invalid Login or Password."
#
#  Scenario: Login With Correct Username and incorrect password
#      When user enters username "Tester"
#      And user enters password "ttest"
#      Then user validates the error message "Invalid Login or Password."
#
#  Scenario: Login With incorrect Username and correct password
#      When user enters username "TTester"
#      And user enters password "test"
#      Then user validates the error message "Invalid Login or Password."
#
#  Scenario: Login With No Username and correct password
#      When user enters password "test"
#      Then user validates the error message "Invalid Login or Password."
#
#  Scenario: Login With Correct Username and no password
#      When user enters username "Tester"
#      And user clicks on login button
#      Then user validates the error message "Invalid Login or Password."
#
#    Example: Login With no Username and no password
#      And user clicks on login button
#      Then user validates the error message "Invalid Login or Password."