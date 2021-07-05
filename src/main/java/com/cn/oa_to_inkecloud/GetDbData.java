package com.cn.oa_to_inkecloud;

import com.cn.nvwa_testcase_statistics.util.DbcpUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.HashMap;
import java.util.Map;

/**
 * @ClassName: 1
 * @Author: huangzhijuan
 * @Description: TODO
 * @Date: Create in 1 1
 * @Version 1.0
 */
public class GetDbData {
    private static PreparedStatement ps = null;

    public static Map<String,String> getData(){
        Map<String,String> map = new HashMap<>();
        Connection connection = null;
        ResultSet rs = null;
        String sql = "select login_name,email,chinese_name,department from personnel_info";
        try{
            connection = DbcpUtil.getConnection();
            ps = connection.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()){
                String login_name = rs.getString("login_name");
                String email = rs.getString("email");
                String chinese_name = rs.getString("chinese_name");
                String department = rs.getString("department");


                String info = email + " : " + chinese_name + " : " + department;
                map.put(login_name,info);
            }

        }catch (Exception e){
            e.printStackTrace();
        }

        return map;
    }

    public static void main(String[] args) {
        Map<String, String> data = getData();
        for(Map.Entry<String,String> entry : data.entrySet()){
            String key = entry.getKey();
            String value = entry.getValue();
            System.out.println(key + " : " + value);
        }
    }
}
