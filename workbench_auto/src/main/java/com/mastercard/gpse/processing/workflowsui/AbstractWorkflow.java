package com.mastercard.gpse.processing.workflowsui;

import com.mastercard.gpse.processing.managers.PageObjectManager;

import java.util.HashMap;

public class AbstractWorkflow {

    public static HashMap<String,String> testContext = new HashMap<>();
    public PageObjectManager pageObjectFactory = new PageObjectManager();
}
