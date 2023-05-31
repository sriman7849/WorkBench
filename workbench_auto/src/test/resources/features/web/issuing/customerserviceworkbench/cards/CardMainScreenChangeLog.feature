Feature: Card Main Screen Change Log Feature

  @Author:Jyoti @RiskPayLaterInstall @R-PI.003.13.01 @R-PI.003.02.05
  Scenario:  Validate card main Screen Change Log with PCI
    When user login workbench application for role "Risk - Pay Later Instalments"
    And user navigates to Free Search
    Then user navigates to
      | Account |
    When user search cardDetails by accountNumber
    And user navigates to
      | Details |
    Then user click on CardNumber
    And user navigates to
      | Change Log |
    Then validate "Change Log" tab title
    Then user is able to see the record on "Change Log" page
    And Logout from Workbench


  @Author:Jyoti  @RiskPayNowEditPCI-Less @R-PNEL.003.09.01
  Scenario: Validate card main Screen Change Log with PCI-Less
    When user login workbench application for role "Risk - Pay Later Instalments (PCI-Less)"
    And user navigates to Free Search
    Then user navigates to
      | Account |
    When user search cardDetails by accountNumber
    And user navigates to
      | Details |
    Then user click on CardNumber
    And user navigates to
      | Change Log |
    Then validate "Change Log" tab title
    Then user is able to see the record on "Change Log" page
    And Logout from Workbench

  @Author:Jyoti @RiskPayLaterInstall @R-PI.003.13.02
  Scenario: Validate card  main Screen Change log-view Data
    When user login workbench application for role "Risk - Pay Later Instalments"
    And user navigates to Free Search
    Then user navigates to
      | Account |
    When user search cardDetails by accountNumber
    And user navigates to
      | Details |
    Then user click on CardNumber
    And user navigates to
      | Change Log |
      | View Data |
    Then validate "Details" tab title
    Then user is able to see the record on "Details" page
    And Logout from Workbench