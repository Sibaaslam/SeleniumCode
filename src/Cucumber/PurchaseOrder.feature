@Regression
Feature: Purchase the order for E commerce website
 
   Background:
  	Given I landed on E commerce page
 
  Scenario Outline: Positive test of submitting order

    Given logged with username<name> and password<password>
    When I add product<product> 
    And checkout <product> and submit the order
    Then "THANKYOU FOR THE ORDER." message is displayed on the confirmation page


  

    Examples: 
      |name               | password  |product|
      |sibaaslam@gmail.com| Siba@1996 |ADIDAS ORIGINAL|
  
