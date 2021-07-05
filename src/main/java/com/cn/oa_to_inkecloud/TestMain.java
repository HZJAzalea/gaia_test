package com.cn.oa_to_inkecloud;

import java.util.List;
import java.util.Map;

/**
 * @ClassName: 1
 * @Author: huangzhijuan
 * @Description: TODO
 * @Date: Create in 1 1
 * @Version 1.0
 */
public class TestMain {
    public static void main(String[] args) {
        //getCompanyMembers
        List<String> membersList = GetCompanyMembers.getMembers();
        List<Map<String, String>> memberInfoList = GetCompanyMembers.getMemberInfo(membersList);

        for(Map<String,String> memberInfo : memberInfoList){

            for(Map.Entry<String,String> entry : memberInfo.entrySet()){
                String key = entry.getKey();
                String value = entry.getValue();
                System.out.println("OA接口数据： " + key + " : " + value);

//                if(mysqlData.containsKey(key)){
//                    dbValue = mysqlData.get(key);
//                }
//                if(value.equals(dbValue)){
//                    System.out.println("OA接口数据和数据库一致");
//                }else {
//                    System.out.println( key + "OA接口数据： " +" : " + value + " 数据库数据： " + dbValue);
//                }

            }
            System.out.println(memberInfo.size());
        }
    }
}
