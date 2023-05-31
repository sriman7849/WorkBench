Feature: User must be able to create client


  Scenario:
    Given User must have "createClient.json" body json
    When User sent post request to "createClient.json"
    Then user get "200" response code

  Scenario Outline: create client using API
    Given User must have "createClient.json" body json
    When User sent post request to "createClient.json"
    Then User validate response code is "<responseCode>" and validate "<fieldname>" and "<fieldvalue>" is present
    Examples:
      | responseCode | fieldname  | fieldvalue |
      | 200          | merchantId | numeric-15 |

