@ClientMainScreenClassifiers
Feature: Client Main Screen Classifiers

  @Author:Akhil @RiskPayLaterInstall @R-PI.005.06.01 @R-PI.005.06.02 @R-PI.005.06.03 @R-PI.005.06.05
  Scenario: Client Main Screen - Classifiers - Set Yes (GDPR Data Erasure Requested) - Set no (GDPR Data Erasure Requested) - Classifiers Change Log
    When user login workbench application for role "Risk - Pay Later Instalments"
    And user navigates to clients
    And user search client by client number
    And user navigates to
      | Details     |
      | Classifiers |
      | All         |
    Then validate "Classifiers" tab title
    Then user validate Client Classifiers records
    And user Set Classifiers to Set Yes (GDPR Data Erasure Requested)
    Then user validate Set Classifiers popup message
    And user Set Classifiers to Set No (GDPR Data Erasure Requested)
    Then user validate Set Classifiers popup message
    And user navigates to
      | Classifier Change Log |
    Then user validate Client Classifier Change Log records
    And Logout from Workbench

  @@RiskPayNowEdit @R-PNEL.005.06.01
  @Author:@AartiPatel
  Scenario: Client Main Screen - Classifiers
    When user login workbench application for role "Risk - Pay Now Edit (PCI-Less)"
    And user navigates to clients
    And user search client by client number
    And user navigates to
      | Details     |
      | Classifiers |
      | All         |
    Then validate "Classifiers" tab title
    And user is able to see the record on "Classifiers" page