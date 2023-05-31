package com.mastercard.gpse.processing.utils;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;

public class DateUtility {
    private static final Logger logger = LogManager.getLogger(DateUtility.class);

    /**
     * Return the current local date time
     *
     * @return local date time
     */
    public static String getExecutionDate(){
        return LocalDateTime.now().toString();
    }

    /**
     * Get the current date time in string type with this "dd/MM/yy HH:mm:ss" pattern
     *
     * @return current date in string type
     */
    public static String getCurrentDateTime(){
        return LocalDateTime.now().format(DateTimeFormatter.ofPattern("dd/MM/yy HH:mm:ss"));
    }

    /**
     * Get the current date time in string type in given pattern
     *
     * @return current date in string type
     */
    public static String getCurrentDateTimeInFormat(String pattern){
        return LocalDateTime.now().format(DateTimeFormatter.ofPattern(pattern));
    }

    /**
     * Get current time stamp
     * Pattern - "yyyyMMddHHmmss"
     *
     * @return formatted current date time
     */
    public static String getTimeStamp(){
        return new SimpleDateFormat("yyyyMMddHHmmss").format(new Date());
    }

    /**
     * Parse the string date in "yyyy-MM-dd" format into instance of Local date time
     *
     * @param date - date in string format
     * @return formatted instance of LocalDate
     */
    public static LocalDate getDate(String date){
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        return  LocalDate.parse(date, formatter);
    }

    /**
     * Get the current date in string type from the current instance of local date time
     *
     * @return current date in string type
     */
    public static String getCurrentDate(){
        return getDate(LocalDate.now().toString()).toString();
    }

    /**
     * Get the date in given pattern string type after subtracting the given number of days from the current date
     *
     * @param noOfDays - no of days to be subtracted from the current date
     * @return formatted date in string type
     */
    public static String minusDaysFromCurrentDate(int noOfDays, String pattern){
        LocalDate date = LocalDate.now().minusDays(noOfDays);
        return date.format(DateTimeFormatter.ofPattern(pattern));
    }

    /**
     * Get the date in string type after subtracting the given number of days from the current date
     * Date pattern - "dd/MM/yyyy"
     *
     * @param noOfDays - no of days to be subtracted from the current date
     * @return formatted date in string type
     */
    public static String minusDaysFromCurrentDate(int noOfDays){
        LocalDate date = LocalDate.now().minusDays(noOfDays);
        return date.format(DateTimeFormatter.ofPattern("dd/MM/yyyy"));
    }

    /**
     * Get the date in string type after subtracting the given number of days from the current date time
     * Date pattern - "dd/MM/yy HH:mm:ss"
     *
     * @param noOfDays - no of days to be subtracted from the current date time
     * @return formatted date in string type
     */
    public static String minusDaysFromCurrentDateTime(int noOfDays){
        LocalDateTime dateTime = LocalDateTime.now().minusDays(noOfDays);
        return dateTime.format(DateTimeFormatter.ofPattern("dd/MM/yy HH:mm:ss"));
    }

    /**
     * Get the date in given pattern string type after adding the given number of days from the current date
     *
     * @param noOfDays - no of days to be added from the current date
     * @return formatted date in string type
     */
    public static String plusDaysFromCurrentDate(int noOfDays, String pattern){
        LocalDate date = LocalDate.now().plusDays(noOfDays);
        return date.format(DateTimeFormatter.ofPattern(pattern));
    }

    /**
     * Get the date in string type after adding the given number of days from the current date
     * Date pattern - "dd/MM/yyyy"
     *
     * @param noOfDays - no of days to be added from the current date
     * @return formatted date in string type
     */
    public static String plusDaysFromCurrentDate(int noOfDays){
        LocalDate date = LocalDate.now().plusDays(noOfDays);
        return date.format(DateTimeFormatter.ofPattern("dd/MM/yyyy"));
    }

    /**
     * Get the date in string type after adding the given number of days from the current date time
     * Date pattern - "dd/MM/yy HH:mm:ss"
     *
     * @param noOfDays - no of days to be added from the current date time
     * @return formatted date in string type
     */
    public static String plusDaysFromCurrentDateTime(int noOfDays){
        LocalDateTime dateTime = LocalDateTime.now().plusDays(noOfDays);
        return dateTime.format(DateTimeFormatter.ofPattern("dd/MM/yy HH:mm:ss"));
    }

    /**
     * Get the formatted instance if LocalDateTime after adding given number of days in the given instance of LocalDateTime
     *
     * @param actDate - given instance of LocalDateTime
     * @param days - number of days to be added
     * @return formatted instance of LocalDateTime
     */
    public static LocalDateTime plusDaysFromDateTime(LocalDateTime actDate, int days){
        LocalDateTime dateTime = LocalDateTime.from(actDate).plusDays(days);
        return  dateTime;
    }

    /**
     * Get the formatted instance if LocalDateTime after adding given number of minutes in the given instance of LocalDateTime
     *
     * @param actDate - given instance of LocalDateTime
     * @param minutes - number of minutes to be added
     * @return formatted instance of LocalDateTime
     */
    public static LocalDateTime plusMinutesFromDateTime(LocalDateTime actDate, long minutes){
        return  LocalDateTime.from(actDate).plusMinutes(minutes);
    }

    /**
     * Give the difference between given instance of LocalDateTime and current instance of LocalDateTime
     *
     * @param date - given instance of LocalDateTime
     * @return the comparator value, negative if less, positive if greater
     */
    public static int compareDates(LocalDateTime date){
        int dateTime = LocalDateTime.from(date).compareTo(LocalDateTime.now());
        return  dateTime;
    }

    /**
     * Change the format of date in string type from one given format to another given format
     *
     * @param dateValue - date in string type
     * @param fromFormat - format from
     * @param toFormat - format to
     * @return - formatted date in string type
     */
    public static String changeDateFormat(String dateValue, String fromFormat, String toFormat) {
        try {
            DateFormat dateFormatFrom = new SimpleDateFormat(fromFormat);
            DateFormat dateFormatTo = new SimpleDateFormat(toFormat);
            Date date = dateFormatFrom.parse(dateValue);
            String dateString = dateFormatTo.format(date);
            logger.info("Manipulated string date value is : " + dateString);
            return dateString;
        } catch (ParseException e) {
            logger.info("Parse Exception occured.");
            return null;
        }
    }
}
