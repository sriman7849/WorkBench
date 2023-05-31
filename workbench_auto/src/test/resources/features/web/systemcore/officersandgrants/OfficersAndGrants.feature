Feature: Officers and Grants

  @Author:Aishwarya @IssuerAdmin @IA.003.01.01
  Scenario: Verify Officers and Grants
    When user login workbench application for role "Issuer Admin"
    And user navigates to Officers
    Then validate "Officers" tab title
    And Logout from Workbench
