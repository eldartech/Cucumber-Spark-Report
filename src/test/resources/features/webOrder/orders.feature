Feature: New Order

  Background: Navigate and login
    Given user in WebOrder
    And user logs in with "Tester" username and "test" password

  @smokeWO
  Scenario: Validate Order Page Headers
    When user clicks on orders
    Then user validates content headers
      | Product Information |
      | Address information |
      | Payment Information |

  @smokeWO
  Scenario: Validate Order Page Content Labels
    When user clicks on orders
    Then user validates labels for Product Information
      | Product:*       |
      | Quantity:*      |
      | Price per unit: |
      | Discount:       |
      | Total:          |
    And user validates labels for Address Information
      | Customer name:* |
      | Street:*        |
      | City:*          |
      | State:          |
      | Zip:*           |
    And user validates labels for Payment Information
      | Card:*                |
      | Card Nr:*             |
      | Expire date (mm/yy):* |


  @functional
  Scenario Outline: Validate new order
    When user clicks on orders
    And user enters product info "<product>" and quantity of "<quantity>"
    And user enters address info "<name>", "<address>","<city>","<state>","<zip>"
    And user enters payment info "<card>", "<cardNumber>","<expDate>"
    Then user validates success message of "New order has been successfully added."
    Examples:
      | product     | quantity | name          | address       | city            | state    | zip   | card             | cardNumber     | expDate |
      | MyMoney     | 10       | David Johns   | 123 Main st   | Fort Lauderdale | Florida  | 60001 | Visa             | 11234234432342 | 05/26   |
      | FamilyAlbum | 5        | Jessica Johns | 465 State ave | New York        | New York | 36544 | American Express | 45635345345353 | 01/29   |
      | ScreenSaver | 2        | Anna Kenricks | 1 E York dr   | Chicago         | Illinois | 23534 | MasterCard       | 46732546494564 | 12/30   |

  @functional
  Scenario Outline: Validate new order
    When user navigates to "Order" tab
    And user enters product info "<product>" and quantity of "<quantity>"
    And user enters address info "<name>", "<address>","<city>","<state>","<zip>"
    And user enters payment info "<card>", "<cardNumber>","<expDate>"
    And user navigates to "View all orders" tab
    Then user validates created information "<product>", "<quantity>","<name>", "<address>","<city>","<state>","<zip>", "<card>", "<cardNumber>","<expDate>"
    Examples:
      | product     | quantity | name          | address       | city            | state    | zip   | card             | cardNumber     | expDate |
      | MyMoney     | 10       | David Johns   | 123 Main st   | Fort Lauderdale | Florida  | 60001 | Visa             | 11234234432342 | 05/26   |
      | FamilyAlbum | 5        | Jessica Johns | 465 State ave | New York        | New York | 36544 | American Express | 45635345345353 | 01/29   |
      | ScreenSaver | 2        | Anna Kenricks | 1 E York dr   | Chicago         | Illinois | 23534 | MasterCard       | 46732546494564 | 12/30   |


  Scenario Template: Validate Order Update
    When user edits first order from list of all orders
    And user updates address info "<name>", "<address>","<city>","<state>","<zip>"
    And user updates payment info "<card>", "<cardNumber>","<expDate>"
    And user clicks update button
    Then user validates updated information "<name>", "<address>","<city>","<state>","<zip>", "<card>", "<cardNumber>","<expDate>"
    Examples:
      | name          | address       | city            | state    | zip   | card             | cardNumber     | expDate |
      | David Johns   | 123 Main st   | Fort Lauderdale | Florida  | 60001 | Visa             | 11234234432342 | 05/26   |
      | Jessica Johns | 465 State ave | New York        | New York | 36544 | American Express | 45635345345353 | 01/29   |
      | Anna Kenricks | 1 E York dr   | Chicago         | Illinois | 23534 | MasterCard       | 46732546494564 | 12/30   |
