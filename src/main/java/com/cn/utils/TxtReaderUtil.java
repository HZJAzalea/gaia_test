package com.cn.utils;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;

/**
 * @ClassName: 1
 * @Author: huangzhijuan
 * @Description: TODO
 * @Date: Create in 1 1
 * @Version 1.0
 */
public class TxtReaderUtil {
    public static String txtToString(File file){
        String result = "";
        try{
            BufferedReader bufferedReader = new BufferedReader(new FileReader(file));
            String s = null;
            //使用readline方法一次读取一行
            while ((s = bufferedReader.readLine()) != null){
                result = result + "\n" + s;
            }
            bufferedReader.close();
        }catch(Exception e){
            e.printStackTrace();
        }

        return result;
    }
}

