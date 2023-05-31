@LoginFeature
Feature: Card Main Screen
  As Customer Service Bench Administrators, I want to login to Workbench

  @Author:Akhil @RiskPayLaterInstall @R-PI.003.01.01 @R-PI.003.01.02 @R-PI.003.01.03
  Scenario: Validate Card Main Screen - (Full Data Fields) - Get Card Number
    When user login workbench application for role "Risk - Pay Later Instalments"
    And user navigates to Free Search
    When user search cardDetails by accountNumber
    And user navigates to
      | Details |
    Then user click on CardNumber
    Then validate "Details" tab title
    And user validate CardMainScreen has "Card Data"
    And validate Full Data Fields
    Then click on the get clear pan
    And validate Card number is displayed
    And Logout from Workbench

  @Author:Jyoti @RiskPayNowEditPCI-Less @R-PNEL.003.01.01
  Scenario: Validate Card Main Screen
    When user login workbench application for role "Risk - Pay Later Instalments (PCI-Less)"
    And user navigates to Free Search
    When user search cardDetails by accountNumber
    And user navigates to
      | Details |
    Then user click on CardNumber
    Then validate "Details" tab title
    And user validate CardMainScreen has "Card Data"
    And validate Full Data Fields
    And Logout from Workbench

  @Author:Jyoti @RiskPayNowEditPCI-Less @R-PNEL.003.01.03
  Scenario: Validate Card Main Screen - Get Card Number {button should not be visible}
    When user login workbench application for role "Risk - Pay Later Instalments (PCI-Less)"
    And user navigates to Free Search
    When user search cardDetails by accountNumber
    And user navigates to
      | Details |
    Then user click on CardNumber
    Then validate "Details" tab title
    And user validate CardMainScreen has "Card Data"
    And validate Get clear pan button is not be visible
    And Logout from Workbench
