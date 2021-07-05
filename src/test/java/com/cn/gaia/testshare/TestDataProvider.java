package com.cn.gaia.testshare;

import org.testng.annotations.DataProvider;

/**
 * @ClassName: TestDataProvider
 * @Author: huangzhijuan
 * @Description: TODO
 * @Date: Create in 11:00 2019.09.24
 * @Version 1.0
 */
public class TestDataProvider {

    @DataProvider
    public static Object[][] dataMethodNoName(){
        return new Object[][]{
                {"data with no name 1"},
                {"data with no name 2"},
                {"data with no name 3"}
        };
    }

    @DataProvider(name = "dataMethodWithName",parallel = true)
    public static Object[][] dataMethodWithName(){
        return new Object[][]{
                {"data with name 1"},
                {"data with name 2"},
                {"data with name 3"}
        };
    }

    /*
    @DataProvider(name = "testExcelUtil")
    public static Object[][] testExcelUtil(){
        ExcelUtil excelUtil = new ExcelUtil(filePath);
        Object[][] data = excelUtil.getArrayCellValue(0);
        return data;
    }

     */
}
