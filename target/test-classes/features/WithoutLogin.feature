Feature: Shopping without Login

  Background:
    Given I am on the homepage

  @smoke
  Scenario: Left navigation to Mobiles
    When I navigate to Mobiles and back
    Then I should stay on the same page

  @smoke @sanity
  Scenario: Search for mobiles and get the last mobile's details
    When I search for "Mobile"
    Then I get the details of the last mobile on the page

  @sanity
  Scenario: Add a product from today's deals to the cart
    When I go to today's deals
    And I select the third product
    And I remember the quantity of the selected product
    And I add the product to the cart
    Then the product quantity in the cart should match the remembered quantity