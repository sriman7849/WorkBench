@ContractMainScreenClassifiers
Feature: Contract Main Screen Classifiers

  @Author:Akhil @RiskPayLaterInstall @R-PI.004.08.01 @R-PI.004.08.04 @R-PI.004.08.05 @R-PI.004.08.07
  Scenario: Contract Main Screen - Classifiers - Set Yes (BCC Is contract Overlimit) - Set No (BCC Is contract Overlimit) - Classifiers Change Log
    When user login workbench application for role "Risk - Pay Later Instalments"
    And user navigates to contracts
    Then user search contract by contract ID
    And user navigates to
      | Contract Tree |
      | Classifiers   |
      | All           |
    Then validate "Classifiers" tab title
    Then  user validate Contract Classifiers records
    And user Set Classifiers to Set Yes (BCC Is contract Overlimit)
    Then user validate Set Classifiers popup message
    And user Set Classifiers to Set NO (BCC Is contract Overlimit)
    Then user validate Set Classifiers popup message
    And user navigates to
      | Classifier Change Log |
    Then user validate Contract Classifier Change Log records
    And Logout from Workbench

  @Author:Vijay
  @RiskPayNowEditPCI-Less @R-PNEL.004.04.01
  Scenario: Contract Main Screen - Classifiers
    When user login workbench application for role "Risk - Pay Later Instalments (PCI-Less)"
    And user navigates to contracts
    Then user search contract by contract ID
    And user navigates to
      | Contract Tree |
      | Classifiers   |
      | All           |
    Then validate "Classifiers" tab title
    Then user is able to see the record on "Classifiers" page
    And Logout from Workbench


