package com.miko.genericUtils;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
//import org.apache.poi.xssf.usermodel.XSSFWorkbook;

/**
 * This class used to read & write the data from property file
 * @author Rakesh
 */
public class ExcelFileUtil {

	 /**
     * Use this method to read data from excel sheet and file path are taken from FilePaths interface
     *
     * @param sheetName
     * @param rowNo
     * @param cellNo
     * @return Excel cell value in string
     */
    public static String getExcelData(String sheetName, int rowNo, int cellNo) {
        try {
            FileInputStream file = new FileInputStream(IPathConstant.ExcelFilePath);
            Workbook workbook = WorkbookFactory.create(file);
            return workbook.getSheet(sheetName).getRow(rowNo).getCell(cellNo).getStringCellValue();
        } catch (Exception e) {
            System.out.println("File not found");
            e.printStackTrace();
        }

        return null;
    }
    /**
     * Use this method to read data from excel sheet and file path are taken from FilePaths interface
     *
     * @param sheetName
     * @param rowNo
     * @param cellNo
     * @return Excel cell value in string
     */
    public static String getExcelData(String FilePath,String sheetName, int rowNo, int cellNo) {
        try {
            FileInputStream file = new FileInputStream(FilePath);
            Workbook workbook = WorkbookFactory.create(file);
            return workbook.getSheet(sheetName).getRow(rowNo).getCell(cellNo).getStringCellValue();
        } catch (Exception e) {
            System.out.println("File not found");
            e.printStackTrace();
        }

        return null;
    }

    /**
     * Method read data based on testcase id and the column name/test data cell
     *
     * @param sheetName
     * @param testcaseID
     * @param columnName
     * @return Cell String value
     */
    public static String getExcelData(String sheetName, String testcaseID, String columnName) {
        
        try {
            FileInputStream file = new FileInputStream(IPathConstant.ExcelFilePath);
            Workbook workbook = WorkbookFactory.create(file);
            Sheet sheet = workbook.getSheet(sheetName);
            int lastRow = sheet.getLastRowNum();
            int testRow = 0;
            for (int i = 0; i <= lastRow; i++) {
                try {
                    String testcaseNum = sheet.getRow(i).getCell(0).getStringCellValue();
                    if (testcaseNum.equalsIgnoreCase(testcaseID)) {
                        testRow = i;
                        break;
                    }
                } catch (NullPointerException e) {

                }
            }
            int lastCell = sheet.getRow(testRow - 1).getLastCellNum();
            int testcell = 0;
            for (int i = 0; i <= lastCell; i++) {
                try {
                    String cellData = sheet.getRow(testRow - 1).getCell(i).getStringCellValue();
                    if (cellData.equalsIgnoreCase(columnName)) {
                        testcell = i;
                        break;
                    }
                } catch (NullPointerException e) {
                }
            }
            return getExcelData(sheetName, testRow, testcell);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return "No Data found";
    }
    

    /**
     * Method used to write data into excel sheet
     *
     * @param sheetName
     * @param rowNum
     * @param cellNum
     * @param cellValue
     */
    public void writeDataToExcel(String sheetName, int rowNum, int cellNum, String cellValue) {
       
        try {
            FileInputStream file = new FileInputStream(IPathConstant.ExcelFilePath);
            Workbook workbook = WorkbookFactory.create(file);
            Sheet sheet = workbook.getSheet(sheetName);
            sheet.createRow(rowNum).createCell(cellNum).setCellValue(cellValue);
            FileOutputStream fileOut = new FileOutputStream(IPathConstant.ExcelFilePath);
            workbook.write(fileOut);
        } catch (Throwable e) {
            e.printStackTrace();
        }
    }
}
