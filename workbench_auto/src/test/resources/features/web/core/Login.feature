@LoginFeature
Feature: Login tests
  As Customer Service Bench Adminstrator, I want to login to Workbench

  @Risk-PayLaterInstal @R-PI.001.01.01 @R-PI.001.01.02 @R-PI.001.01.03
  Scenario: Unsuccessful Login(enter wrong password) - Successful Login - Successful Logout
    Given Login into workbench application with wrong Password
    Then Validate error message
    Given user login workbench application for role "Risk - Pay Later Instalments"
    Then Validate home page is available
    When Logout from Workbench
    Then Validate Login page is available

  @ManuallyLockUserFeature
  @RiskPayLaterInstall @R-PI.001.02.01  @R-PI.001.02.02
  Scenario: Lock the User manually and unlock it
    When user login workbench application for role "Issuer Admin"
    Then validate availability of lock button
    Then User click on User icon and lock the user manually
    Then unlock logged in user
    When User unlocked successfully
    Then validate lock button is available again for user
    And Logout from Workbench

