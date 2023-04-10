package com.sungchul.excel;
import java.io.FileInputStream;
import java.io.IOException;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ExcelReader {

    public static void main(String[] args) throws IOException {
        String excelFilePath = "data.xlsx";
        FileInputStream inputStream = new FileInputStream(excelFilePath);
        Workbook workbook = new XSSFWorkbook(inputStream);

        Sheet sheet = workbook.getSheetAt(0);
        for (Row row : sheet) {
            for (Cell cell : row) {
                switch (cell.getCellType()) {
                    case STRING:
//                        if(cell.getStringCellValue().indexOf("222")>0 || cell.getStringCellValue().indexOf("988")>0)  {
//                            System.out.print(cell.getStringCellValue() + "\t");
//                        }
                        System.out.print(cell.getStringCellValue() + "\t");
                        break;
                    case NUMERIC:
                        System.out.print(cell.getNumericCellValue() + "\t");
                        break;
                    case BOOLEAN:
                        System.out.print(cell.getBooleanCellValue() + "\t");
                        break;
                    default:
                        System.out.print("\t");
                }
            }
            System.out.println();
        }

        workbook.close();
        inputStream.close();
    }

}