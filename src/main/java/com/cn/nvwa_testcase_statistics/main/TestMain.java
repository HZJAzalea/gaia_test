package com.cn.nvwa_testcase_statistics.main;

import com.cn.nvwa_testcase_statistics.dao.ExcelDao;
import com.cn.nvwa_testcase_statistics.entity.ExcelInfo;
import com.cn.nvwa_testcase_statistics.entity.MysqlToExcel;
import com.cn.nvwa_testcase_statistics.entity.MysqlToExcelTotal;
import com.cn.nvwa_testcase_statistics.util.ExcelReaderUtil;

import com.cn.nvwa_testcase_statistics.util.ExcelWriterUtil;

import java.util.List;

/**
 * @ClassName: 1
 * @Author: huangzhijuan
 * @Description: TODO
 * @Date: Create in 1 1
 * @Version 1.0
 */
public class TestMain {
    public static void main(String[] args) {


        //String sheetIndex= "1,2";
        //写入mysql的文件路径
        String excelFilePathAndIndex = "/Users/huangzhijuan/Documents/测试工作/接口测试/1.中台/finance金融/提现服务接口-石基宏/提现服务logic层重构20201126-贾若/提现服务logic层重构.xlsx";
//        String excelFilePathAndIndex = "/Users/huangzhijuan/Documents/测试工作/接口测试/1.中台/业务监控/业务监控测试用例的副本.xlsx";
        //String excelFilePath = "/Users/huangzhijuan/Desktop/抢麦优化后-测试用例.xlsx";
        //String excelFilePath = args[0];

        //写入excel的文件路径
        String exportFilePath = null;
        String excelFilePath = excelFilePathAndIndex.split(" ")[0];
        if(excelFilePath.endsWith(".xlsx")){
            exportFilePath = excelFilePath.replace(".xlsx","-bug率统计.xlsx");
        }else if(excelFilePath.endsWith("xls")){
            exportFilePath = excelFilePath.replace(".xls","-bug率统计.xls");
        }
        //excel数据入mysql
        //读取Excel文件内容
        List<ExcelInfo> excelInfoList = ExcelReaderUtil.readExcel(excelFilePathAndIndex);
        ExcelDao excelDao = new ExcelDao();
        excelDao.saveExcelInfo(excelInfoList);


        //mysql入excel
        List<MysqlToExcel> mysqlToExcelList = ExcelDao.dbcpQuery();
        List<MysqlToExcelTotal> mysqlToExcelTotalList = ExcelDao.dbcpQueryTotal();
        ExcelWriterUtil.excelWriter(exportFilePath,mysqlToExcelList,mysqlToExcelTotalList);




//        List<List<T>> excelInfoEntityList = ExcelReaderUtil2.readExcel(excelFilePath);
//        //ExcelDao excelDao = new ExcelDao();
//        excelDao.saveExcelInfo2(excelInfoEntityList);

    }
}
