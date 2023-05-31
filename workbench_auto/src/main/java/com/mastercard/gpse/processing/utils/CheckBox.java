package com.mastercard.gpse.processing.utils;

import com.mastercard.gpse.processing.managers.AllDriverManager;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.core.Logger;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

/**
 * This class is used to perform actions on checkbox.
 *
 * @author e141923
 */
public class CheckBox extends AllDriverManager {

    private final static Logger logger = (Logger) LogManager.getLogger(CheckBox.class);
    private final WebElement element;
    private WebDriver driver = getDriver();

    public CheckBox(WebElement element) {
        this.element = element;
    }

    /**
     * Check whether the checkbox is checked or not
     *
     * @return true if it is checked otherwise false
     */
    public boolean isCheckBoxChecked() {
        return element.isSelected();
    }

    /**
     * Select the checkbox
     */
    public void selectCheckbox() {
        new WaitUtility().waitForElementToBeVisible(element);
        if (!isCheckBoxChecked()) {
            element.click();
        }
    }

    /**
     * Select the checkbox
     *
     * @param element
     */
    public static void selectCheckbox(WebElement element) {
        try {
            new WebElementUtility().waitForElementToBeClickableAfterPageLoading(element);
            String sCheck = element.getAttribute("checked");
            if (sCheck.equalsIgnoreCase("checked"))
                logger.info("Check box selected");
            else
                element.click();
        } catch (NoSuchElementException e) {
            e.getMessage();
        }
    }

    /**
     * Unselect the checkbox if it is already select
     */
    public void unSelectCheckBox() {
        if (isCheckBoxChecked()) {
            element.click();
        }
    }
}