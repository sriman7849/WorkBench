@CardMainScreenClassifiers
Feature: Card Main Screen Contract Parameters Features

  @Author:Jyoti @RiskPayNowEditPCI-Less @R-PNEL.003.06.01
  Scenario: Card Main Screen - Contract Parameters
    When user login workbench application for role "Risk - Pay Later Instalments (PCI-Less)"
    And user navigates to Free Search
    Then user navigates to
      | Account |
    When user search cardDetails by accountNumber
    And user navigates to
      | Details |
    Then user click on CardNumber
    And user navigates to
      | Contract Parameters |
    Then validate "Contract Parameters" tab title
    And user validate Contract parameter records
    And Logout from Workbench

  @Author:Vijay @RiskPayLaterInstalments @R-PI.003.10.08 @R-PI.003.10.11
  Scenario: Contract Main Screen - Contract Parameters - History
    When user login workbench application for role "Risk - Pay Later Instalments"
    And user navigates to Free Search
    Then user navigates to
      | Account |
    When user search cardDetails by accountNumber    
    And user navigates to
      | Details |
    Then user click on CardNumber
    And user navigates to
      | Contract Parameters |
    Then validate "Contract Parameters" tab title
    And user navigates to
      | All Visible |
    Then update Contract Parameters "BCC Billing Day"
    And user navigates to
      | History |
    Then Validate Contract Parameters History
    

