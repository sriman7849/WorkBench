package com.mastercard.gpse.processing.utils;

import com.mastercard.gpse.processing.managers.AllDriverManager;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.core.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.*;

public class Table extends AllDriverManager {

    private final static Logger logger = (Logger) LogManager.getLogger(Table.class);
    private final WebElement element;
    private WebDriver driver = getDriver();

    public Table(WebElement element) {
        this.element = element;
    }

    /**
     * Get column names from a table
     *
     * @return - names of the column in String[]
     */
    public List<String> getColumnNamesFromTable() {
        List<WebElement> columns = element.findElements(By.xpath(".//div[contains(@class,'slick-header-column') and @title]"));
        List<String> columnNames = new ArrayList<String>();
        columns.forEach((header) -> columnNames.add(header.getAttribute("title")));
        return columnNames;
    }

    /**
     * Stores the data of the given row in the Map object
     *
     * @param rowNumber - given row number
     * @return row data in Map object
     */
    public Map<String, String> getRowData(int rowNumber) {
        Map<String, String> rowData = new HashMap<String, String>();
        WebElement row = element.findElements(By.xpath(".//div[contains(@class,'slick-row')]")).get(rowNumber - 1);
        List<WebElement> rowCells = row.findElements(By.xpath(".//span[@class='cell-content-inspect']"));
        for (WebElement rowCell : rowCells) {
            String title = rowCell.getAttribute("data-title");
            String data = rowCell.findElement(By.xpath(".//preceding-sibling::span")).getText();
            rowData.put(title, data);
        }
        return rowData;
    }

    /**
     * Stores the data of the given row in the Map object
     *
     * @return
     */
    public List<HashMap<String, String>> getTableData() {
        List<HashMap<String, String>> tableData = new ArrayList<HashMap<String, String>>();
        Map<String, String> rowData = new HashMap<String, String>();
        List<WebElement> rows = element.findElements(By.xpath(".//div[contains(@class,'slick-row')]"));
        for (WebElement row: rows) {
            List<WebElement> rowCells = row.findElements(By.xpath(".//span[@class='cell-content-inspect']"));
            for (WebElement rowCell : rowCells) {
                String title = rowCell.getAttribute("data-title");
                String data=new WebElementUtility().getElementText(rowCell.findElement(By.xpath(".//preceding-sibling::span")));
                rowData.put(title, data);
            }
            tableData.add((HashMap<String, String>) rowData);
        }
        return tableData;
    }

    /**
     * Read the web table and check if the given row data is present in the web table or not
     *
     * @param rowCellData - given row data
     * @return true if given row data is present in the web table else false
     */
    public boolean isRecordPresent(Map<String,String> rowCellData) {
        List<HashMap<String, String>> tableData = this.getTableData();
        for (HashMap<String, String> rowData : tableData){
            if (rowData.entrySet().containsAll((Collection<?>) rowCellData))
                return true;
        }
        return false;
    }

    /**
     * Clicks on row by row number
     *
     * @param rowNum
     */
    public void clickOnRow(int rowNum) {
        try {
            WebElement row = element.findElement(By.xpath(".//div[contains(@class,'slick-row')][" + rowNum + "]"));
            row.click();
        } catch (NoSuchElementException e) {
        }
    }

    /**
     * Get row count of the given table
     *
     * @return row count
     */
    public int getRowCount() {
        List<WebElement> rows = element.findElements(By.xpath("//div[contains(@class,'slick-row')]"));
        return rows.size();
    }
}
