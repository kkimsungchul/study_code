package com.sungchul.excel;

import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.HashMap;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;


public class ExcelWrite {

    public static void main(String[] args) {
        try{

            ArrayList<HashMap<String, String>> dataList = new ArrayList<>();
            for(int i=0;i<100;i++){
                HashMap<String, String> data = new HashMap<String, String>();
                data.put("Key1", "Value111"+ " " + i);
                data.put("Key2", "Value222"+ " " + i);
                data.put("Key3", "Value433"+ " " + i);
                data.put("Key4", "Value544"+ " " + i);
                data.put("Key5", "Value655"+ " " + i);
                data.put("Key6", "Value766"+ " " + i);
                data.put("Key7", "Value877"+ " " + i);

                data.put("Key8", "Value988"+ " " + i);
                dataList.add(data);
            }



            String excelFilePath = "data.xlsx";
            Workbook workbook = new XSSFWorkbook();
            Sheet sheet = workbook.createSheet("Data");

            int rowNum = 0;
            int topCellIndex=0;
            Row topRow = sheet.createRow(rowNum);
            for (String key : dataList.get(1).keySet()) {


                Cell keyCell = topRow.createCell(topCellIndex++);
                keyCell.setCellValue(key);

            }
            rowNum++;
            for( HashMap<String, String> data: dataList){
                Row row = sheet.createRow(rowNum++);
                int cellIndex=0;
                for (String key : data.keySet()) {
                    //Cell keyCell = row.createCell(cellIndex++);
                    //keyCell.setCellValue(key);
                    Cell valueCell = row.createCell(cellIndex++);
                    valueCell.setCellValue(data.get(key));
                }
            }


            FileOutputStream outputStream = new FileOutputStream(excelFilePath);
            workbook.write(outputStream);
            workbook.close();
        }catch (Exception e){
            e.printStackTrace();
        }

    }

}