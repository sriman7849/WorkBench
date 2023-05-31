package com.mastercard.gpse.processing.managers;

import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.cucumber.adapter.ExtentCucumberAdapter;
import org.apache.logging.log4j.core.Logger;

public class Report {

    public static void info(Logger logger, String message){
        logger.info(message);
        ExtentCucumberAdapter.getCurrentStep().log(Status.INFO, "&emsp; <font color=black>&#9432;</font></b> &emsp; <font style=\"font-family:Courier New;\">" +  message + "</font>");
    }

    public static void warn(Logger logger, String message){
        logger.warn(message);
        ExtentCucumberAdapter.getCurrentStep().log(Status.WARNING, "&emsp; <b><font color=yellow>&#9888;</font></b> &emsp; <font style=\"font-family:Courier New;\">" +  message + "</font>");
    }

    public static void fail(Logger logger, String message){
        logger.fatal(message);
        ExtentCucumberAdapter.getCurrentStep().log(Status.FAIL, "&emsp; <b><font color=red>&#9447;</font></b> &emsp; <font style=\"font-family:Courier New;\" color=red>" +  message + "</font>");
    }
}
