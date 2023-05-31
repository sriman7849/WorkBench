Feature: Client Main Screen Feature

  @Author:Viresh
  @RiskPayLaterInstall @R-PI.005.01.01 @R-PI.005.01.02
  Scenario: Validate client main screen
    When user login workbench application for role "Risk - Pay Later Instalments"
    And user navigates to clients
    And user search client by client number
    Then user navigates to "Details"
    And user validate the client main screen full details
    And Logout from Workbench

  @Author:Viresh
  @RiskPayLaterInstall @R-PI.005.02.01 @R-PI.005.02.02
  Scenario: User is able to set GDPR Marketing consents
    When user login workbench application for role "Risk - Pay Later Instalments"
    And user navigates to clients
    Then user search client by client number
    And user navigates to
      | Details |
      | GDPR    |
    Then validate "GDPR" tab title
    And user set the GDPR Marketing Consents
    And user validate the GDPR screen details

  @Author:Viresh
  @RiskPayLaterInstall @R-PI.005.04.01 @R-PI.005.04.02
  Scenario: user is able to see the debts details
    When user login workbench application for role "Risk - Pay Later Instalments"
    And user navigates to clients
    Then user search client by client number
    And user navigates to
      | Details |
      | Debts   |
    Then validate "Debts" tab title
    And user is able to see the record on "Debts" page
    Then user navigates to the link "Details" at the bottom of the tab "Debts"
    Then validate "Details" tab title
    And user validate debt details screen details

  @RiskPayNowEdit @R-PNEL.005.01.01
  @Author:@AartiPatel
  Scenario: Validate client main screen
    When user login workbench application for role "Risk - Pay Now Edit (PCI-Less)"
    And user navigates to clients
    And user search client by client number
    Then user navigates to "Details"
    And user validate the client main screen full details

  @RiskPayNowEdit @R-PNEL.005.02.01
  @Author:@AartiPatel
  Scenario: Validate client main screen GDPR Screen
    When user login workbench application for role "Risk - Pay Now Edit (PCI-Less)"
    And user navigates to clients
    Then user search client by client number
    Then user navigates to
      | Details |
      | GDPR    |
    Then validate "GDPR" tab title
    And user set the GDPR Marketing Consents
    And user validate the GDPR screen details