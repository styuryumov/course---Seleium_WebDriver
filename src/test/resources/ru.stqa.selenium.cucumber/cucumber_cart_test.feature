Feature: cucumber cart test

  @OpenPage
  Scenario: Test of the possibility of adding and removing a product from the cart
    Given choose '3' products from the list Most Popular, the selected products must be added to the cart
    And for some products it is possible to set the size 'Small'
    When the user has filled the cart with products
    Then user can remove products from cart


