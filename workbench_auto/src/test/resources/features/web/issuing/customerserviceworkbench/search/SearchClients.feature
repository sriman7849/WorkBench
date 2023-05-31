Feature: Search by Client

  @Author:Aishwarya @RiskPayLaterInstalments @R-PI.002.01.01
  Scenario: Search Client By Client Number
    When user login workbench application for role "Risk - Pay Later Instalments"
    And user navigates to clients
    And user navigates to
      | By Number |
    Then user search client by client number
    And user is able to see the record

  @Author:Aishwarya @RiskPayLaterInstalments @R-PI.002.01.02
  Scenario: Search Client By Identity Card Number
    When user login workbench application for role "Risk - Pay Later Instalments"
    And user navigates to clients
    And user navigates to
      | By Identity Card Number |
    Then user search client by Identity Card Number
    And user is able to see the record

  @Author:Aishwarya @RiskPayLaterInstalments @R-PI.002.01.03
  Scenario: Search Client By Basic Search
    When user login workbench application for role "Risk - Pay Later Instalments"
    And user navigates to clients
    And user navigates to
      | Basic Search |
    Then user search client by Basic Search
    And user is able to see the record

  @Author:Aishwarya @RiskPayLaterInstalments @R-PI.002.01.04
  Scenario: Search client
    When user login workbench application for role "Risk - Pay Later Instalments"
    And user navigates to clients
    And user navigates to
      | By Number |
    Then user search client by client number
    And user click on Form editor and click on Client Number
    Then user validate Client Number is Hidden or Not


  @Author:Aishwarya @RiskPayLaterInstalments @R-PI.002.01.05
  Scenario: Search Client By Client ID
    When user login workbench application for role "Risk - Pay Later Instalments"
    And user navigates to clients
    And user navigates to
      | By Client ID |
    Then user search client by ClientID
    And user is able to see the record

  @RiskPayLaterInstalments @R-PI.002.05.01
  @Author:@AartiPatel
  Scenario: Search Client By Client Number from different customer
    When user login workbench application for role "Risk - Pay Later Instalments"
    And user navigates to clients
    And user navigates to
      |By Number|
    Then user search client by client number from different customer

