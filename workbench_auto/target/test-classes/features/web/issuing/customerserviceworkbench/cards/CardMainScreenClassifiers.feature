@CardMainScreenClassifiers
Feature: Card Main Screen Classifiers

  @Author:Akhil @RiskPayLaterInstall @R-PI.003.09.01 @R-PI.003.09.04 @R-PI.003.09.05 @R-PI.003.09.07
  Scenario: Card Main Screen - Classifiers - Set No (ABU) - Set Yes (ABU) - Classifier Change Log
    When user login workbench application for role "Risk - Pay Later Instalments"
    And user navigates to Free Search
    Then user search cardDetails by accountNumber
    And user navigates to
      | Details |
    Then user click on CardNumber
    And user validate CardMainScreen has "Card Data"
    And user navigates to
      | Classifiers |
      | All         |
    Then validate "Classifiers" tab title
    Then user validate Card Classifiers records
    And user Set Classifiers to Set No (ABU)
    Then user validate Set Classifiers popup message
    And user Set Classifiers to Set Yes(ABU)
    Then user validate Set Classifiers popup message
    And user navigates to
      | Classifier Change Log |
    Then user validate Card Classifier Change Log records
    And Logout from Workbench

  @Author:Jyoti @RiskPayNowEditPCI-Less @R-PNEL.003.05.01
  Scenario: Card Main Screen - Classifiers
    When user login workbench application for role "Risk - Pay Later Instalments (PCI-Less)"
    And user navigates to Free Search
    Then user navigates to
      | Account |
    When user search cardDetails by accountNumber
    And user navigates to
      | Details |
    Then user click on CardNumber
    And user validate CardMainScreen has "Card Data"
    And user navigates to
      | Classifiers |
      | All         |
    Then validate "Classifiers" tab title
    Then user validate Card Classifiers records
    And Logout from Workbench