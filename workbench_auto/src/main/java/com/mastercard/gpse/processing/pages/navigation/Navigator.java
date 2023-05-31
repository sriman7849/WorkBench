package com.mastercard.gpse.processing.pages.navigation;

import com.mastercard.gpse.processing.managers.PageObjectManager;
import com.mastercard.gpse.processing.managers.Report;
import com.mastercard.gpse.processing.pages.BasePage;
import com.mastercard.gpse.processing.constants.GlobalConstants;
import com.mastercard.gpse.processing.pages.CommonPage;
import com.mastercard.gpse.processing.utils.StringUtility;
import com.mastercard.gpse.processing.utils.WaitUtility;
import com.mastercard.gpse.processing.utils.WebElementUtility;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.core.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.lang.annotation.Annotation;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public final class Navigator extends BasePage {
    private static final Logger logger = (Logger) LogManager.getLogger(Navigator.class);

    public PageObjectManager pageObjFactory = new PageObjectManager();


    private String pageLink = "//span[text()='%s']";
    private String morePageLink = "//span[@ng-bind-html = 'getLinkTitle(link)  | sanitize' and contains(text(),'%s')]//ancestor::div[contains(@class,'br-menu-item')]";
    /**
     * Method navigate to page by page object's class, using @Navigation
     * annotation
     * <p>
     * E.g. Page Object should have following annotation
     *
     * @param pageObjectClass
     * @return
     * @Navigation (tabTitle = CustomerServiceWorkbench.TAB_TITLE, menuItemsTree
     *= { CustomerServiceWorkbench.Clients })
     */
    public <T extends BasePage> T navigateToPage(Class<T> pageObjectClass) {

        Annotation[] annotations = pageObjectClass.getAnnotations();

        for (Annotation annotation : annotations) {
            if (annotation instanceof Navigation) {

                Navigation nav = (Navigation) annotation;

                if (nav.menuItemsTree().length == 0) {
                    navigateToPath(nav.tabTitle());

                } else {
                    navigateToPath(nav.tabTitle(), nav.menuItemsTree());
                }

                break;
            }
        }

        return pageObjFactory.getPage(pageObjectClass);

    }


    /**
     * Method navigates to page by page object's annotation and checks if page
     * is available E.g. Page Object should have following annotation
     *
     * @param pageObjectClass
     * @return
     * @Navigation (tabTitle = CustomerServiceWorkbench.TAB_TITLE, menuItemsTree
     *= { CustomerServiceWorkbench.Clients })
     */
    public <T extends BasePage> boolean isPageAvailable(
            Class<T> pageObjectClass) {

        Annotation[] annotations = pageObjectClass.getAnnotations();
        boolean isPathAvailable = false;

        for (Annotation annotation : annotations) {
            if (annotation instanceof Navigation) {
                Navigation nav = (Navigation) annotation;

                if (nav.menuItemsTree().length == 0) {
                    isPathAvailable = isNavigationPathAvailable(nav.tabTitle());

                } else {
                    isPathAvailable = isNavigationPathAvailable(nav.tabTitle(),
                            nav.menuItemsTree());
                }
            }
        }

        //return isPathAvailable && pageObjFactory.getPage(pageObjectClass).isLoaded();
        return isPathAvailable;
    }


    /**
     * Method click on tab, then click on a menu item, next menu item is taking
     * as nested menu item.
     *
     * @param tabTitle      - tab title
     * @param menuItemsTree - it is array of menuIDs
     */
    public void navigateToPath(String tabTitle, String... menuItemsTree) {
        navigateToPath(tabTitle);
        navigateToPath(menuItemsTree);
    }

    /**
     * Navigate to a tab
     * <p>
     * move to tabName
     */
    public void navigateToPath(String tabTitle) {
        WebElement tab = getElement(By.xpath(String.format(
                "//div[@class = 'title ng-binding' and contains(text(),'%s')]", tabTitle)));
        logger.info(String.format("Navigate to %s Page", tabTitle));
        if (tab != null)
            tab.click();

    }

    /**
     * Navigate to tab
     *
     * @param tabTitle - page title
     * @param subTab   - tab name
     */
    public void navigateToTab(String tabTitle, String subTab) {
        String locator = "//span[@ng-bind-html='getTabTitle(data) | sanitize' and text()='%s']/ancestor::tab//span[contains(@ng-bind-html,'getTitleForQuery(header)') and text()='%s']";
        WebElement tab = getElement(By.xpath(String.format(locator, tabTitle, subTab)));
        if (tab != null)
            tab.click();
        Report.info(logger, String.format("Navigated to %s tab on %s page ", subTab, tabTitle));
    }

    /**
     * Method clicks on each menu items, w/o clicking on tab
     *
     * @param menuItemsTree - menu item's tree
     */
    public void navigateToPath(String... menuItemsTree) {
        WebElement menuItem;

        for (int i = 0; i < menuItemsTree.length; i++) {
            //WebElementUtility.threadDotSleep(5);
            By locator = (i < menuItemsTree.length - 1) ? By
                    .id(menuItemsTree[i]) : By.xpath(String.format(
                    "//span[text()='%s']", menuItemsTree[i]));
            menuItem = getElement(locator);
            if (menuItem != null)
                menuItem.click();

        }
        logger.info(String.format("Navigate to menu items %s",
                Stream.of(menuItemsTree).collect(Collectors.joining(" -> "))));
    }

    /**
     * Method clicks on each menu items, w/o clicking on tab
     *
     * @param links - menu item's tree
     */
    public void navigateToLinks(String... links) {
        WebElement link;
        WebElement moreLink;

        for (int i = 0; i < links.length; i++) {
            new WaitUtility().waitForTime(WaitUtility.WAIT_TIMEOUT_IN_MILLI_SEC_THREE_THOUSAND);

            By locator = By.xpath(String.format(pageLink, links[i]));
            link = getElement(locator);
            if (link != null) {
                link.click();
            } else {
                new CommonPage().clickOnMoreLink();
                By xpath = By.xpath(String.format(morePageLink, links[i]));
                moreLink = getElement(xpath);
                moreLink.click();
            }
            logger.info(String.format("Navigate to menu items %s",
                    Stream.of(links).collect(Collectors.joining(" -> "))));
        }
    }

    /**
     * Navigate to the bottom links pf the given tab
     *
     * @param tabTitle - tab name on which the link is present
     * @param link     - link to be clicked
     */
    public void navigateToTabLink(String tabTitle, String link) {
        new WaitUtility().waitForPageToLoad();
        String locator = "//span[@ng-bind-html='getTabTitle(data) | sanitize' and text()='%s']/ancestor::tab//div[@class='tab-bottom-outer ng-scope']//span[@class='link-span']//span[contains(@ng-bind-html,'getLinkTitle(link)') and text()='%s']";
        new WebElementUtility().clickOnElement(new WebElementUtility().locateElement("xpath", StringUtility.formatExpression(locator, tabTitle, link)));
        new WaitUtility().waitForPageToLoad();
        new WaitUtility().waitForTabTitleTobeVisible(tabTitle);
        Report.info(logger, String.format("Navigated to %s link on %s page ", link, tabTitle));
    }

    /**
     * Click on tab in Widget Mene in Left
     *
     * @param tabTitle - title of the tab to be clicked upon
     */
    public void navigateToWidgetMenuTab(String tabTitle) {
        new WaitUtility().waitForAbsenceOfLocator("//div[contains(@class,'panel animate pane')][1]//div[@title='Refresh' and contains(@class,'loading')]", WaitUtility.WAIT_TIMEOUT_IN_SEC_SIXTY);
        new WaitUtility().waitForTime(1000);
        List<WebElement> tabs = driver.findElements(By.xpath("//div[@class='grid-container']//div[contains(@class,'ui-widget-content slick-row')]//span[@class='cell-content']"));
        for (WebElement tab : tabs) {
            try {
                if (tab.getText().trim().endsWith(tabTitle)) {
                    tab.click();
                }
            } catch (Exception e) {
            }
        }
        Report.info(logger, "Clicked on " + tabTitle);
    }

    /**
     * Methods walks through navigation (starts from tab) and check if menu
     * items is available
     *
     * @param tabTitle
     * @param menuItemsTree
     * @return boolean
     */
    public boolean isNavigationPathAvailable(String tabTitle,
                                             String... menuItemsTree) {

        return isNavigationPathAvailable(tabTitle)
                && isNavigationPathAvailable(menuItemsTree);
    }

    /**
     * Methods walks through navigation (w/o clicking on tab) and check if menu
     * items is available
     *
     * @param menuItemsTree
     * @return boolean
     */
    public boolean isNavigationPathAvailable(String... menuItemsTree) {
        boolean isAvailable = false;
        for (int i = 0; i < menuItemsTree.length; i++) {
            WebElement menuItem;
            // find elements
            By locator = (i < menuItemsTree.length - 1) ? By
                    .id(menuItemsTree[i]) : By.xpath(String.format(
                    "//*[@id='%s']/a", menuItemsTree[i]));
            menuItem = getElement(locator);

            if (null == menuItem)
                return false;

            isAvailable = true;
            menuItem.click();
        }
        return isAvailable;
    }

    /**
     * Method checks if the tab is available
     *
     * @param tabTitle
     * @return boolean
     */
    public boolean isNavigationPathAvailable(String tabTitle) {
        WebElement tab = getElement(By.xpath(String.format(
                "//a[contains(@title,'%s')]", tabTitle)));
        if (null == tab)
            return false;
        tab.click();
        return true;
    }

    /**
     * Method checks if the page link is available
     *
     * @param link page link
     */
    public boolean isNavigationLinkAvailable(String link) {
        new WaitUtility().waitForAbsenceOfLocator("//div[contains(@class,'panel animate pane')][1]//div[@title='Refresh' and contains(@class,'loading')]", WaitUtility.WAIT_TIMEOUT_IN_SEC_SIXTY);
        new WaitUtility().waitForTime(1000);
        return driver.findElement(By.xpath(String.format(pageLink, link))).isDisplayed();
    }

    private WebElement getElement(By locator) {
        WebElement elem;
        try {
            elem = new WebDriverWait(getDriver(),
                    (GlobalConstants.PAGE_LOADING_TIME_SEC))
                    .until(ExpectedConditions.elementToBeClickable(locator));
        } catch (TimeoutException e) {
            elem = null;
        }

        return elem;
    }

    /**
     * move to pop up
     */
    public <T extends BasePage> T navigateToPopUp(Class<T> pageObjectClass) {
        return pageObjFactory.getPage(pageObjectClass);

    }

    /**
     * @SEE Function for get page factory.
     */
    public <T extends BasePage> T getPageFactoryToPage(
            Class<T> pageObjectClass) {
        return pageObjFactory.getPage(pageObjectClass);

    }
}
