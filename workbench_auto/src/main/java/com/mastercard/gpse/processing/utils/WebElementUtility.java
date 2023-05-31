package com.mastercard.gpse.processing.utils;

import com.google.common.base.Preconditions;
import com.mastercard.gpse.processing.constants.Constants;
import com.mastercard.gpse.processing.constants.GlobalConstants;
import com.mastercard.gpse.processing.fileoperations.TestProperties;
import com.mastercard.gpse.processing.managers.Report;
import com.mastercard.gpse.processing.pages.BasePage;
import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.core.Logger;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.File;
import java.util.List;

public class WebElementUtility extends BasePage {

    private final static Logger logger = (Logger) LogManager.getLogger(WebElementUtility.class);

    WebDriver driver;

    public WebElementUtility() {
        driver = getDriver();
    }

    /**
     * This method is used to get Table Object, converting WebElement to Table
     *
     * @param element -
     */
    public Table getTableObject(WebElement element) {
        return new Table(element);
    }

    public Table getTableObject(String tabTitle) {
        String locator = "//span[@ng-bind-html='getTabTitle(data) | sanitize' and text()='%s']/ancestor::tab//div[contains(@class,'content-element')]";
        WebElement element = new WebElementUtility().locateElement("xpath", StringUtility.formatExpression(locator, tabTitle));
        return new Table(element);
    }

    public boolean isElementVisible(WebElement element) {
        boolean b = false;
        try {
            b = element.isDisplayed();
        } catch (NoSuchElementException e) {
            Report.info(logger, "exception occured while searching the element");
            Report.warn(logger, "exception occured while searching the element: " + e.toString());
        }
        return b;
    }

    /**
     * This method is used to check whether element is visible or not
     *
     * @param element - check visibility for this webelement
     * @return true if element is visible otherwise false
     */
    public boolean isElementVisibleWithoutWarning(WebElement element) {
        boolean b = false;
        try {
            b = element.isDisplayed();
        } catch (NoSuchElementException e) {
        }
        return b;
    }

    /**
     * Wait for element to be clickable
     *
     * @param element - object of WebElement which needs to be clicked
     */
    public void waitForElementToBeClickableAfterPageLoading(WebElement element) {
        new WebDriverWait(webDriver,
                (GlobalConstants.WAITING_FOR_ELEMENT_PRESENT_SEC))//, MIN_TIMEOUT)
                .until(ExpectedConditions.elementToBeClickable(element));
    }

    /**
     * Get title attribute of the WebElement
     *
     * @param element - WebElement for which title attribute needs to be retrieved
     * @return title of the web element
     */
    public String getElementTitleAttributeValue(WebElement element) {
        waitForElementToBeClickableAfterPageLoading(element);
        return element.getAttribute("title");
    }

    public String getElementText(WebElement element) {
        try {
            new WaitUtility().waitForElementToBeVisible(element, WaitUtility.WAIT_TIMEOUT_IN_SEC_TWENTY);
        } catch (StaleElementReferenceException e) {
            e.printStackTrace();
        }
        return element.getText();
    }

    /**
     * Get the value of the WebElement
     *
     * @param element - WebElement for which value attribute needs to be retrieved
     * @return value of the web element
     */
    public String getElementValueAttributeValue(WebElement element) {
        //waitForElementToBeClickableAfterPageLoading(element);
        return element.getAttribute("value");
    }

    /**
     * SendKeys into the WebElement after clearing any existing value
     *
     * @param element - input web element
     * @param value   - value to be sent
     */
    public void clearAndSet(WebElement element, String value) {
        if ((element == null) || (StringUtils.isBlank(value))) {
            logger.info("Element is null");
            return;
        }
        element.clear();
        element.sendKeys(value);
        logger.info("Entered Value: " + value);
    }
    /**
     * SendKeys into the WebElement after clearing any existing value
     *
     * @param element - input web element
     * @param value   - value to be sent
     */
    public void enterText(WebElement element, String value) {
        if ((element == null) || (StringUtils.isBlank(value))) {
            logger.info("Element is null");
            return;
        }
        element.clear();
        element.sendKeys(value);
        logger.info("Entered Value: " + value);
    }


    /**
     * Hover over a WebElement
     *
     * @param element - element on which Hover needs to be performed
     */
    public void hoverWebElement(WebElement element) {
        waitForElementToBeClickableAfterPageLoading(element);
        Actions action = new Actions(webDriver);
        action.moveToElement(element).build().perform();
    }

    /**
     * Clicks on WebElement
     *
     * @param more - WebElement to be clicked
     */
    public void clickOnElement(WebElement more) {
        new WebElementUtility().waitForElementToBeClickableAfterPageLoading(more);
        /*actions.moveToElement(more);
        actions.click().perform();*/
        more.click();
    }

    /**
     * Clicks on WebElement
     *
     * @param element - WebElement to be clicked
     */
    public void clickOnElement(WebElement element, String fieldName) {
        new WebElementUtility().waitForElementToBeClickableAfterPageLoading(element);
        element.click();
        Report.info(logger, "Clicked On " + fieldName);
    }

    /**
     * Clicks on WebElement using JavascriptExecutor
     *
     * @param element - WebElement to be clicked
     */
    public void JSClickOnElement(WebElement element) {
        JavascriptExecutor executor = (JavascriptExecutor) webDriver;
        executor.executeScript("arguments[0].click();", element);
    }

    /**
     * Check if the button element is displayed or not
     *
     * @param element - button element
     * @return true if element displayed else false
     */
    public boolean checkBtnAvailability(WebElement element) {
        try {
            waitForElementToBeClickableAfterPageLoading(element);
            return element.isDisplayed();
        } catch (Exception e) {
            logger.info(e);
            return false;
        }
    }

    /**
     * Close the tab with given title
     *
     * @param title - title of the tab to be closed
     */
    public void closeTab(String title) {
        String str = String.format("//div[@class='tab-label selected']/span[text()='%s']/parent::div//following-sibling::div[@class='tab-label br-step br-right icon-links remove-icon']", title);
        WebElement closeBtn = new WebElementUtility().locateElement("xpath", str);
        new WebElementUtility().clickOnElement(closeBtn);
    }

    /**
     * This method is used to navigate back to page in the browser & wait for page
     * to load
     */
    public void navigateBackToPreviousPage(int noOfTimes) {
        for (int i = 0; i < noOfTimes; i++) {
            driver.navigate().back();
            new WaitUtility().waitForTime(WaitUtility.WAIT_TIMEOUT_IN_MILLI_SEC_FIVE_THOUSAND);
        }
        logger.info("User navigated to back to previous page.");
    }

    /**
     * Resolves the locator
     *
     * @param typeOfLocator - type of the locator(e.g. - id, xpath, css etc..)
     * @param locator       - value of the locator
     * @return resolves the type of the locator
     */
    public By resolveByType(String typeOfLocator, String locator) {
        Preconditions.checkArgument(StringUtils.isNotBlank(typeOfLocator), "Locator type cannot be null (or) empty.");
        Preconditions.checkArgument(StringUtils.isNotBlank(locator), "Locator cannot be null (or) empty.");
        String type = typeOfLocator.toLowerCase();
        By by = null;
        switch (type) {
            case "id":
                by = By.id(locator);
                break;
            case "className":
                by = By.className(locator);
                break;
            case "tagName":
                by = By.tagName(locator);
                break;
            case "xpath":
                by = By.xpath(locator);
                break;
            case "cssSelector":
                by = By.cssSelector(locator);
                break;
            case "linkText":
                by = By.linkText(locator);
                break;
            case "name":
                by = By.name(locator);
                break;
            case "partialLinkText":
                by = By.partialLinkText(locator);
                break;
            default:
                throw new IllegalStateException("Type : " + type + " not found!!!");
        }
        return by;
    }

    /**
     * This method is used to scroll into view
     *
     * @param driver  - driver reference of the browser
     * @param element - perform action on this element
     */
    public void scrollIntoView(WebDriver driver, WebElement element) {
        new WaitUtility().waitForTime(WaitUtility.WAIT_TIMEOUT_IN_MILLI_SEC_FIVE_THOUSAND);
        JavascriptExecutor executor = (JavascriptExecutor) driver;
        try {
            executor.executeScript("arguments[0].scrollIntoView(true);", element);
        } catch (StaleElementReferenceException e) {
            new WaitUtility().waitForPageToLoad();
            executor.executeScript("arguments[0].scrollIntoView(true);", element);
        }
    }

    /**
     * Scroll the WebElement into the middle of the screen
     *
     * @param element - WebElement to be scrolled
     */
    public void scrollIntoMiddle(WebElement element) {
        JavascriptExecutor executor = (JavascriptExecutor) driver;
        try {
            executor.executeScript("arguments[0].scrollIntoView({block: 'center', inline: 'nearest'});", element);
        } catch (StaleElementReferenceException e) {
            new WaitUtility().waitForPageToLoad();
            executor.executeScript("arguments[0].scrollIntoView({block: 'center', inline: 'nearest'});", element);
        }
    }

    /**
     * Get the name of the screenshot file
     *
     * @param methodName - current being invoked
     * @return name of the screenshot file
     */
    public String getFileNameForScreenshot(String methodName) {
        String ns = "_"; //name separator
        return testContext.get(Constants.TEST_CASE_ID) + ns
                + testContext.get(Constants.TEST_CASE_NAME) + ns
                + methodName + ns
                + DateUtility.getCurrentDate();
    }

    /**
     * Locate list of webelements
     *
     * @param typeOfLocator - type of the locator(e.g. - id, xpath, css etc..)
     * @param locator       - value of the locator
     * @return
     */
    public WebElement locateElement(String typeOfLocator, String locator) {
        By locatorBy = resolveByType(typeOfLocator, locator);
        WebElement webElementFound = driver.findElement(locatorBy);
        return webElementFound;
    }

    /**
     * Take the screenshot and store at the given path with given file name
     *
     * @param fileName    - name by which screenshot file will be saved
     * @param testCaseDir - path at which screenshot file will be saved
     * @return return the screenshot data in the form of byte[]
     */
    public byte[] getScreenshot(String fileName, String testCaseDir) {

        String dirPath = TestProperties.getInstance().getEnvProperty("wb.screenshots.dir.path");
        String screenShotLocation = Constants.USER_DIR + Constants.PATH_SEPARATOR
                + dirPath + Constants.PATH_SEPARATOR + testCaseDir;
        FileUtility.getDir(screenShotLocation);
        //logger.info("Screenshot Location: "+screenShotLocation);
        try {
            File srcFile = ((TakesScreenshot) webDriver).getScreenshotAs(OutputType.FILE);
            File destFile = new File(screenShotLocation + Constants.PATH_SEPARATOR + fileName + ".png");
            FileUtils.copyFile(srcFile, destFile);
            return FileUtility.getFileByteArray(destFile);
        } catch (Exception e) {
            logger.error("Error Taking Screenshot" + e.getMessage(), e);
            return null;
        }
    }
    public List<WebElement> getListOfElemnets()
    {
        List<WebElement> els=driver.findElements(By.xpath("//div[contains(@class,'r0')]//span[@class='cell-content']"));
        return els;
    }

}