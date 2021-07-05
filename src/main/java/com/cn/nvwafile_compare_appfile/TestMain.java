package com.cn.nvwafile_compare_appfile;

import com.cn.utils.HttpReqUtil;
import com.cn.utils.PropertiesGetUtil;
import com.cn.utils.TxtReaderUtil;

import java.io.File;
import java.util.*;

/**
 * @ClassName: 1
 * @Author: huangzhijuan
 * @Description: TODO
 * @Date: Create in 1 1
 * @Version 1.0
 */
public class TestMain {
    private static String androidTxtFile = null;
    private static String iosTxtFile = null;
    private static String param_url = null;
    private static String param_param = null;

    static {
//        try{

//            Properties pro = new Properties();
//            ClassLoader classLoader = TestMain.class.getClassLoader();
//            InputStream in = classLoader.getResourceAsStream("config.properties");
//            pro.load(in);

//            androidTxtFile = pro.getProperty("androidTxtFile");
//            iosTxtFile = pro.getProperty("iosTxtFile");
//            param_url = pro.getProperty("param_url");
//            param_param = pro.getProperty("param_param");


//        }catch (Exception e){
//            e.printStackTrace();
//        }

        PropertiesGetUtil propertiesGet = new PropertiesGetUtil();
        androidTxtFile = propertiesGet.getProperties("androidTxtFile");
        iosTxtFile = propertiesGet.getProperties("iosTxtFile");
        param_url = propertiesGet.getProperties("param_url");
        param_param = propertiesGet.getProperties("param_param");

    }
    public static void main(String[] args) {

            //获取android配置文件内容
            File androidFile = new File(androidTxtFile);
            String anroidActResult = TxtReaderUtil.txtToString(androidFile);
            System.out.println(anroidActResult);

            //获取ios配置文件内容
            File iosFile = new File(iosTxtFile);

            //获取中台接口返回值
//            String param_url = "https://api.meeshow.com/api/v2/base/serviceinfo/info";
//            String param_param = "use_type=1";
            String nvwaResult = HttpReqUtil.sendGet(param_url, param_param);

            //将中台接口返回值的key和url放入map集合
            Map<String, String> nvwaMap = FileCompare.putResultToMap(nvwaResult);
            //将android配置返回的key和url放入map集合
            Map<String, String> androidMap = FileCompare.putResultToMap(anroidActResult);
            System.out.println("======以下是安卓配置结果(只返回问题结果)======");
            FileCompare.compareResult(nvwaMap,androidMap);

            System.out.println();
            //将ios配置返回的key和url放入map集合
            Map<String, String> iosMap = FileCompare.putIosTpMap(iosFile);
            System.out.println("=======以下是ios配置结果(只返回问题结果)=======");
            FileCompare.compareResult(nvwaMap,iosMap);

            System.out.println();
            System.out.println("========以下是nvwa接口返回值(只返回访问不通的)=======");
            FileCompare.nvwaApiReturnValue(nvwaMap);

            System.out.println();
            System.out.println("========以下是android接口返回值(只返回访问不通的)=======");
            FileCompare.nvwaApiReturnValue(androidMap);

            System.out.println();
            System.out.println("========以下是ios接口返回值(只返回访问不通的)=======");
            FileCompare.nvwaApiReturnValue(iosMap);


    }





}
