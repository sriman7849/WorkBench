package com.mastercard.gpse.processing.workflowsui;

import com.mastercard.gpse.processing.builders.TransactionBuilder;
import com.mastercard.gpse.processing.constants.GlobalConstants;
import com.mastercard.gpse.processing.enums.FieldsEnum;
import com.mastercard.gpse.processing.enums.GroupEnum;
import com.mastercard.gpse.processing.enums.TabTitleEnum;
import com.mastercard.gpse.processing.managers.Report;
import com.mastercard.gpse.processing.pages.CommonPage;
import com.mastercard.gpse.processing.pages.ContractParametersPage;
import com.mastercard.gpse.processing.pages.FieldPage;
import com.mastercard.gpse.processing.utils.*;
import com.mastercard.gpse.processing.workflowsapi.WBPreRequisite;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.core.Logger;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ContractWorkFlow extends AbstractWorkflow {
    private static final Logger logger = (Logger) LogManager.getLogger(CommonPage.class);

    Map<String, String> contractParameterValues = new HashMap<>();
    SoftAssert softAssert = TestContext.softAssert;

    public boolean validateCreditLimitLogData(Map<String, String> financialLimitValue) {
        new WaitUtility().waitForTime(WaitUtility.WAIT_TIMEOUT_IN_MILLI_SEC_THREE_THOUSAND);

        Table table = new CommonPage().getTableObject(TabTitleEnum.CREDIT_LIMIT_LOG.getValue());
        Map<String, String> rowData = table.getRowData(1);
        String amount = rowData.get(FieldsEnum.AMOUNT.getValue());
        amount = amount.substring(0, amount.length() - 6);
        rowData.replace(FieldsEnum.AMOUNT.getValue(), amount);
        if (rowData.get(GlobalConstants.LIMIT_TYPE).equalsIgnoreCase(financialLimitValue.get(GlobalConstants.LIMIT_TYPE)) &&
                rowData.get(FieldsEnum.AMOUNT.getValue()).equalsIgnoreCase(financialLimitValue.get(FieldsEnum.AMOUNT.getValue())) &&
                rowData.get(FieldsEnum.EFFECTIVE_DATE.getValue()).equalsIgnoreCase(financialLimitValue.get(FieldsEnum.EFFECTIVE_DATE.getValue()))
        )
            return true;
        else
            return false;
    }

    public Map<String, String> setFinancialLimit(String limit, String amount, String effectiveDate, String expirationDate, String reason, String comment) {
        new WaitUtility().waitForTime(WaitUtility.WAIT_TIMEOUT_IN_MILLI_SEC_TWO_THOUSAND);
        Map<String, String> financialLimitValue = new HashMap<>();
        new FieldPage().enterTextValue(FieldsEnum.AMOUNT.getValue(), amount);
        new FieldPage().enterTextValue(FieldsEnum.EFFECTIVE_DATE.getValue(), effectiveDate);
        new CommonPage().clickOnDoneBtn();
        new FieldPage().enterTextValue(FieldsEnum.EXPIRATION_DATE.getValue(), expirationDate);
        new CommonPage().clickOnDoneBtn();
        new CommonPage().selectDropDown(FieldsEnum.REASON.getValue());
        new CommonPage().selectDropDownValue(reason);
        new FieldPage().enterTextValue(FieldsEnum.COMMENT.getValue(), comment);
        new CommonPage().clickOnSaveBtn();
        String msg = new CommonPage().getPopUpDialogText();
        if (limit.equalsIgnoreCase(GlobalConstants.ADDITIONAL))
            financialLimitValue.put(GlobalConstants.LIMIT_TYPE, "Additional Credit Limit (Set Temporary)");
        else
            financialLimitValue.put(GlobalConstants.LIMIT_TYPE, "Credit Limit (Set Temporary)");
        financialLimitValue.put(FieldsEnum.AMOUNT.getValue(), amount);
        financialLimitValue.put(FieldsEnum.EFFECTIVE_DATE.getValue(), effectiveDate);
        financialLimitValue.put(FieldsEnum.EXPIRATION_DATE_YYMM.getValue(), expirationDate);
        financialLimitValue.put(GlobalConstants.MESSAGE, msg);
        return financialLimitValue;
    }

    public String updateContractParameter(String contractParameterName) {
        new WaitUtility().waitForTime(WaitUtility.WAIT_TIMEOUT_IN_MILLI_SEC_TWO_THOUSAND);
        new ContractParametersPage().selectContractParameter(contractParameterName);
        new WaitUtility().waitForTime(WaitUtility.WAIT_TIMEOUT_IN_MILLI_SEC_TWO_THOUSAND);
        new CommonPage().clickOnSetParameterButton();
        new WaitUtility().waitForTime(WaitUtility.WAIT_TIMEOUT_IN_MILLI_SEC_THREE_THOUSAND);
        new CommonPage().selectDropDown(FieldsEnum.REASON.getValue());
        new CommonPage().selectDropDownValue(GlobalConstants.REASON_DD_VALUE_BANK_DECISION);
        String contractParameterComment = "Test" + RandomUtils.getRandomNumericString(10);
        new CommonPage().enterComment(contractParameterComment);
        new CommonPage().selectDropDown(FieldsEnum.NEW_VALUE.getValue());
        String newValue = new CommonPage().selectDropDownValueByIndex(Integer.parseInt(RandomUtils.getRandomNumberBetween(1, 20)));
        new CommonPage().clickOnSaveBtn();
        contractParameterValues.put(FieldsEnum.REASON.getValue(), GlobalConstants.REASON_DD_VALUE_BANK_DECISION);
        contractParameterValues.put(FieldsEnum.COMMENT.getValue(), contractParameterComment);
        contractParameterValues.put(FieldsEnum.NEW_VALUE.getValue(), newValue);
        return new CommonPage().getPopUpDialogText();
    }

    public boolean validateUpdateContractParameterHistory() {
        new WaitUtility().waitForTime(WaitUtility.WAIT_TIMEOUT_IN_MILLI_SEC_THREE_THOUSAND);
        boolean flag = false;
        Table historyTable = new CommonPage().getTableObject(TabTitleEnum.HISTORY.getValue());
        List<HashMap<String, String>> tableData = historyTable.getTableData();
        for (HashMap<String, String> row : tableData) {
            if (row.containsValue(contractParameterValues.get(FieldsEnum.NEW_VALUE.getValue())) && row.containsValue(GlobalConstants.ACTIVE))
                flag = true;
        }
        return flag;
    }

    public boolean validateUpdateContractParameterLog() {
        new WaitUtility().waitForTime(WaitUtility.WAIT_TIMEOUT_IN_MILLI_SEC_THREE_THOUSAND);
        boolean flag = false;
        Table changeLogTable = new CommonPage().getTableObject(TabTitleEnum.CHANGE_LOG.getValue());
        Map<String, String> rowData = changeLogTable.getRowData(1);
        if (rowData.get(GlobalConstants.ACTION_TYPE).equalsIgnoreCase(GlobalConstants.ADD_OR_UPDATE) && rowData.get(GlobalConstants.OBJECT_TYPE).equalsIgnoreCase(GlobalConstants.CONTRACT_PARAMETER))
            flag = true;

        return flag;
    }

    public boolean validateChangeLogComment() {
        new WaitUtility().waitForTime(WaitUtility.WAIT_TIMEOUT_IN_MILLI_SEC_THREE_THOUSAND);
        String commentMsg = new CommonPage().getValueOfTextArea(GroupEnum.GENERAL.getValue(), FieldsEnum.COMMENT.getValue());

        if (commentMsg.equalsIgnoreCase(contractParameterValues.get(FieldsEnum.REASON.getValue()) + "." + contractParameterValues.get(FieldsEnum.COMMENT.getValue())))
            return true;
        else
            return false;
    }

    public void validateAccountsDetails() {
        softAssert.assertEquals(new CommonPage().getValueOf(TabTitleEnum.DETAILS.getValue(), GroupEnum.GENERAL.getValue(), FieldsEnum.NAME.getValue()), GlobalConstants.NAME, "Value of Name on Accounts Details page displayed correctly");
    }

    public void validateStatementEntriesDetails() {
        TransactionBuilder debitContract = (TransactionBuilder) WBPreRequisite.GlobalWBTestData.get(GlobalConstants.DEBIT_CONTRACT);
        softAssert.assertTrue(new CommonPage().getValueOf(TabTitleEnum.DETAILS.getValue(), GroupEnum.TRANSACTION_DETAILS.getValue(), FieldsEnum.ENTRY_DESCRIPTION.getValue()).contains(debitContract.getDescription()), "Debit Contract transaction description displayed correctly");
    }
}

