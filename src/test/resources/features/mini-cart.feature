Feature: Mini-Cart

  # avoid verbose tests
  Scenario: View product in mini-cart
    Given I open the homepage
    And I search products by "vase"
    And I store the name of the 1st product with Add to Cart button
#    And I click on the 1st Add to Cart button
#    When I expand the mini-cart
    Then the previously stored product name is displayed in mini-cart