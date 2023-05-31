Feature: Search Contract Features

  @Author:Jyoti @RiskPayLaterInstalments @R-PI.002.02.01
  Scenario: Search by Contract by Contract Number
    When user login workbench application for role "Risk - Pay Later Instalments"
    And user navigates to contracts
    And user navigates to
      | By Number |
    Then user search contract by contract Number
    And user is able to see the record

  @Author:Jyoti @RiskPayLaterInstalments @R-PI.002.02.03
  Scenario: Search by Contract by Contract ID
    When user login workbench application for role "Risk - Pay Later Instalments (PCI-Less)"
    And user navigates to contracts
    And user navigates to
      | By Contract ID |
    Then user search contract by contract ID
    And user is able to see the record

  @Author:Jyoti @RiskPayLaterInstalments @R-PI.002.02.04
  Scenario: Search by Contract by CBS Number
    When user login workbench application for role "Risk - Pay Later Instalments (PCI-Less)"
    And user navigates to contracts
    And user navigates to
      | by CBS Number |
    Then user search contract by CBS Number
    And user is able to see the record

  @RiskPayLaterInstall @R-PI.002.02.02
  @Author:@AartiPatel
  Scenario: Search by Contract By Bank Account Number
    When user login workbench application for role "Risk - Pay Later Instalments"
    And user navigates to contracts
    And user search contract by contract ID
    And user navigates to
      | Contract Tree |
    And get the Bank Code to search in Contract Search Page
    Then user validate contract by Bank Account Number