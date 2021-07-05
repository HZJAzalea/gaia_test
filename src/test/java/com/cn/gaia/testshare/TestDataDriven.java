package com.cn.gaia.testshare;

import com.cn.utils.ExcelUtil;
import org.testng.annotations.*;

/**
 * @ClassName: TestDataDriven
 * @Author: huangzhijuan
 * @Description: TODO
 * @Date: Create in 10:46 2019.09.24
 * @Version 1.0
 */
public class TestDataDriven {


    private static String filePath = null;

    @Parameters({"filePathName"})
    @BeforeTest
    public void beforeTest(String fromTestNgXMLParam){
        this.filePath = fromTestNgXMLParam;
    }



    /*
    @Test(dataProvider = "testData")
    public void testShare(int a,int b){
        System.out.println("a+b=" + (a + b));
        //System.out.println("@test");
    }

    @DataProvider(name = "testData" )
    public Object[][] dp(){
        //System.out.println("@dataProvider");
        return new Object[][]{
                {1,2},
                {3,4},
                {4,5}
        };

    }

     */

    /*
    @Test(dataProvider = "dataMethodWithName",dataProviderClass = TestDataProvider.class)
    public void testDataDrivenDemo(String data){
        System.out.println("this is : " + data);
    }

     */


    /*
    @Test(dataProviderClass = TestDataProvider.class,dataProvider = "testExcelUtil")
    public void testExcelUtilDemo(String a,String b,String c){
        System.out.println("a = " + a + "," + "b = " + b + "," + "c = " + c);
    }
    */


    @Test(dataProvider = "testExcelUtil")
    public void testExcelUtilDemo(String a,String b,String sum){
        System.out.println("a = " + a + "," + "b = " + b + "," + "sum = " + sum);
    }

    @DataProvider(name = "testExcelUtil")
    public static Object[][] testExcelUtil(){
        //ExcelUtil excelUtil = new ExcelUtil("/Users/huangzhijuan/Documents/testcase/testcase.xlsx");
        ExcelUtil excelUtil = new ExcelUtil(filePath);
        Object[][] data = excelUtil.getArrayCellValue(0);
        return data;
    }





    /*
    @BeforeSuite
    public void beforeSuite(){
        System.out.println("@beforeSuite");
    }
    @BeforeClass
    public void beforeClass(){
        System.out.println("@beforeClass");
    }
    @BeforeTest
    public void beforeTest(){
        System.out.println("@beforeTest");
    }
    @BeforeMethod
    public void beforeMethod(){
        System.out.println("@beforeMethod");
    }

    @AfterSuite
    public void afterSuite(){
        System.out.println("@afterSuite");
    }
    @AfterClass
    public void afterClass(){
        System.out.println("@afterClass");
    }
    @AfterTest
    public void afterTest(){
        System.out.println("@afterTest");
    }
    @AfterMethod
    public void afterMethod(){
        System.out.println("@afterMethod");
    }

     */
}
