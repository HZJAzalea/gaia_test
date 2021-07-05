package com.cn.test.erercise;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import org.testng.Assert;
import com.alibaba.fastjson.JSONPath;

/**
 * @ClassName: PatternUtil
 * @Author: huangzhijuan
 * @Description: TODO
 * @Date: Create in 16:01 2019.10.29
 * @Version 1.0
 */
public class PatternUtil {
    private static String compareResultRegex = "([\\$\\.\\w\\[\\]]+)=([\\w\\:\\/\\.\\?\\=]+)";
    public static Matcher getMatcher(String regex, String data){

        Pattern pattern = null;
        Matcher matcher = null;

        try{
            pattern = Pattern.compile(regex);
            matcher = pattern.matcher(data);
        }catch(Exception e){
            e.printStackTrace();
        }
        return matcher;
    }


    public static void compareResultToReportng(String expResult,String actResult){

        Matcher matcher = getMatcher(compareResultRegex,expResult);
        while (matcher.find()){
            Assert.assertEquals(JSONPath.read(actResult, matcher.group(1)).toString(), matcher.group(2));
        }
    }

    public static int compareResultToDb(String expResult,String actResult){


        int flag = 0;
        int count = 0;
        List<Integer> list = new ArrayList<Integer>();

        Matcher matcher = getMatcher(compareResultRegex,expResult);

        while (matcher.find()){
            flag = JSONPath.read(actResult, matcher.group(1)).toString().equals(matcher.group(2)) ? 1:0;
            list.add(flag);
            if(flag == 1){
                count++;
            }

        }
        /*
        if(!list.contains(0)){
            flag = 1;
        }

         */

        return count;
    }

}
