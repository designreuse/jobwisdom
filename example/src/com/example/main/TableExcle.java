package com.example.main;

import java.io.File;
import java.io.FileInputStream;
import java.util.Iterator;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class TableExcle {

    public static void main(String[] args) throws Exception {
        File file = new File("F:\\input.xlsx");
        FileInputStream fis = new FileInputStream(file);
        Workbook wb = null;
        boolean isE2007 = false;
        if (file.getName().endsWith("xlsx")) {
            isE2007 = true;
        }
        if (isE2007) {
            wb = new XSSFWorkbook(fis);
        } 
        else {
            wb = new HSSFWorkbook(fis);
        }
        Sheet sheet = wb.getSheetAt(0);
        Iterator<Row> rows = sheet.rowIterator();
        for (int rowNum = 1; rowNum <= sheet.getLastRowNum(); rowNum++) {
            Row xssfRow = sheet.getRow(rowNum);
            for (int cellNum = 0; cellNum <= xssfRow.getLastCellNum(); cellNum++) {
                String value = xssfRow.getCell(cellNum).getStringCellValue();
                System.out.println(value);
            }
        }
    }
    
}
