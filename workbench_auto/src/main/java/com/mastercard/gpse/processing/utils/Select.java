package com.mastercard.gpse.processing.utils;

import com.mastercard.gpse.processing.managers.AllDriverManager;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.core.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;

/**
 * This class is used to perform actions on drop-down.
 * 
 * @author e141923
 *
 */
public final class Select extends AllDriverManager {

	private static Logger logger = (Logger) LogManager.getLogger(Select.class);
	private WebDriver driver = getDriver();
	private org.openqa.selenium.support.ui.Select select;

	/**
	 * Constructor
	 *
	 */
	public Select() {
	}

	/**
	 * Constructor
	 *
	 * @param element - drop down WebElement
	 */
	public Select(WebElement element) {
		new WebElementUtility().scrollIntoView(driver, element);
		select = new org.openqa.selenium.support.ui.Select(element);
	}

	/**
	 * Get all the values in a drop down
	 *
	 * @return - List<WebElement>
	 */
	public List<WebElement> getOptions() {
		return select.getOptions();
	}

	/**
	 * Select drop down by visible text
	 *
	 * @param visibleText - text to be selected
	 */
	public void selectDropDownByVisibleText(String visibleText) {
		if(visibleText ==null ||visibleText.isEmpty()) {
			logger.info("Value selected is empty:"+visibleText);
			return;
		}
		select.selectByVisibleText(visibleText);
		logger.info("Value selected:"+visibleText);
	}

	/**
	 * Select drop down by visible text
	 *
	 * @param webElement - drop down WebElement
	 * @param visibleText - text to be selected
	 */
	public static void selectDropDownByVisibleText(WebElement webElement,String visibleText) {
		org.openqa.selenium.support.ui.Select dropdown = new org.openqa.selenium.support.ui.Select(webElement);
		if(visibleText ==null ||visibleText.isEmpty()) {
			logger.info("Value selected is empty:"+visibleText);
			return;
		}
		dropdown.selectByVisibleText(visibleText);
		logger.info("Value selected:"+visibleText);
	}

	/**
	 * Select drop down by value
	 *
	 * @param value - value to be selected
	 */
	public void selectDropDownByValue(String value) {
		select.selectByValue(value);
	}

	/**
	 * Select drop down by value
	 *
	 * @param webElement - drop down WebElement
	 * @param value - value to be selected
	 */
	public static void selectDropDownByValue(WebElement webElement, String value) {
		org.openqa.selenium.support.ui.Select dropdown = new org.openqa.selenium.support.ui.Select(webElement);
		dropdown.selectByValue(value);
	}

	/**
	 * Select drop down by index
	 *
	 * @param index
	 */
	public void selectDropDownByIndex(int index) {
		select.selectByIndex(index);
	}

	/**
	 * Select drop down by index
	 *
	 * @param WebElement
	 * @param index
	 */
	public static void selectDropDownByIndex(WebElement WebElement, int index) {
		org.openqa.selenium.support.ui.Select dropdown = new org.openqa.selenium.support.ui.Select(WebElement);
		dropdown.selectByIndex(index);
	}

	/**
	 * This methods will get the selected dropdown value
	 * 
	 * @return text of the selected element
	 */
	public String getSelectedValue() {
		return select.getFirstSelectedOption().getText().trim();
	}

	/**
	 * Logs the error message
	 *
	 * @param message - message to be logged
	 */
	private void loggError(String message) {
		logger.error(message);
	}
}