Feature: Officers and Grants

  @Author:Aishwarya @IssuerAdmin @IA.003.05.01
  Scenario: Verify Custom Profiles
    When user login workbench application for role "Issuer Admin"
    And user navigates to Custom Profiles
    And user navigates to
      |Get All|
    Then validate "Custom Profiles" tab title
    And Logout from Workbench
