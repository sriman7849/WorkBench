
Feature: Validation of Addresses

  @Author:Viresh
  @RiskPayLaterInstall @R-PI.005.05.01 @R-PI.005.05.02
  Scenario: user is able to see the debts details
    When user login workbench application for role "Risk - Pay Later Instalments"
    And user navigates to clients
    Then user search client by client number
    And user navigates to
      | Details   |
      | Addresses |
      | All       |
    Then validate "Addresses" tab title
    And user is able to see the record on "Addresses" page
    Then user navigates to the link "Details" at the bottom of the tab "Addresses"
    Then validate "Details" tab title
    And user validate client Address details

  @RiskPayNowEdit @R-PNEL.005.05.01
  @Author:@AartiPatel
  Scenario: Validate client main screen Address Screen
    When user login workbench application for role "Risk - Pay Now Edit (PCI-Less)"
    And user navigates to clients
    Then user search client by client number
    And user navigates to
      | Details   |
      | Addresses |
      | All       |
    Then validate "Addresses" tab title
    And user is able to see the record on "Addresses" page
