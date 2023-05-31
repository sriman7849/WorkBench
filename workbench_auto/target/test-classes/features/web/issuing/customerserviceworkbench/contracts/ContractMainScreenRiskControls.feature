Feature: Risk Controls Features

  @RiskPayLaterInstalments @Author:Harika @R-PI.004.05.03  @Author:Vijay @RiskPayNowEditPCI-Less  @R-PNEL.004.03.02
  Scenario Outline: Risk Rules Page Validation with PCI and PCI-Less
    When user login workbench application for role "<user_role>"
    And user navigates to contracts
    Then user search contract by contract ID
    And user navigates to
      | Contract Tree |
      | Risk Controls |
      | Risk Rules    |
    Then validate Risk Control "Risk Rules" tab
    And Logout from Workbench
    Examples:
      | user_role |
      | Risk - Pay Later Instalments |
      | Risk - Pay Later Instalments (PCI-Less) |

  @Author:Vijay @RiskPayLaterInstalments @RiskPayNowEditPCI-Less @Author:Ramprasad @R-PI.004.05.02 @R-PNEL.004.03.03
  Scenario Outline: Fraud Rules Page Validation with PCI and PCI-Less
    When user login workbench application for role "<user_role>"
    And user navigates to contracts
    Then user search contract by contract ID
    And user navigates to
      | Contract Tree |
      | Risk Controls |
      | Fraud Rules   |
    Then validate Risk Control "Fraud Rules" tab
    And Logout from Workbench
    Examples:
      | user_role |
      | Risk - Pay Later Instalments |
      | Risk - Pay Later Instalments (PCI-Less) |

  @Author:Vijay @RiskPayLaterInstalments @RiskPayNowEditPCI-Less @R-PI.004.05.04 @R-PNEL.004.03.04
  Scenario Outline: Contract Main Screen - Risk Controls with PCI and PCI-Less
    When user login workbench application for role "<user_role>"
    And user navigates to contracts
    Then user search contract by contract ID
    And user navigates to
      | Contract Tree |
      | Risk Controls |
      | Usage Limiters  |
    Then validate Risk Control "Usage Limiters" tab
    And Logout from Workbench
    Examples:
      | user_role |
      | Risk - Pay Later Instalments |
      | Risk - Pay Later Instalments (PCI-Less) |

  @Author:Vijay @RiskPayNowEditPCI-Less @R-PNEL.004.03.01
  Scenario: Contract Main Screen - Risk Controls with PCI-Less
    When user login workbench application for role "Risk - Pay Later Instalments (PCI-Less)"
    And user navigates to contracts
    Then user search contract by contract ID
    And user navigates to
      | Contract Tree |
      | Risk Controls |
      | Fraud Rules   |
    Then validate Risk Control "Fraud Rules" tab
    And user navigates to
      | Risk Rules |
    Then validate Risk Control "Risk Rules" tab
    And user navigates to
      | Usage Limiters |
    Then validate Risk Control "Usage Limiters" tab
    And Logout from Workbench