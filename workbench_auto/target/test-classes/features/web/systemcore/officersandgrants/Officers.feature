Feature: Officers and Grants

  @Author:Aishwarya @IssuerAdmin @IA.003.02.01
  Scenario: Verify Officer
    When user login workbench application for role "Issuer Admin"
    And user navigates to Officers
    And user navigates to
      | By Mask |
    Then user search Officers "Aleksandr Kolesnikov" BY MASK by Name
    And user is able to see the record
    And Logout from Workbench

  @Author:Aishwarya @IssuerAdmin @IA.003.02.02
  Scenario: Verify Officers
    When user login workbench application for role "Issuer Admin"
    And user navigates to Officers
    And user navigates to
      | By Mask |
    Then user search Officers "EMURK" BY MASK by UserID
    And user is able to see the record
    And Logout from Workbench

  @Author:Aishwarya @IssuerAdmin @IA.003.04.05
  Scenario: Verify Officers
    When user login workbench application for role "Issuer Admin"
    And user navigates to Officers
    And user navigates to Active
    Then user search "INBANK_PCI" with "Officer Group"
    And user navigates to
      | Details |
    Then validate "Details" tab title
    And validate Officers details page
    And Logout from Workbench

  @Author:Aishwarya @IssuerAdmin @IA.003.04.03
  Scenario: Verify Officers
    When user login workbench application for role "Issuer Admin"
    And user navigates to Officers
    And user navigates to
      | By Mask |
    Then user search Officers "RELEASE_INSTAL_2" BY MASK by UserID
    And user click on set password on "Officers" Screen
    Then user enters Password and Password Confirmation and click in save button
    And Logout from Workbench

  @Author:Aishwarya @IssuerAdmin @IA.003.04.04
  Scenario: Verify Officers
    When user login workbench application for role "Issuer Admin"
    And user navigates to Officers
    And user navigates to
      | By Mask |
    Then user search Officers "RELEASE_INSTAL_2" BY MASK by UserID
    And user click on Reset Failed Login Count on "Officers" Screen
    And user click on OK Button of Dialog Box
    Then validate Reset Failed Login Count Popup Message
    And Logout from Workbench

  @Author:Aishwarya @IssuerAdmin @IA.003.04.06
  Scenario: Verify Officers
    When user login workbench application for role "Issuer Admin"
    And user navigates to Officers
    And user navigates to
      | By Mask |
    Then user search Officers "RELEASE_INSTAL_2" BY MASK by UserID
    And user navigates to
      | Details |
    And user click on set password on "Details" Screen
    Then user enters Password and Password Confirmation and click in save button
    And Logout from Workbench

  @Author:Aishwarya @IssuerAdmin @IA.003.04.07
  Scenario: Verify Officers
    When user login workbench application for role "Issuer Admin"
    And user navigates to Officers
    And user navigates to
      | By Mask |
    Then user search Officers "RELEASE_INSTAL_2" BY MASK by UserID
    And user navigates to
      | Details |
    And user click on Reset Failed Login Count on "Details" Screen
    And user click on OK Button of Dialog Box
    Then validate Reset Failed Login Count Popup Message
    And Logout from Workbench