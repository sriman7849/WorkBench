Feature: Contract Main Screen Billing History

  @Author:Vijay @RiskPayLaterInstalments @R-PI.004.04.01
  Scenario: Contract Main Screen - Billing History
    When user login workbench application for role "Risk - Pay Later Instalments"
    And user navigates to contracts
    Then user search contract by contract ID
    And user navigates to
      | Contract Tree   |
      | Billing History |
    Then validate "Billing History" tab title
    And Logout from Workbench