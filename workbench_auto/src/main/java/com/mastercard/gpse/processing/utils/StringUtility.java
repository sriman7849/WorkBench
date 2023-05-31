
package com.mastercard.gpse.processing.utils;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.jboss.aerogear.security.otp.Totp;

import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.text.NumberFormat;
import java.util.Arrays;
import java.util.List;
import java.util.Locale;
import java.util.Random;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class StringUtility {
    private static final Logger logger = LogManager.getLogger(StringUtility.class);

    /**
     * Compares two strings ignoring the cases
     *
     * @param expectedValue - expected string value
     * @param actualValue - actual string value
     * @return true if two strings are equal else false
     */
    public static boolean verifyValue(String expectedValue, String actualValue){
        boolean flag = true;
        if(expectedValue.equalsIgnoreCase(actualValue)){
            String msg = "Expected Value: "+ expectedValue + " = Actual Value: "+ actualValue;
            logger.info(msg);
        } else {
            String msg = "Expected Value: "+ expectedValue + " != Actual Value: "+ actualValue;
            logger.error(msg);
            flag = false;
        }
        return flag;
    }

    /**
     * Check if all the values of the given actual String[] are present in the expected String[] or not
     *
     * @param expectedArray - expected String[]
     * @param actualArray - actual String[]
     * @return true if all the values of actual String[] exists in expected String[] else false
     */
    public static boolean compareStringArrays(String[] expectedArray, String actualArray[]){
        boolean flag = true;
        for(String value: expectedArray){
            flag = searchArray(actualArray, value);
            if(!flag)
                return flag;
        }
        return flag;
    }

    /**
     * This method is used to convert string values to list
     *
     * @param value    - string value to convert into list
     * @param splitter - splitter value
     * @return list of string
     */
    public static List<String> convertStringToList(String value, String splitter) {
        List<String> list = Arrays.asList(value.split(splitter));
        return list;
    }

    /**
     * Reverse the string
     *
     * @param str - string to be reversed
     * @return reversed string
     */
    public static String reverseString(String str){
        StringBuilder reversedString = new StringBuilder();
        reversedString.append(str);
        reversedString.reverse();
        return reversedString.toString();
    }

    /**
     * Check if the given value is present in the given String[] or not
     *
     * @param actualArray - given array of string
     * @param value - value to be searched
     * @return true if value exists in the String[] else false
     */
    private static boolean searchArray(String[] actualArray, String value) {
        for(String actualValue: actualArray){
            if(value.equalsIgnoreCase(actualValue)){
                logger.info("Expected Value: "+ value + " Present");
                return true;
            }
        }
        logger.error("Expected Value: "+ value + " Not Present");
        return false;
    }

    /**
     * Check both the arguments are not null
     *
     * @param title - string argument 1
     * @param field - string argument 2
     * @return true if both the arguments are not null else false
     */
    public static boolean checkNullValue(String title, String field){
        boolean flag = true;
        if(title == null){
            logger.error(field + ": " + title + " is Null");
            return false;
        }
        if(title.length() == 0) {
            logger.error(field + " is Null");
            flag = false;
        } else
            logger.info(field + ": " + title + " is Not Null");

        return flag;
    }

    /**
     * This method is used to returns the formatted string by given format and
     * arguments.
     *
     * @param format
     * @param args
     * @return formatted string
     */
    public static String formatExpression(String format, Object... args) {
        return String.format(format, args);
    }

    /**
     * This method is used to check if the string contains the given pattern
     *
     * @param content      - String in which pattern is to be searched
     * @param regexPattern
     * @return
     */
    public static boolean isRegexPatternMatching(String content, String regexPattern) {
        Pattern p = Pattern.compile(regexPattern, Pattern.CASE_INSENSITIVE);
        Matcher m = p.matcher(content);
        return m.find();
    }

    /**
     * This method is used to extract number from string Eg. Pass '10 Kilo Grams' to
     * this function it will return output as 10
     *
     * @param data - String having number
     * @return - extracting number from string
     */
    public String extractNumberFromString(String data) {
        // Regular expression to digits
        String regex = "([0-9]+)";
        // Creating a pattern object
        Pattern pattern = Pattern.compile(regex);
        // Creating a Matcher object
        Matcher matcher = pattern.matcher(data);
        matcher.find();
        return matcher.group();
    }


    /**
     * Convert the given whole number (string type) into Amount format<br><br>
     * Example: 56461654.25 will be converted to 56,461,654.25
     *
     * @param convertVal - number (string type) to be converted
     * @return number in amount format
     */
    public static Number AmountFormat(String convertVal){

        String value = convertVal;
        Number number = null;
        char c = value.charAt(0);

        if (Character.isDigit(c)) {
        } else {
            value = value.substring(1);
        }
        try {
            NumberFormat format = NumberFormat.getInstance();
            number = format.parse(value);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return number;
    }

    /**
     * This method is used to convert the amount into currency format. <br><br>
     * Example 1000.00 will be converted to ₹1,000.00
     *
     * @param amount - number to be converted
     * @return number in INR format
     */
    public static String convertAmountToINRFormat(Double amount) {
        Locale locale = new Locale("en", "IN");
        DecimalFormat decimalFormat = (DecimalFormat) DecimalFormat.getCurrencyInstance(locale);
        DecimalFormatSymbols dfs = DecimalFormatSymbols.getInstance(locale);
        dfs.setCurrencySymbol("\u20B9");
        decimalFormat.setDecimalFormatSymbols(dfs);
        return decimalFormat.format(amount);
    }

    /**
     * Evaluate the given mathematical expression in string format
     *
     * @param expression - string expression
     * @return
     */
    public static String evaluateExpression(String expression) {
        ScriptEngineManager mgr = new ScriptEngineManager();
        ScriptEngine engine = mgr.getEngineByName("JavaScript");
        String foo = "var res = " + expression + "; res.toFixed(2).replace(/(\\d)(?=(\\d{3})+(?!\\d))/g, '$1,')";
        String result = null;
        try {
            result = engine.eval(foo).toString();
            logger.info("Expression : " + foo + " Result : " + result);
        } catch (ScriptException e) {
            e.printStackTrace();
        }
        return result;
    }

    /**
     * Run any command in command prompt
     *
     * @param command - command to be executed in cmd
     * @return
     */
    public static String runCommand(String command) {
        String allLine = "";
        Process process = null;
        try {
            String os = System.getProperty("os.name");
            // build cmd proccess according to os
            if (os.contains("Windows")) // if windows
            {
                ProcessBuilder builder = new ProcessBuilder("cmd.exe", "/c", command);
                builder.redirectErrorStream(true);
                Thread.sleep(1000);
                process = builder.start();
            }

            // get std output
            BufferedReader buffReader = new BufferedReader(new InputStreamReader(process.getInputStream()));
            String line = "";
            while ((line = buffReader.readLine()) != null) {
                allLine = allLine + " " + line + "\n";
                if (line.contains("Console LogLevel: debug"))
                    break;
            }
        } catch (Exception exception) {
            exception.printStackTrace();
        }
        return allLine;
    }
}

