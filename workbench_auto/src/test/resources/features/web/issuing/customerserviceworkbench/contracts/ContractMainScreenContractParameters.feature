@ContractMainScreenContractParameters
Feature: Contract Main Screen Contract Parameters Features

  @Author:Akhil @RiskPayLaterInstall @R-PI.004.09.01 @R-PI.004.09.02 @R-PI.004.09.03
  Scenario: Contract Main Screen - Contract Parameters
    When user login workbench application for role "Risk - Pay Later Instalments"
    And user navigates to contracts
    Then user search contract by contract ID
    And user navigates to
      | Contract Tree       |
      | Contract Parameters |
      | All Visible         |
    And user validate Contract parameter records
    Then user Set Parameter
    And user validate set parameter popup message
    Then user click on clear
    Then user validate clear popup message
    And Logout from Workbench

  @Author:Vijay @RiskPayLaterInstalments @R-PI.004.09.04 @R-PI.004.12.01 @R-PI.004.12.02
  Scenario: Contract Main Screen - Contract Parameters - History
    When user login workbench application for role "Risk - Pay Later Instalments"
    And user navigates to contracts
    Then user search contract by contract ID
    And user navigates to
      | Contract Tree       |
      | Contract Parameters |
    Then validate "Contract Parameters" tab title
    And user navigates to
      | All Visible |
    Then update Contract Parameters "BCC Billing Day"
    And user navigates to
      | History |
    Then Validate Contract Parameters History
    And close "History" tab
    And close "Contract Parameters" tab
    And user navigates to
      | Change Log |
    Then validate "Change Log" tab title
    Then validate Contract Parameters log
    And user navigates to
      | View Data |
    Then validate "Details" tab title
    Then validate Change Log details
    And Logout from Workbench

  @Author:Vijay @RiskPayNowEditPCI-Less @R-PNEL.004.05.01
  Scenario: Contract Main Screen - Contract Parameters
    When user login workbench application for role "Risk - Pay Later Instalments (PCI-Less)"
    And user navigates to contracts
    Then user search contract by contract ID
    And user navigates to
      | Contract Tree       |
      | Contract Parameters |
      | All Visible         |
    Then validate "Contract Parameters" tab title
    Then user is able to see the record on "Contract Parameters" page
    And Logout from Workbench

  @Author:Vijay @RiskPayNowEditPCI-Less @R-PNEL.004.08.01
  Scenario: Contract Main Screen - Contract Parameters - History
    When user login workbench application for role "Risk - Pay Later Instalments (PCI-Less)"
    And user navigates to contracts
    Then user search contract by contract ID
    And user navigates to
      | Contract Tree |
      | Contract Parameters |
    Then validate "Contract Parameters" tab title
    And user navigates to
      | All Visible |
    Then update Contract Parameters "BCC Billing Day"
    And close "Contract Parameters" tab
    And user navigates to
      | Change Log |
    Then validate "Change Log" tab title
    Then validate Contract Parameters log


