Feature: Contract Main Screen - Instalments feature

  @Author:Vijay @RiskPayLaterInstalments @R-PI.004.06.01
  Scenario: Contract Main Screen - Instalments
    When user login workbench application for role "Risk - Pay Later Instalments (PCI-Less)"
    And user navigates to contracts
    Then user search contract by contract ID
    And user navigates to
      | Contract Tree |
      | Instalments   |
    Then validate "Instalments" tab title
    Then validate Instalments Data Field
    And Logout from Workbench

  @Author:Vijay @RiskPayLaterInstalments @R-PI.004.06.02 @R-PI.004.06.17 @R-PI.004.06.21
  Scenario: Contract Main Screen - Instalments - Active Plans/Transaction/History
    When user login workbench application for role "Risk - Pay Later Instalments (PCI-Less)"
    And user navigates to contracts
    Then user search contract by contract ID
    And user navigates to
      | Contract Tree |
      | Instalments   |
      | Active Plans  |
    Then validate "Active Plans" tab title
    #Data validation pending
    And user navigates to
      | Transactions |
    Then validate "Transactions" tab title
    #Data validation pending
    And user navigates to
      | History |
    Then validate "History" tab title
    #Data validation pending

  @Author:Vijay @RiskPayLaterInstalments @R-PI.004.06.76
  Scenario: Contract Main Screen - Instalments - Instalment Plan By Balance [button]
    When user login workbench application for role "Risk - Pay Later Instalments (PCI-Less)"
    And user navigates to contracts
    Then user search contract by contract ID
    And user navigates to
      | Contract Tree |
      | Instalments   |
    Then validate "Instalments" tab title
    Then click on Instalment Plan By Balance
    Then validate "Instalments: Instalment Plan By Balance" tab title
    ##Data validation pending
    And Logout from Workbench

  @Author:Vijay @RiskPayLaterInstalments @R-PI.004.06.77
  Scenario: Contract Main Screen - Instalments - Close Instalment Plans [button]
    When user login workbench application for role "Risk - Pay Later Instalments (PCI-Less)"
    And user navigates to contracts
    Then user search contract by contract ID
    And user navigates to
      | Contract Tree |
      | Instalments   |
    Then validate "Instalments" tab title
    Then click on Close Instalment Plan
    Then validate "Instalments: Close Instalment Plans" tab title
    Then validate Close Instalment Plans
    And Logout from Workbench


