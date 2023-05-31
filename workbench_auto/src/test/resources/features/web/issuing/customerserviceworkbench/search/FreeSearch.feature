@FreeSearchFeature
Feature: Free Search
  As Customer Service Bench Admin. I want to search specific record

  @Author:Vijay @RiskPayLaterInstall @R-PI.002.03.04
  Scenario: Search by Free Search by Company
    When user login workbench application for role "Risk - Pay Later Instalments"
    Then user Free Search by Company name
    And validate records count
    Then Logout from Workbench

  @RiskPayLaterInstalments @R-PI.002.03.03
    @Author:@AartiPatel
  Scenario: Free Search Account Validation
    When user login workbench application for role "Risk - Pay Later Instalments"
    Then validate search in Free Search by Account ID

  @Author:Vijay @RiskPayLaterInstall @R-PI.002.03.01
  Scenario: Search by Free Search by Cardholder
    When user login workbench application for role "Risk - Pay Later Instalments"
    Then user Free Search by Card Holder name
    And validate records count
    Then Logout from Workbench

  @RiskPayLaterInstall @R-PI.002.04.01
  Scenario: Search by Free Search by Cardholder substring
    When user login workbench application for role "Risk - Pay Later Instalments"
    Then user Free Search by Card Holder name using substring

  @Author:Vijay @RiskPayLaterInstall @R-PI.002.04.02
  Scenario: Search by Free Search by Masked PAN
    When user login workbench application for role "Risk - Pay Later Instalments"
    Then user Free Search by Masked Card Number
    And validate records count
    Then Logout from Workbench
