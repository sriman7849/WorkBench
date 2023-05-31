Feature: Contract Main Screen Features

  @Author:Jyoti @RiskPayLaterInstalments @R-PI.004.01.01 @R-PI.004.01.02
  Scenario: Validate contract main screen(Full Data Fields) with PCI
    When user login workbench application for role "Risk - Pay Later Instalments"
    And user navigates to contracts
    And user navigates to "By Contract ID"
    Then user search contract by contract ID
    And user navigates to "Contract Tree"
    Then validate "Details" tab title
    And validate the contract main screen details
    And user validate contract main screen Full Data Fields
    And Logout from Workbench

  @Author:Vijay @RiskPayNowEditPCI-Less @R-PNEL.004.01.01
  Scenario: Contract Main Screen with PCI-Less
    When user login workbench application for role "Risk - Pay Later Instalments (PCI-Less)"
    And user navigates to contracts
    And user navigates to "By Contract ID"
    Then user search contract by contract ID
    And user navigates to
      | Contract Tree |
    Then validate "Details" tab title
    And validate the contract main screen details
    And Logout from Workbench