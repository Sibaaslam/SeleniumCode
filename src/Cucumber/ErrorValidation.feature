  @Errorvalidation
  Feature: Error validation 
  Scenario Outline: Capturing the error message
    Given I landed on E commerce page
    When logged with username<name> and password<password>
    Then "Incorrect email or password." msg should be displayed

    Examples: 
      | name                |password | 
      | sibaslam@gmail.com |Siba@1996| 
      
