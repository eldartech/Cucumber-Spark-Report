@allProducts
Feature: All Products

  Background: Navigate to WebOrder
    Given user in WebOrder

#  Scenario: Validate List of Products
#    When user logs in with "Tester" username and "test" password
#    And user navigates to View all products tab
#    Then user validates the product details table


  Scenario: Validate List of Products

    When user logs in with "Tester" username and "test" password
    And user navigates to View all products tab
    Then user validates the product details table
      | Product name | Price | Discount |
      | MyMoney      | $100  | 8%       |
      | FamilyAlbum  | $80   | 15%      |
      | ScreenSaver  | $20   | 10%      |
    And user validates url for all products

  Scenario: Validate List of Products

    When user logs in with "Admin" username and "test" password
    And user navigates to View all products tab
    Then user validates the product details table
      | Product name | Price | Discount |
      | MyMoney      | $100  | 8%       |
      | FamilyAlbum  | $80   | 15%      |
      | ScreenSaver  | $20   | 10%      |
    And user validates url for all products

  Scenario: Validate List of Products

    When user logs in with "Analyst" username and "test" password
    And user navigates to View all products tab
    Then user validates the product details table
      | Product name | Price | Discount |
      | MyMoney      | $100  | 8%       |
      | FamilyAlbum  | $80   | 15%      |
      | ScreenSaver  | $20   | 10%      |
    And user validates url for all products


