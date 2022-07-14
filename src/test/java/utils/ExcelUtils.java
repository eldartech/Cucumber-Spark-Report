package utils;

import org.apache.poi.ss.usermodel.*;
import org.apache.poi.ss.util.NumberToTextConverter;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class ExcelUtils {
    private static Workbook workbook;
    private static Sheet sheet;
    private static Row row;
    private static Cell cell;
    private static String filePath;
    public static Sheet getSheet() {
        return sheet;
    }
    public static void setSheet(Sheet sheet) {
        ExcelUtils.sheet = sheet;
    }
    public static Row getRow() {
        return row;
    }
    public static void setRow(Row row) {
        ExcelUtils.row = row;
    }
    public static Cell getCell() {
        return cell;
    }
    public static void setCell(Cell cell) {
        ExcelUtils.cell = cell;
    }
    /**
     * Method will open Excel file with provided excel file name and sheet name
     *
     * @param fileName
     * @param sheetName
     * @throws IOException
     */
    public static void openExcel(String fileName, String sheetName) {
        filePath = "src\\test\\resources\\data\\" + fileName + ".xlsx";
        File file = new File(filePath);
        FileInputStream inputStream = null;
        try {
            inputStream = new FileInputStream(file);
            workbook = new XSSFWorkbook(inputStream);
            sheet = workbook.getSheet(sheetName);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    /**
     * Method will open Excel file with provided excel file name and sheet name
     *
     */
    public static void openExcel(String absolutePath,int sheetIndex) {
        filePath = absolutePath;
        File file = new File(filePath);
        FileInputStream inputStream = null;
        try {
            inputStream = new FileInputStream(file);
            workbook = new XSSFWorkbook(inputStream);
            sheet = workbook.getSheetAt(sheetIndex);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    /**
     * Method will provide the value on specified row number and cell number.
     *
     * @param rowNumber
     * @param cellNumber
     * @return
     */
    public static String getValue(int rowNumber, int cellNumber) {
        return sheet.getRow(rowNumber).getCell(cellNumber).toString();
    }
    /**
     * Method will set a new value.
     *
     * @param value
     * @param rowNumber
     * @param cellNumber
     * @throws IOException
     */
    public static void setValue(String value, int rowNumber, int cellNumber) {
        try {
            row = sheet.getRow(rowNumber);
            cell = row.getCell(cellNumber);
            cell.setCellValue(value);
        } catch (NullPointerException e) {
            cell = sheet.createRow(rowNumber).createCell(cellNumber);
            cell.setCellValue(value);
        }
        FileOutputStream fileOutputStream = null;
        try {
            fileOutputStream = new FileOutputStream(new File(filePath));
            workbook.write(fileOutputStream);
            fileOutputStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public static void getAllExcelValue() {
        int rowNumber = sheet.getLastRowNum() - sheet.getFirstRowNum();
        for (int i = sheet.getFirstRowNum(); i < rowNumber; i++) {
            Row row = sheet.getRow(i);
            for (int q = row.getFirstCellNum(); q < row.getLastCellNum(); q++) {
                Cell cell = row.getCell(q);
                System.out.print(cell + "|");
            }
            System.out.println();
        }
    }
    /**
     * Method will return number of cells on specified row.
     *
     * @return
     */
    public static int getNumberOfCells(int rowNumber) {
        try {
            return sheet.getRow(rowNumber).getPhysicalNumberOfCells();
        } catch (NullPointerException e) {
            System.out.println(e.getMessage());
            return -1;
        }
    }
    public List<Map<String, String>> getData(String excelFilePath, String sheetName) {
        sheet = getSheetByName(excelFilePath, sheetName);
        return readSheet(sheet);
    }
    public List<Map<String, String>> getData(String excelFilePath, int sheetNumber) {
        sheet = getSheetByIndex(excelFilePath, sheetNumber);
        return readSheet(sheet);
    }
    private Sheet getSheetByName(String excelFilePath, String sheetName){
        sheet = getWorkBook(excelFilePath).getSheet(sheetName);
        return sheet;
    }
    private Sheet getSheetByIndex(String excelFilePath, int sheetNumber){
        sheet = getWorkBook(excelFilePath).getSheetAt(sheetNumber);
        return sheet;
    }
    private Workbook getWorkBook(String excelFilePath){
        workbook=null;
        try {
            workbook= WorkbookFactory.create(new File(excelFilePath));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return workbook;
    }
    private List<Map<String, String>> readSheet(Sheet sheet) {
        int totalRow = sheet.getPhysicalNumberOfRows();
        List<Map<String, String>> excelRows = new ArrayList<Map<String, String>>();
        int headerRowNumber = getHeaderRowNumber(sheet);
        if (headerRowNumber != -1) {
            int totalColumn = sheet.getRow(headerRowNumber).getLastCellNum();
            int setCurrentRow = 1;
            for (int currentRow = setCurrentRow; currentRow <= totalRow; currentRow++) {
                row = getRow(sheet, sheet.getFirstRowNum() + currentRow);
                LinkedHashMap<String, String> columnMapdata = new LinkedHashMap<String, String>();
                for (int currentColumn = 0; currentColumn < totalColumn; currentColumn++) {
                    columnMapdata.putAll(getCellValue(sheet, row, currentColumn));
                }
                excelRows.add(columnMapdata);
            }
        }
        return excelRows;
    }
    private int getHeaderRowNumber(Sheet sheet) {
        int totalRow = sheet.getLastRowNum();
        for (int currentRow = 0; currentRow <= totalRow + 1; currentRow++) {
            row = getRow(sheet, currentRow);
            if (row != null) {
                int totalColumn = row.getLastCellNum();
                for (int currentColumn = 0; currentColumn < totalColumn; currentColumn++) {
                    Cell cell;
                    cell = row.getCell(currentColumn, Row.MissingCellPolicy.CREATE_NULL_AS_BLANK);
                    if (cell.getCellType() == CellType.STRING) {
                        return row.getRowNum();
                    } else if (cell.getCellType() == CellType.NUMERIC) {
                        return row.getRowNum();
                    } else if (cell.getCellType() == CellType.BOOLEAN) {
                        return row.getRowNum();
                    } else if (cell.getCellType() == CellType.ERROR) {
                        return row.getRowNum();
                    }
                }
            }
        }
        return (-1);
    }
    private Row getRow(Sheet sheet, int rowNumber) {
        return sheet.getRow(rowNumber);
    }
    private LinkedHashMap<String, String> getCellValue(Sheet sheet, Row row, int currentColumn) {
        LinkedHashMap<String, String> columnMapdata = new LinkedHashMap<String, String>();
        if (row == null) {
            if (sheet.getRow(sheet.getFirstRowNum()).getCell(currentColumn, Row.MissingCellPolicy.CREATE_NULL_AS_BLANK)
                    .getCellType() != CellType.BLANK) {
                String columnHeaderName = sheet.getRow(sheet.getFirstRowNum()).getCell(currentColumn)
                        .getStringCellValue();
                columnMapdata.put(columnHeaderName, "");
            }
        } else {
            cell = row.getCell(currentColumn, Row.MissingCellPolicy.CREATE_NULL_AS_BLANK);
            if (cell.getCellType() == CellType.STRING) {
                if (sheet.getRow(sheet.getFirstRowNum())
                        .getCell(cell.getColumnIndex(), Row.MissingCellPolicy.CREATE_NULL_AS_BLANK)
                        .getCellType() != CellType.BLANK) {
                    String columnHeaderName = sheet.getRow(sheet.getFirstRowNum()).getCell(cell.getColumnIndex())
                            .getStringCellValue();
                    columnMapdata.put(columnHeaderName, cell.getStringCellValue());
                }
            } else if (cell.getCellType() == CellType.NUMERIC) {
                if (sheet.getRow(sheet.getFirstRowNum())
                        .getCell(cell.getColumnIndex(), Row.MissingCellPolicy.CREATE_NULL_AS_BLANK)
                        .getCellType() != CellType.BLANK) {
                    String columnHeaderName = sheet.getRow(sheet.getFirstRowNum()).getCell(cell.getColumnIndex())
                            .getStringCellValue();
                    columnMapdata.put(columnHeaderName, NumberToTextConverter.toText(cell.getNumericCellValue()));
                }
            } else if (cell.getCellType() == CellType.BLANK) {
                if (sheet.getRow(sheet.getFirstRowNum())
                        .getCell(cell.getColumnIndex(), Row.MissingCellPolicy.CREATE_NULL_AS_BLANK)
                        .getCellType() != CellType.BLANK) {
                    String columnHeaderName = sheet.getRow(sheet.getFirstRowNum()).getCell(cell.getColumnIndex())
                            .getStringCellValue();
                    columnMapdata.put(columnHeaderName, "");
                }
            } else if (cell.getCellType() == CellType.BOOLEAN) {
                if (sheet.getRow(sheet.getFirstRowNum())
                        .getCell(cell.getColumnIndex(), Row.MissingCellPolicy.CREATE_NULL_AS_BLANK)
                        .getCellType() != CellType.BLANK) {
                    String columnHeaderName = sheet.getRow(sheet.getFirstRowNum()).getCell(cell.getColumnIndex())
                            .getStringCellValue();
                    columnMapdata.put(columnHeaderName, Boolean.toString(cell.getBooleanCellValue()));
                }
            } else if (cell.getCellType() == CellType.ERROR) {
                if (sheet.getRow(sheet.getFirstRowNum())
                        .getCell(cell.getColumnIndex(), Row.MissingCellPolicy.CREATE_NULL_AS_BLANK)
                        .getCellType() != CellType.BLANK) {
                    String columnHeaderName = sheet.getRow(sheet.getFirstRowNum()).getCell(cell.getColumnIndex())
                            .getStringCellValue();
                    columnMapdata.put(columnHeaderName, Byte.toString(cell.getErrorCellValue()));
                }
            }
        }
        return columnMapdata;
    }
    public static void closeWorkbook() {
        try {
            workbook.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
