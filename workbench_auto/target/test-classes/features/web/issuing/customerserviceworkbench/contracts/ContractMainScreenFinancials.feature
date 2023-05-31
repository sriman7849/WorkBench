Feature: Contract Main Screen Financials

  @Author:Jyoti @RiskPayLaterInstalments @R-PI.004.02.01
  Scenario: Verify Contract main Screen Financials
    When user login workbench application for role "Risk - Pay Later Instalments"
    And user navigates to contracts
    And user navigates to
      | By Contract ID |
    Then user search contract by contract ID
    And user navigates to
      | Contract Tree |
      | Financials    |
    Then validate "Financials" tab title
    Then Validate the contract main screen Financial Page

  @Author:Aishwarya @RiskPayLaterInstalments @R-PI.004.02.04
  Scenario: Verify Contract main Screen Financials-Details
    When user login workbench application for role "Risk - Pay Later Instalments"
    And user navigates to contracts
    And user navigates to
      | By Contract ID |
    Then user search contract by contract ID
    And user navigates to
      | Contract Tree     |
      | Financials        |
      | Financial Details |
    Then validate "Financial Details" tab title
    Then validate Financial Details page

  @Author:Vijay @RiskPayLaterInstalments @R-PI.004.02.02 @R-PI.004.02.03 @R-PI.004.02.38
  Scenario: Contract Main Screen - Financials - Set Additional/Temporary Limit- Credit Limit Log
    When user login workbench application for role "Risk - Pay Later Instalments"
    And user navigates to contracts
    And user search contract by contract ID
    And user navigates to
      | Contract Tree |
      | Financials    |
    Then validate "Financials" tab title
    Then click on Set "Additional" Limit
    Then validate "Additional" Financial Limit
    Then click on Set "Temporary" Limit
    Then validate "Temporary" Financial Limit
    And user navigates to
      | Credit Limit Log |
    Then validate Credit Limit log data
    And Logout from Workbench


  @Author:Viresh @RiskPayLaterInstalments @R-PI.004.02.05
  Scenario: Contract Main Screen - Financials - Financial Details - Billing History
    When user login workbench application for role "Risk - Pay Later Instalments"
    And user navigates to contracts
    And user navigates to
      | By Number |
    Then user search contract by contract Number - "57583106"
    And user navigates to
      | Contract Tree     |
      | Financials        |
      | Financial Details |
    Then user navigates to the link "Billing History" at the bottom of the tab "Financial Details"
    Then user is able to see the record on "Financial Details" page

  @Author:Viresh @RiskPayLaterInstalments @R-PI.004.02.27
  Scenario: Contract Main Screen - Financials - Financial Details - Accounts
    When user login workbench application for role "Risk - Pay Later Instalments"
    And user navigates to contracts
    And user navigates to
      | By Number |
    Then user search contract by contract Number - "57583106"
    And user navigates to
      | Contract Tree     |
      | Financials        |
      | Financial Details |
    Then user navigates to the link "Accounts" at the bottom of the tab "Financial Details"
    Then user is able to see the record on "Financial Details" page

  @Author:Viresh @RiskPayLaterInstalments @R-PI.004.02.28
  Scenario: Contract Main Screen - Financials - Financial Details - Accounts - Details
    When user login workbench application for role "Risk - Pay Later Instalments"
    And user navigates to contracts
    And user navigates to
      | By Number |
    Then user search contract by contract Number - "57583106"
    And user navigates to
      | Contract Tree     |
      | Financials        |
      | Financial Details |
    Then user navigates to the link "Accounts" at the bottom of the tab "Financial Details"
    Then user navigates to the link "Details" at the bottom of the tab "Accounts"
    Then user validate financial account details

  @Author:Akhil @RiskPayLaterInstalments @R-PI.004.02.32 @R-PI.004.02.33
  Scenario: Contract Main Screen - Financials - Accounts - Details
    When user login workbench application for role "Risk - Pay Later Instalments"
    And user navigates to contracts
    And user navigates to
      | By Number |
    Then user search contract by contract Number - "57583106"
    And user navigates to
      | Contract Tree |
      | Financials    |
      | Accounts      |
    Then user is able to see the record on "Accounts" page
    Then user navigates to the link "Details" at the bottom of the tab "Accounts"
    Then user validate financial account details
    And Logout from Workbench

  @Author:Viresh @RiskPayLaterInstalments @R-PI.004.02.29
  Scenario: Contract Main Screen - Financials - Financial Details - Accounts - Statement Entries
    When user login workbench application for role "Risk - Pay Later Instalments"
    And user navigates to contracts
    And user navigates to
      | By Number |
    And user search contract by contract Number
    And user navigates to
      | Contract Tree     |
      | Financials        |
      | Financial Details |
    Then user navigates to the link "Accounts" at the bottom of the tab "Financial Details"
    Then user navigates to the link "Statement Entries" at the bottom of the tab "Accounts"
    Then user is able to see the record on "Statement Entries" page

  @Author:Viresh @RiskPayLaterInstalments @R-PI.004.02.34
  Scenario: Contract Main Screen - Financials - Accounts - Statement Entries
    When user login workbench application for role "Risk - Pay Later Instalments"
    And user navigates to contracts
    And user navigates to
      | By Number |
    And user search contract by contract Number
    And user navigates to
      | Contract Tree     |
      | Financials        |
    Then user navigates to the link "Accounts" at the bottom of the tab "Financials"
    Then user navigates to the link "Statement Entries" at the bottom of the tab "Accounts"
    Then user is able to see the record on "Statement Entries" page

  @Author:Viresh @RiskPayLaterInstalments @R-PI.004.02.35
  Scenario: Contract Main Screen - Financials - Accounts - Statement Entries - Details
    When user login workbench application for role "Risk - Pay Later Instalments"
    Then user navigates to contracts
    And user navigates to
      | By Number |
    And user search contract by contract Number
    And user navigates to
      | Contract Tree     |
      | Financials        |
    And user navigates to the link "Accounts" at the bottom of the tab "Financials"
    And user navigates to the link "Statement Entries" at the bottom of the tab "Accounts"
    And user navigates to the link "Details" at the bottom of the tab "Statement Entries"
    Then validate "Details" tab title
    And user validate Statement Entries details
