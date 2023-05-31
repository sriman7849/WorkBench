package com.mastercard.gpse.processing.utils;

import com.mastercard.gpse.processing.managers.AllDriverManager;
import com.mastercard.gpse.processing.managers.Report;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.core.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;

/**
 * This class is used to perform actions on drop-down.
 * 
 * @author e141923
 *
 */
public final class DropDown extends AllDriverManager {

	private static final Logger logger = (Logger) LogManager.getLogger(DropDown.class);
	private final WebDriver driver = getDriver();

	public void clickOnDropdown(String fieldName) {
		String locator = "//div[@class='form-label' and @title='%s']/ancestor::field-block//div[@ng-class='{open: $select.open}']";
		WebElement field = new WebElementUtility().locateElement("xpath", StringUtility.formatExpression(locator, fieldName));
		new WebElementUtility().scrollIntoMiddle(field);
		field.click();
		Report.info(logger, "Clicked on " + fieldName + " dropdown");
	}

	/**
	 * Get all the values in a drop down
	 *
	 * @return - List<WebElement>
	 */
	public List<WebElement> getOptions() {
		String locator = "//span[contains(@ng-bind-html,'highlight: $select.search')]/ancestor::div[contains(@ng-attr-id,'ui-select-choices-row')]";
		return driver.findElements(By.xpath(locator));
	}

	/**
	 * Select drop down by visible text
	 *
	 * @param visibleText - text to be selected
	 */
	public void selectDropDownByVisibleText(String visibleText) {
		String locator = "//span[contains(@ng-bind-html,'highlight: $select.search') and text()='%s']/ancestor::div[contains(@ng-attr-id,'ui-select-choices-row')]";
		WebElement dropDownOption = new WebElementUtility().locateElement("xpath", StringUtility.formatExpression(locator, visibleText));
		new WebElementUtility().scrollIntoView(driver, dropDownOption);
		dropDownOption.click();
		Report.info(logger, "Value selected:" + visibleText);
	}

	/**
	 * Click on the drop down and Select the value by visible text
	 *
	 * @param fieldName - drop down field name
	 * @param visibleText - drop down text to be selected
	 */
	public void selectDropDownByVisibleText(String fieldName, String visibleText) {
		String dropDownLocator = "//div[@class='form-label' and @title='%s']/ancestor::field-block//div[@ng-class='{open: $select.open}']";
		WebElement field = new WebElementUtility().locateElement("xpath", StringUtility.formatExpression(dropDownLocator, fieldName));
		new WebElementUtility().scrollIntoMiddle(field);
		field.click();

		String locator = "//span[contains(@ng-bind-html,'highlight: $select.search') and text()='%s']/ancestor::div[contains(@ng-attr-id,'ui-select-choices-row')]";
		WebElement dropDownOption = new WebElementUtility().locateElement("xpath", StringUtility.formatExpression(locator, visibleText));
		new WebElementUtility().scrollIntoView(driver, dropDownOption);
		dropDownOption.click();
		Report.info(logger, "Selected value '" + visibleText + "' in field '" + fieldName + "'" );
	}

	/**
	 * Select drop down by index
	 *
	 * @param index - row index to be selected
	 */
	public void selectDropDownByIndex(int index) {
		String locator = "(//span[contains(@ng-bind-html,'highlight: $select.search')]/ancestor::div[@role='option'])["+index+"]";
		WebElement dropDownOption = new WebElementUtility().locateElement("xpath", locator);
		new WebElementUtility().scrollIntoView(driver, dropDownOption);
		dropDownOption.click();
		Report.info(logger, "Selected index in the dropdown: " + index);
	}
	/**
	 * Click on the drop down and Select drop down by index
	 *
	 * @param fieldName - drop down field name
	 * @param index - row index to be selected
	 */
	public void selectDropDownByIndex(String fieldName, int index) {
		String dropDownLocator = "//div[@class='form-label' and @title='%s']/ancestor::field-block//div[@ng-class='{open: $select.open}']";
		WebElement field = new WebElementUtility().locateElement("xpath", StringUtility.formatExpression(dropDownLocator, fieldName));
		new WebElementUtility().scrollIntoMiddle(field);
		field.click();

		String locator = "(//span[contains(@ng-bind-html,'highlight: $select.search')]/ancestor::div[@role='option'])["+index+"]";
		WebElement dropDownOption = new WebElementUtility().locateElement("xpath", locator);
		new WebElementUtility().scrollIntoView(driver, dropDownOption);
		dropDownOption.click();
		Report.info(logger, "Selected index '" + index + "' in " + fieldName + " dropdown");
	}
}