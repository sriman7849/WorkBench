package com.mastercard.gpse.processing.utils;

import java.util.Map;

import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.cucumber.adapter.ExtentCucumberAdapter;
import com.aventstack.extentreports.model.Log;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.core.Logger;
import org.testng.Reporter;
import org.testng.asserts.Assertion;
import org.testng.asserts.IAssert;
import org.testng.collections.Maps;

/**
 * When an assertion fails, don't throw an exception but record the failure.
 * Calling {@code assertAll()} will cause an exception to be thrown if at least
 * one assertion failed.
 *
 * @author e141923
 */
public final class SoftAssert extends Assertion {

    private static Logger logg = (Logger) LogManager.getLogger(StringUtility.class);

    // LinkedHashMap to preserve the order
    private Map<AssertionError, IAssert> allErrors = Maps.newLinkedHashMap();

    @Override
    protected void doAssert(IAssert assertCommand) {
        onBeforeAssert(assertCommand);
        try {
            executeAssert(assertCommand);
        } finally {
            onAfterAssert(assertCommand);
        }
    }

    @Override
    public void onAssertSuccess(IAssert assertCommand) {
        ExtentCucumberAdapter.getCurrentStep().createNode("Soft Assert Passed");
        showAssertInfo(assertCommand, (AssertionError) null, false);
    }

    @Override
    public void onAssertFailure(IAssert assertCommand, AssertionError ex) {
        ExtentCucumberAdapter.getCurrentStep().createNode("Soft Assert Failed");
        showAssertInfo(assertCommand, ex, true);
    }

    @Override
    public void executeAssert(IAssert a) {
        try {
            a.doAssert();
            onAssertSuccess(a);
        } catch (AssertionError ex) {
            onAssertFailure(a, ex);
            this.allErrors.put(ex, a);
        }
    }

    private void showAssertInfo(IAssert assertCommand, AssertionError ex, boolean failedTest) {
        StringBuilder sb = new StringBuilder();
        sb.append("&emsp; &#x21DD; &emsp; Soft Assert ");
        if (assertCommand.getMessage() != null && !assertCommand.getMessage().trim().isEmpty())
            sb.append("[").append(assertCommand.getMessage()).append("] ");
        if (failedTest) {
            sb.append("failed").append("\n");
        } else {
            sb.append("passed");
        }
        if (failedTest) {
            sb.append(ExceptionUtils.getStackTrace(ex));
            logg.error(sb.toString());
            ExtentCucumberAdapter.getCurrentStep().log(Status.FAIL, sb.toString());
        } else {
            logg.info(sb.toString());
            ExtentCucumberAdapter.getCurrentStep().log(Status.INFO, sb.toString());
        }
    }

    public void assertAll() {
        if (this.allErrors.isEmpty())
            return;
        StringBuilder sb = new StringBuilder();
        if (1 == this.allErrors.size()) {
            sb.append("A soft assertion failure occurred [\n");
        } else {
            sb.append("Multiple (").append(this.allErrors.size()).append(") soft assertion failures occurred [\n");
        }
        int counter = 0;
        for (Map.Entry<AssertionError, IAssert> eachEntry : this.allErrors.entrySet()) {
            AssertionError eachError = eachEntry.getKey();
            sb.append("\t").append(++counter).append(". ").append(ExceptionUtils.getRootCauseMessage(eachError))
                    .append("\n");
            if (Reporter.getCurrentTestResult() != null) {
                sb.append(
                        StringUtils.substringBetween(ExceptionUtils.getStackTrace(eachError), "\n", "\tat sun.reflect")
                                .replace("\t", "\t\t"));
                continue;
            }
            sb.append(StringUtils.substringAfter(ExceptionUtils.getStackTrace(eachError), "\n").replace("\t", "\t\t"));
        }
        sb.append("\t]");
        if (Reporter.getCurrentTestResult() != null) {
            Reporter.getCurrentTestResult().setThrowable(new AssertionError(sb.toString()));
            Reporter.getCurrentTestResult().setStatus(2);
        } else {
            throw new AssertionError(sb.toString());
        }
    }

}
