@CardMainScreenCardDataFeature
Feature: Card Main Screen-Card Data

  @Author:Suvarna @RiskPayLaterInstall @R-PI.003.02.01
  Scenario: Validate Card Main Screen-Card Data
    When user login workbench application for role "Risk - Pay Later Instalments"
    And user navigates to Free Search
    When user search cardDetails by accountNumber
    And user navigates to
      | Details |
    Then user click on CardNumber
    And user navigates to
      | Card Data |
    Then validate "Card Data" tab title
    Then Validation on Card Data page
    And Logout from Workbench

  @Author:Jyoti @RiskPayNowEditPCI-Less @R-PNEL.003.02.01
  Scenario: Validate Card Main Screen-Card Data
    When user login workbench application for role "Risk - Pay Later Instalments (PCI-Less)"
    And user navigates to Free Search
    When user search cardDetails by accountNumber
    And user navigates to
      | Details |
    Then user click on CardNumber
    And user navigates to
      | Card Data  |
    Then validate "Card Data" tab title
    Then Validation Full Data on Card Data page
    And Logout from Workbench

  @Author:Suvarna @RiskPayLaterInstall @R-PI.003.02.02
  Scenario: Validate Card Data-Full Data Fields
    When user login workbench application for role "Risk - Pay Later Instalments"
    And user navigates to Free Search
    When user search cardDetails by accountNumber
    And user navigates to
      | Details |
    Then user click on CardNumber
    And user navigates to
      | Card Data |
    Then validate "Card Data" tab title
    Then Validation Full Data on Card Data page
    And Logout from Workbench


  @Author:Suvarna @RiskPayLaterInstall @R-PI.003.02.04
  Scenario: Validate Card Main Screen-Card Data-Set Status
    When user login workbench application for role "Risk - Pay Later Instalments"
    And user navigates to Free Search
    When user search cardDetails by accountNumber
    And user navigates to
      | Details |
    Then user click on CardNumber
    And user navigates to
      | Card Data |
      | Set Status |
    Then User set New status for the card Data
    And Logout from Workbench