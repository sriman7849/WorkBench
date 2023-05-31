Feature: Card Main Screen Financials

  @Author:Aishwarya @RiskPayLaterInstalments @R-PI.003.03.01 @R-PI.003.03.02 @R-PI.003.03.03
  Scenario: Verify Card main Screen Financials
    When user login workbench application for role "Risk - Pay Later Instalments"
    And user navigates to Free Search
    And user navigates to
      | Account |
    When user search cardDetails by accountNumber
    And user navigates to
      | Details |
    Then user click on CardNumber
    And user navigates to
      | Financials |
    Then validate "Financials" tab title
    Then Validate the contract main screen Financial Page
    And user navigates to
      | Financial Details |
    Then validate "Financial Details" tab title
    And Validate the Financial Details
    Then user click on Billing History of Financials
    Then validate "Billing History" tab title
    And Validate the Billing History Page of Financials

  @Author:Jyoti @RiskPayLaterInstall @R-PI.003.03.25
  Scenario: Card Main Screen - Financials - Financial Details - Accounts
    When user login workbench application for role "Risk - Pay Later Instalments"
    And user navigates to Free Search
    And user navigates to
      |Card|
    Then user search cardDetails by card Number - "5429110000257781"
    And user navigates to
      | Details |
    Then user click on CardNumber "7781"
    And user navigates to
      | Financials  |
      | Financial Details |
    Then user navigates to the link "Accounts" at the bottom of the tab "Financial Details"
    Then user is able to see the record on "Financial Details" page

  @Author:Jyoti @RiskPayLaterInstall @R-PI.003.03.26
  Scenario: Card Main Screen - Financials - Financial Details - Accounts
    When user login workbench application for role "Risk - Pay Later Instalments"
    And user navigates to Free Search
    And user navigates to
      |Card|
    Then user search cardDetails by card Number - "5429110000257781"
    And user navigates to
      | Details |
    Then user click on CardNumber "7781"
    And user navigates to
      | Financials  |
      | Financial Details |
    Then user navigates to the link "Accounts" at the bottom of the tab "Financial Details"
    Then user navigates to the link "Details" at the bottom of the tab "Accounts"
    Then user validate financial account details

  @Author:Viresh @RiskPayLaterInstalments @R-PI.003.03.33
  Scenario: Card Main Screen - Financials - Credit Limit Log
    When user login workbench application for role "Risk - Pay Later Instalments"
    And user navigates to contracts
    And user navigates to
      | By Number |
    Then user search contract by contract Number - "57583106"
    And user navigates to "Contract Tree"
    Then user click on card by last four digits of card number "7781"
    And user navigates to
      | Financials       |
      | Credit Limit Log |
    Then user is able to see the record on "Credit Limit Log" page