Feature: Card Main Screen Memos Feature

  @Author:Jyoti @RiskPayLaterInstall @R-PI.003.11.01
  Scenario: Validate card main Screen Memos
    When user login workbench application for role "Risk - Pay Later Instalments"
    And user navigates to Free Search
    Then user navigates to
      | Account |
    When user search cardDetails by accountNumber
    And user navigates to
      | Details |
    Then user click on CardNumber
    And user navigates to
      | Memos |
    And user validate the Memos Screen
    Then Logout from Workbench

  @Author:Jyoti @RiskPayNowEditPCI-Less @R-PNEL.003.07.01
  Scenario: Validate card  main Screen Memos
    When user login workbench application for role "Risk - Pay Later Instalments (PCI-Less)"
    And user navigates to Free Search
    Then user navigates to
      | Account |
    When user search cardDetails by accountNumber
    And user navigates to
      | Details |
    Then user click on CardNumber
    And user navigates to
      | Memos |
    And user validate the Memos Screen
    Then Logout from Workbench

  @Author:Jyoti @RiskPayLaterInstall @R-PI.003.11.02 @R-PI.003.11.03 @R-PI.003.11.04 @R-PI.003.11.05 @R-PI.003.11.06
  Scenario: Validate create, Edit, Details,change log memos from Card Main Screen
    When user login workbench application for role "Risk - Pay Later Instalments"
    And user navigates to Free Search
    Then user navigates to
      | Account |
    When user search cardDetails by accountNumber
    And user navigates to
      | Details |
    Then user click on CardNumber
    And user navigates to
      | Memos |
    And user create new Memos
    And user Validate the Memos
    And User Edit the created Memo
    And user Validate the Memos
    And user Delete the newly created Memo
    And user Validate the Deleted Memos Status
    And user navigates to the link "Change Log" at the bottom of the tab "Memos"
    Then user is able to see the record on "Change Log" page
    Then validate "Change Log" tab title
    And user navigates to the link "Details" at the bottom of the tab "Change Log"
    And validate "Details" tab title
    And user Validate the Memos
    Then Logout from Workbench