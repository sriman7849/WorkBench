package com.mastercard.gpse.processing.workflowsui;

import com.mastercard.gpse.processing.managers.Report;
import com.mastercard.gpse.processing.pages.ClientPage;
import com.mastercard.gpse.processing.utils.RandomUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.core.Logger;

public class ClientSearchWorkFlow {

    private static final Logger logger = (Logger) LogManager.getLogger(ClientSearchWorkFlow.class);

    public boolean validateClientNumberFromDifferentCustomer() {
        new ClientPage().enterClientNumber(RandomUtils.getNineDigitRandomNumber());
        new ClientPage().clickOnFind();
        Report.info(logger, "User search client by Invalid clientNumber");
        return new ClientPage().availabilityOfDetailsLink();
    }
}