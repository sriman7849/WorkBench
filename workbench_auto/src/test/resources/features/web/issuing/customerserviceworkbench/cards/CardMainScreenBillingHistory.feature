@CardMainScreenBillingHistory
Feature: Card Main Screen Billing History

  @Author:Akhil @RiskPayLaterInstall @R-PI.003.04.27
  Scenario: Card Main Screen - Billing History
    When user login workbench application for role "Risk - Pay Later Instalments"
    And user navigates to Free Search
    When user search cardDetails by accountNumber
    And user navigates to
      | Details |
    Then user click on CardNumber
    And user navigates to
      | Billing History |
    Then validate "Billing History" tab title
    And user validate Billing History page records
    And Logout from Workbench