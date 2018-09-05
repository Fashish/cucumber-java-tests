Feature: Checkout feature E2E

  Scenario: As a logged in user I would like to verify I can see the checkout summary of an item on the web
    Given I am on Depop
    And I am logged in
    When I navigate to an item of single variety
    And I can see the buy now button
    And I click on the buy now button
    Then I can see the checkout summary for the item
