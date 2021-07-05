package com.cn.utils;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.apache.poi.ss.usermodel.Row;


import java.io.FileInputStream;
import java.io.InputStream;


/**
 * @ClassName: ExcelUtil
 * @Author: huangzhijuan
 * @Description: TODO
 * @Date: Create in 11:24 2019.09.23
 * @Version 1.0
 */
public class ExcelUtil {
    private String fileName=null;


    /**
     *
     * @param fileName
     */
    public ExcelUtil(String fileName){
        this.fileName=fileName;
    }


    public Workbook getWb(){

        Workbook wb=null;
        try{
            InputStream input=new FileInputStream(fileName);

            if(fileName.endsWith(".xlsx")){
                wb=new XSSFWorkbook(input);
            }else{
                wb=new HSSFWorkbook(input);
            }
        }catch(Exception e){
            e.printStackTrace();
        }

        return wb;
    }
    public Sheet getSheet(int index){

        Sheet sheet=null;
        try{
            Workbook wb=getWb();

            sheet=wb.getSheetAt(index);
        }catch(Exception e){
            e.printStackTrace();
        }

        return sheet;
    }

    public Object getCellValue(int index,int rowNum,int cellnum){

        Object result=null;

        try{

            Row row=getSheet(index).getRow(rowNum);

            Cell cell=row.getCell(cellnum);

            result=fromCellTypeGetCellValue(cell);

        }catch(Exception e){
            e.printStackTrace();
        }

        return result;
    }

    /**
     *
     * @Title: fromCellTypeGetCellValue
     * @Description: TODO
     * @param: @param cell
     * @param: @return
     * @return: Object
     * @throws
     */
    public Object fromCellTypeGetCellValue(Cell cell){

        Object value=null;
        try{

            if(cell.getCellType()== Cell.CELL_TYPE_BLANK){
                value="";
            }else if(cell.getCellType()==Cell.CELL_TYPE_NUMERIC){
                value=cell.getNumericCellValue();
            }else if(cell.getCellType()==Cell.CELL_TYPE_STRING){
                value=cell.getStringCellValue();
            }else if(cell.getCellType()==Cell.CELL_TYPE_FORMULA){
                value=cell.getCellFormula();
            }else if(cell.getCellType()==Cell.CELL_TYPE_BOOLEAN){
                value=cell.getBooleanCellValue();
            }else if(cell.getCellType()==Cell.CELL_TYPE_ERROR){
                value=cell.getErrorCellValue();
            }else{
                value=cell.getDateCellValue();
            }
        }catch(Exception e){
            e.printStackTrace();
        }
        return value;
    }
    /**
     *
     * @Title: getArrayCellValue
     * @Description: TODO
     * @param: @param sheetIndex
     * @param: @return
     * @return: Object[][]
     * @throws
     */
    public Object[][] getArrayCellValue(int sheetIndex){

        Object[][] testCaseData=null;
        try{

            int totalRowIndex=getSheet(sheetIndex).getLastRowNum();
            testCaseData=new Object[totalRowIndex][3];
            for(int rowIndex=1;rowIndex<=totalRowIndex;rowIndex++){

                Row row=getSheet(sheetIndex).getRow(rowIndex);

                if(row==null){
                    continue;
                }

                for(int cellIndex=0;cellIndex<row.getLastCellNum();cellIndex++){
                    Cell cell=row.getCell(cellIndex);
                    if(cell==null){
                        testCaseData[rowIndex-1][cellIndex]="";
                    }else{
                        testCaseData[rowIndex-1][cellIndex]=fromCellTypeGetCellValue(cell);
                    }
                }
            }
        }catch(Exception e){
            e.printStackTrace();
        }
        return testCaseData;
    }
    /**
     *
     * @Title: main
     * @Description: TODO
     * @param: @param args
     * @return: void
     * @throws
     */
    public static void main(String[] args) {

    }

}
