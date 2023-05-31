Feature: Client Main Screen Memos Feature

  @@RiskPayLaterInstall @R-PI.005.07.01
  @Author:@AartiPatel
  Scenario: Validate client main Screen Memos
    When user login workbench application for role "Risk - Pay Later Instalments"
    And user navigates to clients
    And user search client by client number
    And user navigates to
      | Details |
      | Memos   |
    And validate "Memos" tab title
    And user validate the Memos Screen

  @@RiskPayNowEdit @R-PNEL.005.07.01
  @Author:@AartiPatel
  Scenario: Validate client main Screen Memos
    When user login workbench application for role "Risk - Pay Now Edit (PCI-Less)"
    And user navigates to clients
    And user search client by client number
    And user navigates to
      | Details |
      | Memos   |
    And validate "Memos" tab title
    And user validate the Memos Screen

  @RiskPayLaterInstall @R-PI.005.07.02
  @Author:@AartiPatel
  Scenario: Validate create Memos from Client Main Screen
    When user login workbench application for role "Risk - Pay Later Instalments"
    And user navigates to clients
    And user search client by client number
    And user navigates to
      | Details |
      | Memos   |
    And user create new Memos
    Then user Validate the Memos

  @RiskPayLaterInstall @R-PI.005.07.03
  @Author:@AartiPatel
  Scenario: Validate Edit Memos from Client Main Screen
    When user login workbench application for role "Risk - Pay Later Instalments"
    And user navigates to clients
    And user search client by client number
    And user navigates to
      | Details |
      | Memos   |
    And User Edit the created Memo
    Then user Validate the Memos

  @RiskPayLaterInstall @R-PI.005.07.04
  @Author:@AartiPatel
  Scenario: Validate Delete Memos from Client Main Screen
    When user login workbench application for role "Risk - Pay Later Instalments"
    And user navigates to clients
    And user search client by client number
    And user navigates to
      | Details |
      | Memos   |
    And user Delete the newly created Memo
    Then user Validate the Deleted Memos Status

  @RiskPayLaterInstall @R-PI.005.07.05
  @Author:@AartiPatel
  Scenario: Validate change log Memos from Client Main Screen
    When user login workbench application for role "Risk - Pay Later Instalments"
    And user navigates to clients
    And user search client by client number
    And user navigates to
      | Details |
      | Memos   |
    And user create new Memos
    And user navigates to the link "Change Log" at the bottom of the tab "Memos"
    Then user is able to see the record on "Change Log" page
    Then validate "Change Log" tab title

  @RiskPayLaterInstall @R-PI.005.07.06
  @Author:@AartiPatel
  Scenario: Validate Details Memos from Client Main Screen
    When user login workbench application for role "Risk - Pay Later Instalments"
    And user navigates to clients
    And user search client by client number
    And user navigates to
      | Details |
      | Memos   |
    And user create new Memos
    And user navigates to the link "Details" at the bottom of the tab "Memos"
    Then validate "Details" tab title
    Then user Validate the Memos

