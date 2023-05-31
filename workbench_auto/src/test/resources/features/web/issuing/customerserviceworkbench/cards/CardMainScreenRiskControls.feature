Feature: Risk Controls Features

  @Author:Jyoti @RiskPayNowEditPCI-Less @R-PNEL.003.04.01
  Scenario: Card Main Screen - Risk Controls with PCI-Less
    When user login workbench application for role "Risk - Pay Later Instalments (PCI-Less)"
    And user navigates to Free Search
    Then user navigates to
      | Account |
    When user search cardDetails by accountNumber
    And user navigates to
      | Details |
    Then user click on CardNumber
    And user navigates to
      | Risk Controls |
      | Fraud Rules   |
    Then validate Risk Control "Fraud Rules" tab
    And user navigates to
      | Risk Rules |
    Then validate Risk Control "Risk Rules" tab
    And user navigates to
      | Usage Limiters |
    Then validate Risk Control "Usage Limiters" tab

  @Author:Suvarna @RiskPayLaterInstalments @R-PI.003.06.01 @R-PI.003.06.02  @Author:Jyoti @RiskPayNowEditPCI-Less @R-PNEL.003.04.02
  Scenario Outline: Fraud Rules Page Validation with PCI and PCI-Less
    When user login workbench application for role "<user_role>"
    And user navigates to Free Search
    Then user navigates to
      | Account |
    When user search cardDetails by accountNumber
    And user navigates to
      | Details |
    Then user click on CardNumber
    And user navigates to
      | Risk Controls |
      | Fraud Rules   |
    Then validate Risk Control "Fraud Rules" tab
    And Logout from Workbench
    Examples:
      | user_role                               |
      | Risk - Pay Later Instalments            |
      | Risk - Pay Later Instalments (PCI-Less) |

  @Author:Suvarna @RiskPayLaterInstalments @R-PI.003.06.03 @Author:Jyoti @RiskPayNowEditPCI-Less @R-PNEL.003.04.03
  Scenario Outline: Risk Rules Page Validation with PCI and PCI-Less
    When user login workbench application for role "<user_role>"
    And user navigates to Free Search
    Then user navigates to
      | Account |
    When user search cardDetails by accountNumber
    And user navigates to
      | Details |
    Then user click on CardNumber
    And user navigates to
      | Risk Controls |
      | Risk Rules    |
    Then validate Risk Control "Risk Rules" tab
    Then user is able to see the record on "Risk Rules" page
    And Logout from Workbench
    Examples:
      | user_role                               |
      | Risk - Pay Later Instalments            |
      | Risk - Pay Later Instalments (PCI-Less) |

  @Author:Suvarna @RiskPayLaterInstalments @R-PI.003.06.04
    @Author:Jyoti @RiskPayNowEditPCI-Less  @R-PNEL.003.04.04
  Scenario Outline: Card Main Screen - Risk Controls with PCI and PCI-Less
    When user login workbench application for role "<user_role>"
    And user navigates to Free Search
    Then user navigates to
      | Account |
    When user search cardDetails by accountNumber
    And user navigates to
      | Details |
    Then user click on CardNumber
    And user navigates to
      | Risk Controls  |
      | Usage Limiters |
    Then validate Risk Control "Usage Limiters" tab
    Then user is able to see the record on "Usage Limiters" page
    And Logout from Workbench
    Examples:
      | user_role                               |
      | Risk - Pay Later Instalments            |
      | Risk - Pay Later Instalments (PCI-Less) |


  @Author:Suvarna @RiskPayLaterInstalments @R-PI.003.06.05
  Scenario: Risk Controls- Switch On Validation
    When user login workbench application for role "Risk - Pay Later Instalments"
    And user navigates to Free Search
    Then user navigates to
      | Card |
    When user search cardDetails by accountNumber
    And user navigates to
      | Details |
    Then user click on CardNumber
    And user navigates to
      | Risk Controls |
      | Risk Rules    |
      | Switch On     |
    Then User switch on the card data status
    And Logout from Workbench

  @Author:Suvarna @RiskPayLaterInstalments @R-PI.003.06.06
  Scenario: Risk Controls- Switch Off Validation
    When user login workbench application for role "Risk - Pay Later Instalments"
    And user navigates to Free Search
    Then user navigates to
      | Card |
    When user search cardDetails by accountNumber
    And user navigates to
      | Details |
    Then user click on CardNumber
    And user navigates to
      | Risk Controls |
      | Risk Rules    |
      | Switch Off    |
    Then User switch off the card data status
    And Logout from Workbench


  @Author:Suvarna @RiskPayLaterInstalments @R-PI.003.06.07
  Scenario: Risk Controls- Redefine Validation
    When user login workbench application for role "Risk - Pay Later Instalments"
    And user navigates to Free Search
    Then user navigates to
      | Card  |
    When user search cardDetails by accountNumber
    And user navigates to
      | Details  |
    Then user click on CardNumber
    And user navigates to
      | Risk Controls |
      | Risk Rules    |
      | Redefine   |
    Then User Redefine the card data status
    And Logout from Workbench

  @Author:Suvarna @RiskPayLaterInstalments @R-PI.003.06.08
  Scenario: Risk Controls- Restore Original Validation
    When user login workbench application for role "Risk - Pay Later Instalments"
    And user navigates to Free Search
    Then user navigates to
      | Card  |
    When user search cardDetails by accountNumber
    And user navigates to
      | Details  |
    Then user click on CardNumber
    And user navigates to
      | Risk Controls |
      | Risk Rules    |
      | Restore Original   |
    Then User Restore Original the card data status
    And Logout from Workbench

  @Author:Suvarna @RiskPayLaterInstalments @R-PI.003.06.09
  Scenario: Risk Controls- Details Validation
    When user login workbench application for role "Risk - Pay Later Instalments"
    And user navigates to Free Search
    When user search cardDetails by accountNumber
    And user navigates to
      | Details  |
    Then user click on CardNumber
    And user navigates to
      | Risk Controls |
      | Risk Rules    |
      | Switch Off 1 Day   |
    Then validate "Risk Controls: Switch Off 1 Day" tab title
    Then validate risk controls Switch Off One Day
    And Logout from Workbench

  @Author:Suvarna @RiskPayLaterInstalments @R-PI.003.06.10
  Scenario: Risk Controls- Details Validation
    When user login workbench application for role "Risk - Pay Later Instalments"
    And user navigates to Free Search
    When user search cardDetails by accountNumber
    And user navigates to
      | Details  |
    Then user click on CardNumber
    And user navigates to
      | Risk Controls |
      | Risk Rules    |
      | Switch On     |
    Then User switch on the card data status
    Then user navigates to the link "Details" at the bottom of the tab "Risk Controls"
    Then validate "Details" tab title
    Then validate risk controls details page
    And Logout from Workbench


  @Author:Suvarna @RiskPayLaterInstalments @R-PI.003.06.11
  Scenario: Risk Controls- Schedule Validation
    When user login workbench application for role "Risk - Pay Later Instalments"
    And user navigates to Free Search
    When user search cardDetails by accountNumber
    And user navigates to
      | Details  |
    Then user click on CardNumber
    And user navigates to
      | Risk Controls |
      | Risk Rules    |
      | Schedule      |
    Then validate "Schedule" tab title
    And user is able to see the record on "Schedule" page
    And Logout from Workbench

  @Author:Suvarna @RiskPayLaterInstalments @R-PI.003.06.12
  Scenario: Risk Controls- History Validation
    When user login workbench application for role "Risk - Pay Later Instalments"
    And user navigates to Free Search
    When user search cardDetails by accountNumber
    And user navigates to
      | Details  |
    Then user click on CardNumber
    And user navigates to
      | Risk Controls |
      | Risk Rules    |
      | History   |
    Then validate "History" tab title
    And user is able to see the record on "History" page
    And Logout from Workbench

    

