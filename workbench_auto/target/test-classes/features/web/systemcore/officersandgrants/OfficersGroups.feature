Feature: OfficersGroups

  @Author:Suvarna @IssuerAdmin @IA.002.01.01 @IA.002.01.03
  Scenario: Verify Officers Groups-Officers In Group-All
    When user login workbench application for role "Issuer Admin"
    Then user navigates to Officer Groups
    Then user search for "INBANK_RISK_OFFICER_AND_USER_ADMIN_PCI_WB3" with "NAME"
    And user navigates to
      | Officers In Group |
    Then validate "Officers In Group" tab title
    Then user navigates to "All"
    And user is able to see the record
    And Logout from Workbench

  @Author:Suvarna @IssuerAdmin @IA.002.01.02
  Scenario: Verify Officers Groups-Officers In Group-Column Visibility
    When user login workbench application for role "Issuer Admin"
    Then user navigates to Officer Groups
    Then user search for "INBANK_RISK_OFFICER_AND_USER_ADMIN_PCI_WB3" with "NAME"
    And user navigates to
      | Officers In Group |
    Then validate "Officers In Group" tab title
    Then validate "Officers In Group" table Column "Active" is Present
    #And validate col visibility by scrolling
    Then validate "Officers In Group" table Column "Last Login Time" is Present
    And Logout from Workbench

  @Author:Suvarna @IssuerAdmin @IA.002.02.01 @IA.002.02.03
  Scenario: Verify Officers Groups-Officers In Group-By Mask - Search by Name
    When user login workbench application for role "Issuer Admin"
    Then user navigates to Officer Groups
    Then user search for "INBANK_RISK_OFFICER_AND_USER_ADMIN_PCI_WB3" with "NAME"
    And user navigates to
      | Officers In Group |
    Then validate "Officers In Group" tab title
    Then Navigate to "Officers In Group" Tab and "By Mask" SubTab
    Then user search Officers In Group "AUTO_IN%" BY MASK by NAME
    Then user search for "AUTO_IN_RPCI_03" with "NAME"
    And user is able to see the record
    And Logout from Workbench

  @Author:Suvarna @IssuerAdmin @IA.002.02.02
  Scenario: Verify Officers Groups-Officers In Group-By Mask - Search by User Id
    When user login workbench application for role "Issuer Admin"
    Then user navigates to Officer Groups
    Then user search for "INBANK_RISK_OFFICER_AND_USER_ADMIN_PCI_WB3" with "NAME"
    And user navigates to
      | Officers In Group |
    Then validate "Officers In Group" tab title
    Then Navigate to "Officers In Group" Tab and "By Mask" SubTab
    Then user search Officers In Group "AUTO_IN%" BY MASK by USER ID
    Then user search for "AUTO_IN_RPCI_03" with "User ID"
    And user is able to see the record
    And Logout from Workbench

  @Author:Vijay @IssuerAdmin @IA.001.01.03
  Scenario: Officers Groups - Officers In Group - All
    When user login workbench application for role "Issuer Admin"
    Then user navigates to Officer Groups
    Then validate "Officer Groups" tab title
    And user navigates to
      | All |
    Then filter with "INBANK_CUSTOMER_SERVICE_PCI_LESS_WB3" Officer Group
    And validate records count
    And Logout from Workbench

  @Author:Vijay @IssuerAdmin @IA.001.02.01 @IA.001.02.02 @IA.001.02.03
  Scenario: Officers Groups - Officers In Group - By Mask - Search by Name/by Code/Name or Code using substring [%]
    When user login workbench application for role "Issuer Admin"
    Then user navigates to Officer Groups
    Then validate "Officer Groups" tab title
    And user navigates to
      | By Mask |
    Then search by "NAME" with "INBANK_CUSTOMER_SERVICE_PCI_LESS_WB3"
    And validate records count
    Then click on reset button
    Then search by "Code" with "INBANK_CUSTOMER_SERVICE_PCI_LESS_WB3"
    And validate records count
    Then click on reset button
    Then search by "NAME" with "INBANK_CUSTOMER%"
    And validate records count
    And Logout from Workbench

  @Author:Vijay @IssuerAdmin @IA.001.03.01 @IA.001.03.02 @IA.001.03.03
  Scenario: Officers Groups - By Officer - Search by Officer Name - Search by Officer Name/by User ID/Officer Name or User ID using substring [%]
    When user login workbench application for role "Issuer Admin"
    Then user navigates to Officer Groups
    Then validate "Officer Groups" tab title
    And user navigates to
      | By Officer |
    Then search by "Officer Name" with "Eve-mai Kuus"
    And validate records count
    Then click on reset button
    Then search by "User ID" with "WB3_EKUUS"
    And validate records count
    Then click on reset button
    Then search by "Officer Name" with "Eve%"
    And validate records count
    And Logout from Workbench


  @Author:Aishwarya @IssuerAdmin @IA.002.03.01
  Scenario: Verify Officers Groups
    When user login workbench application for role "Issuer Admin"
    And user navigates to Officer Groups
    Then user search for "ZZZ_Release_InBank_PCI" with "NAME"
    And user navigates to
      | Officers In Group |
    And user navigates to
      | All |
    Then user search for "RELEASE_INSTAL_1" with "User ID"
    And user click on set password on "Officers In Group" Screen
    Then user enters Password and Password Confirmation and click in save button
    And Logout from Workbench

  @Author:Aishwarya @IssuerAdmin @IA.002.03.02
  Scenario: Verify Officers Groups
    When user login workbench application for role "Issuer Admin"
    And user navigates to Officer Groups
    Then user search for "INBANK_PCI" with "NAME"
    And user navigates to
      | Officers In Group |
    And user navigates to
      | All |
    Then user search for "AUTO_IN_RPCI_PWDRSET" with "User ID"
    And user click on Reset Failed Login Count on "Officers In Group" Screen
    And user click on OK Button of Dialog Box
    Then validate Reset Failed Login Count Popup Message
    And Logout from Workbench

  @Author:Aishwarya @IssuerAdmin @IA.002.04.01
  Scenario: Verify Officers Groups
    When user login workbench application for role "Issuer Admin"
    And user navigates to Officer Groups
    Then user search for "INBANK_PCI" with "NAME"
    And user navigates to
      | Officers In Group |
    And user navigates to
      | All |
    Then user search for "AUTO_IN_RPCI_PWDRSET" with "User ID"
    And user navigates to
      | Details |
    Then validate "Details" tab title
    And validate Officers groups Details page
    And Logout from Workbench

  @Author:Aishwarya @IssuerAdmin @IA.002.04.02
  Scenario: Verify Officers Groups
    When user login workbench application for role "Issuer Admin"
    And user navigates to Officer Groups
    Then user search for "INBANK_PCI" with "NAME"
    And user navigates to
      | Officers In Group |
    And user navigates to
      | All |
    Then user search for "AUTO_IN_RPCI_PWDRSET" with "User ID"
    And user navigates to
      | Details |
    And user click on set password on "Details" Screen
    Then user enters Password and Password Confirmation and click in save button
    And Logout from Workbench

  @Author:Aishwarya @IssuerAdmin @IA.002.04.03
  Scenario: Verify Officers Groups
    When user login workbench application for role "Issuer Admin"
    And user navigates to Officer Groups
    Then user search for "ZZZ_Release_InBank_PCI" with "NAME"
    And user navigates to
      | Officers In Group |
    And user navigates to
      | All |
    Then user search for "RELEASE_INSTAL_1" with "User ID"
    And user navigates to
      | Details |
    And user click on Reset Failed Login Count on Details Screen
    And user click on OK Button of Dialog Box
    Then validate Reset Failed Login Count Popup Message
    And Logout from Workbench

  @Author:Suvarna @IssuerAdmin @IA.001.01.01 @IA.001.01.02
  Scenario: Officers Groups - By Officer - Search by Officer Name - Search by Officer Name/by User ID/Officer Name or User ID using substring [%]
    When user login workbench application for role "Issuer Admin"
    Then user navigates to Officer Groups
    And validate "Officer Groups" tab title
    Then validate records count
    And  Validate visible are only one "INBANK" client groups
    And Logout from Workbench