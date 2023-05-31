@CardMainScreenInstalments
Feature: Card Main Screen Instalments

  @Author:Akhil @RiskPayLaterInstall @R-PI.003.07.01 @R-PI.003.07.14
  Scenario: Card Main Screen - Instalments
    When user login workbench application for role "Risk - Pay Later Instalments (PCI-Less)"
    And user navigates to Free Search
    When user search cardDetails by accountNumber
    And user navigates to
      | Details |
    Then user click on CardNumber
    Then user navigates to
      | Instalments |
    Then validate "Instalments" tab title
    Then validate Card Instalments Data Field
    And user click on Close Instalment Plans button
    Then user validate the Close Instalment plans popup message
    And Logout from Workbench