package com.mastercard.gpse.processing.pages;

import com.mastercard.gpse.processing.pages.navigation.Navigation;
import com.mastercard.gpse.processing.pages.navigation.OfficersAndGrantsNav;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;

@Navigation(tabTitle = OfficersAndGrantsNav.TAB_TITLE,
        menuItemsTree = {OfficersAndGrantsNav.L1_CUSTOM_PROFILES_PAGE})
public class CustomProfilesPage extends BasePage{

    public CustomProfilesPage() {
        PageFactory.initElements(driver, this);
    }
    public CustomProfilesPage(WebDriver driver) {
        super(driver);
    }
}
