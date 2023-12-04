Feature: Test scenarios with user login

  Background:
    Given I am logged in with valid credentials
#
#  Scenario: Verify prime delivery items
#    When I search for "mobiles"
#    And I select prime delivery and print the first product details
#    Then the prime checkbox should be selected

  Scenario: Verify previous orders
    When I go to the orders page
    And I list past year orders
    Then the time period should have changed

  Scenario: Verify a new address is added or not
    When I go to the address page
    And I add a new address with the following details:
      | name      | phoneNumber | postalCode | line1                    | line2 |
      | userA | 1234567890  | 208001     | Flat 123, ABC Lane | Kanpur |
    Then the address should be added successfully

  Scenario: Verify a new payment method is added or not
    When I go to the payment methods page
    And I add a new payment method with the following details:
      | name      | phoneNumber | postalCode | line1                    | line2 | city      | stateOrRegion |
      | UserA | 1234567890  | 208001     | Flat 123, ABC Lane | Kanpur | Kanpur | uttar Pradesh     |
    Then the payment method should be added successfully
