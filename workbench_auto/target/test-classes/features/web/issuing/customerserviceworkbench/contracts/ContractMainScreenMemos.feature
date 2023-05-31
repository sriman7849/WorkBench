Feature: Contract Main Screen Feature

 @RiskPayLaterInstall @RiskPayNowEditPCI-Less @R-PI.004.10.01
   @Author:@AartiPatel
  Scenario: Validate Contract main Screen Memos
    When user login workbench application for role "Risk - Pay Later Instalments"
    And user navigates to contracts
    And user search contract by contract ID
    And user navigates to
      | Contract Tree |
      | Memos   |
    And validate "Memos" tab title
    And user validate the Memos Screen


 @RiskPayNowEditPCI-Less @R-PNEL.004.06.01
    @Author:@Vijay
  Scenario: Validate Contract main Screen Memos
    When user login workbench application for role "Risk - Pay Now Edit (PCI-Less)"
    And user navigates to contracts
    And user search contract by contract ID
    And user navigates to
      | Contract Tree |
      | Memos   |
    And validate "Memos" tab title
    And user validate the Memos Screen

  @RiskPayLaterInstall @R-PI.004.10.02
  @Author:@AartiPatel
  Scenario: Validate create Memos from Contract Main Screen
    When user login workbench application for role "Risk - Pay Later Instalments"
    And user navigates to contracts
    And user search contract by contract ID
    And user navigates to
      | Contract Tree |
      | Memos         |
    And user create new Memos
    Then user Validate the Memos

  @RiskPayLaterInstall @R-PI.004.10.03
  @Author:@AartiPatel
  Scenario: Validate Edit Memos from Contract Main Screen
    When user login workbench application for role "Risk - Pay Later Instalments"
    And user navigates to contracts
    And user search contract by contract ID
    And user navigates to
      | Contract Tree |
      | Memos         |
    And User Edit the created Memo
    Then user Validate the Memos

  @RiskPayLaterInstall @R-PI.004.10.04
  @Author:@AartiPatel
  Scenario: Validate Delete Memos from Contract Main Screen
    When user login workbench application for role "Risk - Pay Later Instalments"
    And user navigates to contracts
    And user search contract by contract ID
    And user navigates to
      | Contract Tree |
      | Memos         |
    And user Delete the newly created Memo
    Then user Validate the Deleted Memos Status

  @RiskPayLaterInstall @R-PI.004.10.05
  @Author:@AartiPatel
  Scenario: Validate Change Log Memos from Contract Main Screen
    When user login workbench application for role "Risk - Pay Later Instalments"
    And user navigates to contracts
    And user search contract by contract ID
    And user navigates to
      | Contract Tree |
      | Memos         |
    And user create new Memos
    And user navigates to the link "Change Log" at the bottom of the tab "Memos"
    Then user is able to see the record on "Change Log" page
    Then validate "Change Log" tab title

  @RiskPayLaterInstall  @R-PI.004.10.06
  @Author:@AartiPatel
  Scenario: Validate Details  from Contract Main Screen
    When user login workbench application for role "Risk - Pay Later Instalments"
    And user navigates to contracts
    And user search contract by contract ID
    And user navigates to
      | Contract Tree |
      | Memos         |
    And user create new Memos
    And user navigates to the link "Details" at the bottom of the tab "Memos"
    And validate "Details" tab title
    And user Validate the Memos