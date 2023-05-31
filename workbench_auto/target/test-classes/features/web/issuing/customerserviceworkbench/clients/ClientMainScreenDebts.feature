Feature: Client Main Screen Debts Feature

  @RiskPayNowEdit @R-PNEL.005.04.01
  @Author:@AartiPatel
  Scenario: Validate client main screen Debts Screen
    When user login workbench application for role "Risk - Pay Now Edit (PCI-Less)"
    And user navigates to clients
    Then user search client by client number
    And user navigates to
      | Details   |
      | Debts |
    Then validate "Debts" tab title
    And user is able to see the record on "Debts" page