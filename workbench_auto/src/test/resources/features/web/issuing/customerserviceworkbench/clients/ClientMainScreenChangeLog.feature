Feature: Client Main Screen Change Log Feature

  @RiskPayLaterInstall @R-PI.005.09.01
    @Author:@AartiPatel
  Scenario: Validate client main Screen Change Log
    When user login workbench application for role "Risk - Pay Later Instalments"
    And user navigates to clients
    And user search client by client number
    And user navigates to
      | Details    |
      | Change Log |
    And user is able to see the record on "Change Log" page
    And validate "Change Log" tab title

  @RiskPayLaterInstall @R-PNEL.005.09.01
    @Author:@AartiPatel
  Scenario: Validate client main Screen Change Log
    When user login workbench application for role "Risk - Pay Now Edit (PCI-Less)"
    And user navigates to clients
    And user search client by client number
    And user navigates to
      | Details    |
      | Change Log |
    And user is able to see the record on "Change Log" page
    And validate "Change Log" tab title

  @@RiskPayLaterInstall @R-PI.005.09.02
  @Author:@AartiPatel
  Scenario: Validate client main Screen Change Log Screen
    When user login workbench application for role "Risk - Pay Later Instalments"
    And user navigates to clients
    And user search client by client number
    And user navigates to
      | Details    |
      | Change Log |
    And user navigates to
      | View Data |
    And user Validate the View Data Page