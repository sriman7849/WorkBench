package com.mastercard.gpse.processing.utils;

import com.mastercard.gpse.processing.constants.Constants;
import com.mastercard.gpse.processing.fileoperations.TestProperties;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class ExcelUtils {

    private static final Logger logger = LogManager.getLogger(ExcelUtils.class);
    HashMap<String, HashMap<String, String>> entireTestData = new HashMap<String, HashMap<String, String>>();
    public File file;

    public String dataFilePath = TestProperties.getInstance().getEnvProperty("wb.data");

    /**
     * Method to read Test Data.xls
     *
     * @author e066045
     * @name Kamlesh
     *
     */
    public HashMap<String, HashMap<String, String>> readTestData(String fileName,
            String strSheetName) {

        logger.info("FileName Passed:"+fileName);
        logger.info("SheetName Passed:"+strSheetName);

        HashMap<String, String> singleStoryTestData;
        List<String> strHeaders = new ArrayList<String>();
        file = getDataFile(fileName);
        logger.info("Filepath: "+file.getAbsolutePath());
        logger.info("File Access: "+file.getName());
        try (FileInputStream inputStream = new FileInputStream(file);
             XSSFWorkbook excelWB = new XSSFWorkbook(inputStream)) {

            Sheet excelSheet = excelWB.getSheet(strSheetName);
            logger.info("Sheet Name Accessed: "+excelSheet.getSheetName());
            int rowCount = excelSheet.getLastRowNum();

            Row headerRow = excelSheet.getRow(0);
            for (int j = 0; j < headerRow.getLastCellNum(); j++) {
                strHeaders.add(j, headerRow.getCell(j).getStringCellValue());
            }
            for (int i = 1; i <= rowCount; i++) {
                singleStoryTestData = new HashMap<>();
                Row row = excelSheet.getRow(i);
                for (int j = 0; j < row.getLastCellNum(); j++) {
                    row.getCell(j).setCellType(Cell.CELL_TYPE_STRING);
                    if (!(row.getCell(j) == null || row.getCell(j)
                            .getStringCellValue().equals("")))
                        singleStoryTestData.put(strHeaders.get(j),
                                row.getCell(j).getStringCellValue());
                }
                HashMap<String, String> tempMap = singleStoryTestData;
                entireTestData.put(row.getCell(0).getStringCellValue(), tempMap);
            }
        } catch (FileNotFoundException e) {
            logger.error("FileNotFoundException" + e);
            return null;
        } catch (IOException e) {
            logger.error("IOException is" + e);
            return null;
        } catch (Exception e) {
            logger.error("Exception.." + e);
            e.printStackTrace();
            return null;
        }
        return entireTestData;
    }

    public File getDataFile(String fileName) {
        try {
            String filePath = Constants.USER_DIR + Constants.PATH_SEPARATOR
                    + Constants.CONFIG_DIR_PATH + Constants.PATH_SEPARATOR
                    + Constants.RUNTIME_ENVIRONMENT+ Constants.PATH_SEPARATOR
                    + dataFilePath + Constants.PATH_SEPARATOR
                    + "regression" + Constants.PATH_SEPARATOR
                    + fileName;
            file = new File(filePath);

            //logger.info("File Path Variable Value -------------" + filePath);
            //logger.info("File Name to be read-------------" + fileName);
        }
        catch (Exception e) {
            logger.error("Exception" + e + e.getMessage());
            return null;
        }
        return file;
    }
}
