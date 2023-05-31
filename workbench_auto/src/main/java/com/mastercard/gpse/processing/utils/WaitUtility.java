package com.mastercard.gpse.processing.utils;

import com.mastercard.gpse.processing.managers.AllDriverManager;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.core.Logger;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.File;
import java.util.concurrent.TimeUnit;

public class WaitUtility extends AllDriverManager {

    private final Logger logger = (Logger) LogManager.getLogger(WaitUtility.class);
    private final WebDriver driver = getDriver();
    protected static WebDriverWait wait;
    protected static FluentWait<WebDriver> fluentWait;

    //Wait Constants
    public static final long DEFAULT_WAIT_TIMEOUT_IN_SEC = 60;
    public static final long WAIT_TIMEOUT_IN_SEC_TWO = 2;
    public static final long WAIT_TIMEOUT_IN_SEC_FIVE = 5;
    public static final long WAIT_TIMEOUT_IN_SEC_TEN = 10;
    public static final long WAIT_TIMEOUT_IN_SEC_TWENTY = 20;
    public static final long WAIT_TIMEOUT_IN_SEC_THIRTY = 30;
    public static final long WAIT_TIMEOUT_IN_SEC_FOURTY = 40;
    public static final long WAIT_TIMEOUT_IN_SEC_FIFTY = 50;
    public static final long WAIT_TIMEOUT_IN_SEC_SIXTY = 60;
    public static final long WAIT_TIMEOUT_IN_SEC_ONE_HUNDRED_TWENTY = 120;
    public static final long WAIT_TIMEOUT_IN_MILLI_SEC_FIVE_HUNDRED = 500;
    public static final long WAIT_TIMEOUT_IN_MILLI_SEC_ONE_THOUSAND = 1000;
    public static final long WAIT_TIMEOUT_IN_MILLI_SEC_TWO_THOUSAND = 2000;
    public static final long WAIT_TIMEOUT_IN_MILLI_SEC_THREE_THOUSAND = 3000;
    public static final long WAIT_TIMEOUT_IN_MILLI_SEC_FOUR_THOUSAND = 4000;
    public static final long WAIT_TIMEOUT_IN_MILLI_SEC_FIVE_THOUSAND = 5000;
    public static final long WAIT_TIMEOUT_IN_MILLI_SEC_SIXTY_THOUSAND = 60000;

    public WebDriverWait getWebDriverWait() {
        return new WebDriverWait(driver, DEFAULT_WAIT_TIMEOUT_IN_SEC);
    }

    /**
     * Wait for page to loaded completely
     */
    public void waitForPageToLoad() {
        wait = new WebDriverWait(driver, DEFAULT_WAIT_TIMEOUT_IN_SEC);
        wait.until((ExpectedCondition<Boolean>) wd -> (((JavascriptExecutor) wd)
                .executeScript("return document.readyState").equals("DONE")
                || ((JavascriptExecutor) wd).executeScript("return document.readyState").equals("complete"))
                && !((JavascriptExecutor) wd).executeScript("return document.readyState").equals("loading")
                && !((JavascriptExecutor) wd).executeScript("return document.readyState").equals("interactive"));
    }

    public void waitForTabTitleTobeVisible(String tabName){
        String locator = "//span[@ng-bind-html='getTabTitle(data) | sanitize' and  text()='%s']";
        waitForLocatorToBePresent(StringUtility.formatExpression(locator, tabName), WAIT_TIMEOUT_IN_SEC_TWENTY);
        WebElement element = new WebElementUtility().locateElement("xpath", StringUtility.formatExpression(locator, tabName));
        waitForElementToBeVisible(element, WAIT_TIMEOUT_IN_SEC_TWENTY);
    }

    /**
     * Wait for the table to reload data after there has been any action on the table (Including filters)
     * <p>
     * scroll to the bottom of page height
     * then wait for 2000 milliseconds
     * then scroll to the top of page height
     */
    public void waitForTableToReload() {
        JavascriptExecutor executor = (JavascriptExecutor) driver;
        try {
            executor.executeScript("window.scrollBy(0,document.body.scrollHeight)");
            new WaitUtility().waitForTime(WaitUtility.WAIT_TIMEOUT_IN_MILLI_SEC_TWO_THOUSAND);
            executor.executeScript("window.scrollBy(0,-document.body.scrollHeight)");
        } catch (Exception e) {
        }
    }

    /**
     * Wait till the element is invisible for a given time in
     * seconds
     *
     * @param element - WebElement for which the invisibility needs to be checked
     */
    public void waitForElementToBeInvisible(WebElement element) {
        wait = new WebDriverWait(driver, DEFAULT_WAIT_TIMEOUT_IN_SEC);
        wait.until(ExpectedConditions.invisibilityOf(element));
    }

    /**
     * Wait till the element is invisible for a given time in
     * seconds
     *
     * @param element  - WebElement for which the invisibility needs to be checked
     * @param waitTime - The timeout in seconds when an expectation is called
     */
    public void waitForElementToBeInvisible(WebElement element, long waitTime) {
        wait = new WebDriverWait(driver, waitTime);
        wait.until(ExpectedConditions.invisibilityOf(element));
    }

    /**
     * Wait till the element is visible for a given time in
     * seconds
     *
     * @param element - WebElement for which the visibility needs to be checked
     */
    public void waitForElementToBeVisible(WebElement element) {
        wait = new WebDriverWait(driver, DEFAULT_WAIT_TIMEOUT_IN_SEC);
        wait.until(ExpectedConditions.visibilityOf(element));
    }

    /**
     * Wait till the element is visible for a given time in
     * seconds
     *
     * @param element  - WebElement for which the visibility needs to be checked
     * @param waitTime - The timeout in seconds when an expectation is called
     */
    public void waitForElementToBeVisible(WebElement element, long waitTime) {
        wait = new WebDriverWait(driver, waitTime);
        wait.until(ExpectedConditions.visibilityOf(element));
    }

    /**
     * Wait till the element is clickable with in Default
     * Timeout for 60 seconds
     *
     * @param element - WebElement for which the clickability needs to be checked
     */
    public void waitForElementToBeClickable(WebElement element) {
        wait = new WebDriverWait(driver, DEFAULT_WAIT_TIMEOUT_IN_SEC);
        try {
            wait.until(ExpectedConditions.elementToBeClickable(element));
        } catch (StaleElementReferenceException e) {
            waitForPageToLoad();
            wait.until(ExpectedConditions.elementToBeClickable(element));
        }
    }

    /**
     * Wait till the element is clickable for a given time in
     * seconds
     *
     * @param element  - WebElement for which the clickability needs to be checked
     * @param waitTime - The timeout in seconds when an expectation is called
     */
    public void waitForElementToBeClickable(WebElement element, long waitTime) {
        wait = new WebDriverWait(driver, waitTime);
        try {
            wait.until(ExpectedConditions.elementToBeClickable(element));
        } catch (StaleElementReferenceException e) {
            waitForPageToLoad();
            wait.until(ExpectedConditions.elementToBeClickable(element));
        }
    }

    /**
     * Wait till the presence of locator in a given time
     *
     * @param locator  - The locator for which the presence needs to be checked
     * @param waitTime - The timeout in seconds when an expectation is called
     * @return returns webelement for given locator value
     */
    public WebElement waitForLocatorToBePresent(String locator, long waitTime) {
        By loc = new WebElementUtility().resolveByType("xpath", locator);
        wait = new WebDriverWait(driver, waitTime);
        return wait.until(ExpectedConditions.presenceOfElementLocated(loc));
    }

    /**
     * Wait till the absence of locator in a given time
     *
     * @param locator  - The locator for which the absence needs to be checked
     * @param waitTime - The timeout in seconds when an expectation is called
     */
    public void waitForAbsenceOfLocator(String locator, long waitTime) {
        wait = new WebDriverWait(driver, waitTime);
        wait.until(new ExpectedCondition<Boolean>() {
            @Override
            public Boolean apply(WebDriver input) {
                try {
                    return !(driver.findElements(new WebElementUtility().resolveByType("xpath", locator)).size() > 0);
                } catch (NoSuchElementException e) {
                    return true;
                }
            }
        });
    }

    /**
     * Wait till element text to be present in a given time
     *
     * @param element  - Element value
     * @param waitTime - The timeout in seconds when an expectation is called
     * @param text     - Text to be present
     */
    public void waitForElementTextToBePresent(WebElement element, long waitTime, String text) {
        wait = new WebDriverWait(driver, waitTime);
        wait.until(ExpectedConditions.textToBePresentInElement(element, text));
    }

    /**
     * Wait till visibility of alert dialog in given time
     *
     * @param waitTime - The timeout in seconds when an expectation is called
     */
    public void waitForVisibilityOfAlertDialog(By locator, long waitTime) {
        fluentWait = new FluentWait<>(driver).withTimeout(30, TimeUnit.SECONDS).pollingEvery(200, TimeUnit.MILLISECONDS)
                .ignoring(NoSuchElementException.class);
        fluentWait.until(ExpectedConditions
                .presenceOfElementLocated(locator));
    }

    /**
     * Wait till Invisibility of alert dialog in given time
     *
     * @param waitTime - The timeout in seconds when an expectation is called
     */
    public void waitForInVisibilityOfAlertDialog(WebElement element, long waitTime) {
        fluentWait = new FluentWait<>(driver).withTimeout(30, TimeUnit.SECONDS).pollingEvery(200, TimeUnit.MILLISECONDS)
                .ignoring(NoSuchElementException.class);

        fluentWait.until(
                ExpectedConditions.invisibilityOfAllElements(element));
    }

    /**
     * Sleep the thread
     *
     * @param waitTime - Time in milliseconds for hread to sleep
     */
    public void waitForTime(long waitTime) {
        try {
            Thread.sleep(waitTime);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    /**
     * Thread sleep method
     *
     * @param tme - time in milliseconds
     */
    public void threadDotSleep(int tme) {
        try {
            Thread.sleep(tme);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }

    /**
     * Wait for the given amount of time for a file to be present
     *
     * @param filePath - file path
     * @param waitTime - maximum wait time
     */
    public void waitForFileToBePresent(String filePath, long waitTime) {
        wait = new WebDriverWait(driver, waitTime);
        wait.until(new ExpectedCondition<Boolean>() {
            @Override
            public Boolean apply(WebDriver driver) {
                File f = new File(filePath);
                return f.exists();
            }

            @Override
            public String toString() {
                return String.format("file to be present within the time specified");
            }
        });
    }
}
