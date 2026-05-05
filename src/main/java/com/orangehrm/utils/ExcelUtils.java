package com.orangehrm.utils;

import java.io.FileInputStream;
import java.io.IOException;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ExcelUtils {

    private Workbook workbook;
    private Sheet sheet;

    public ExcelUtils(String filePath, String sheetName) throws IOException {
        FileInputStream fis = new FileInputStream(filePath);
        workbook = new XSSFWorkbook(fis);
        sheet = workbook.getSheet(sheetName);

        // Code to initialize workbook and sheet using Apache POI
    }

    public int getRowCount() {
        return sheet.getLastRowNum(); // Return total number of rows
    }

    public int getColumnCount() {
        return sheet.getRow(0).getLastCellNum(); // Return total number of columns
    }

    public String getCellData(int rowNum, int colNum){
        Row row = sheet.getRow(rowNum);
        if(row == null) return "";

        Cell cell = row.getCell(colNum);
        if(cell == null) return "";

        DataFormatter formatter = new DataFormatter();
        return formatter.formatCellValue(cell).trim(); // Return cell data as String
    }

    public void close() throws IOException {
        workbook.close(); // Close the workbook to free resources
    }
    
}
