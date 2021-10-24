@PlanItTesting
Feature: Verify if user is able to create a contact
  @TestCase01
  Scenario: Verify user able to click on Contact page and provide feedback
    Given I Launch the browser
    Then I click on "Contact" Link
    And I enter "forename" field with "Planit"
    And I enter "surname" field with "Planit Testing"
    Then I Enter "email" field with "Planittesting@gmail.com" by id
    Then I Enter "message" field with "Feedback Message" by id
    Then I click on "Submit" Link
    And I verify the success message

  @TestCase02
  Scenario: Verify user is able to purchase Any number of toys and validate cart page
    Given I Launch the browser
    Then I click on "Shop" Link
    And I click on "Funny Cow" buy button 2 time
    And I click on "Fluffy Bunny" buy button 1 time
    Then I click on "Cart" Link
    Then I verify items in the cart

  @Testing3
  Scenario: Verify user is able to purchase Any number of toys and validate cart page and subtotal amount
    Given I Launch the browser
    Then I click on "Shop" Link
    And I click on "Stuffed Frog" buy button 2 time
    And I click on "Fluffy Bunny" buy button 5 time
    And I click on "Valentine Bear" buy button 3 time
    Then I click on "Cart" Link
    Then I verify items in the cart
