package steps.javaTest;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.*;

public class TestExcel {
    public static void main(String[] args) throws IOException {
        String path = "/Users/techtorialacademy/Desktop/contacts.xlsx";
        File excelFile=new File(path);
        FileInputStream inputStream = new FileInputStream(excelFile);
        Workbook workbook = new XSSFWorkbook(inputStream);
        Sheet sheet=workbook.getSheet("employees");
        Row row=sheet.getRow(1);
        Cell cell = row.getCell(1);
       // System.out.println(sheet.getRow(0).getCell(1));
        int rowCount = row.getLastCellNum();
       // System.out.println(rowCount);
        int lastRowNum = sheet.getLastRowNum();
       // System.out.println(lastRowNum);

        row=sheet.createRow(4);
        cell=row.createCell(0);
        cell.setCellValue("Pit");
        row.createCell(1).setCellValue(423);
        workbook.createSheet("accounting");
        sheet=workbook.getSheet("accounting");
        sheet.createRow(0).createCell(0).setCellValue("Hello");

        FileOutputStream fileOutputStream = new FileOutputStream(excelFile);
        workbook.write(fileOutputStream);

        for (int r=0;r<=sheet.getLastRowNum();r++){
            Row row1=sheet.getRow(r);
            for (int c=0; c<sheet.getRow(r).getLastCellNum();c++){
                Cell cell1=row1.getCell(c);
                System.out.print(cell1+" | ");
            }
            System.out.println();
        }


    }
}
