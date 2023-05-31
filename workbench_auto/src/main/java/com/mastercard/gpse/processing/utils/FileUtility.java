package com.mastercard.gpse.processing.utils;

import com.mastercard.gpse.processing.constants.Constants;
import com.mastercard.gpse.processing.fileoperations.TestProperties;
import com.mastercard.gpse.processing.managers.AllDriverManager;
import com.mastercard.gpse.processing.pages.BasePage;
import org.apache.commons.io.FileUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;

import java.io.*;

public class FileUtility{
    private static final Logger logger = LogManager.getLogger(FileUtility.class);

    /**
     * Get file data in array of bytes using the FileInputStream
     *
     * @param file - absolute path of the file to be read
     * @return file data in array of bytes
     */
    public static byte[] getFileByteArray(File file) {
        try {
            FileInputStream fl = new FileInputStream(file);
            byte[] arr = new byte[(int) file.length()];
            fl.read(arr);
            fl.close();
            return arr;
        } catch (IOException e){
            logger.info("Error in reading file: " + e.getLocalizedMessage());
            return null;
        }
    }

    /**
     * Check if the file exists or not at the given path
     *
     * @param filePath - absolute path of the file with name and extension
     * @return - true if exists/false if not
     */
    public static boolean checkFileExist(String filePath){
        File file= new File(filePath);
        boolean fileExists= file.exists();
        if(fileExists)
            logger.info("File exists: " + filePath);
        else
            logger.info("File does not exist: " + filePath);
        return file.exists();
    }

    /**
     * Return the object of FileReader for file
     * @param filePath - absolute path of the file to be read with name and extension
     * @return FileReader
     */
    public static FileReader getFileReader(String filePath) {
        try {
            return new FileReader(filePath);
        } catch (FileNotFoundException e) {
            logger.error("File path not found: " + filePath);
            return null;
        }
    }

    /**
     * Creates the directory if it does not exists
     *
     * @param directoryName - absolute path of the directory
     */
    public static void getDir(String directoryName) {
        File directory = new File(directoryName);
        System.out.println("Directory path: "+directory);
        if (!directory.exists()) {
            directory.mkdir();
            logger.info("Directory Created: "+directory.getName());
        }
    }

    /**
     * Deletes the given file
     *
     * @param filPath - absolute path of the file to be deleted
     */
    public static void deleteFile(String filPath) {
        File file = new File(filPath);
        if (file.exists()) {
            if (file.delete()) {
                logger.info("File deleted successfully: " + filPath);
            } else {
                logger.error("Failed to delete the file: " + filPath);
            }
        } else {
            logger.info("File doesn't exist");
        }
    }

}
