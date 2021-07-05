package com.cn.oa_to_inkecloud;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

import java.util.*;

/**
 * @ClassName: 1
 * @Author: huangzhijuan
 * @Description: TODO
 * @Date: Create in 1 1
 * @Version 1.0
 */
public class GetCompanyMembers {
    public static List<String> getMembers(){
        List<String> list = new ArrayList<>();
        String membersString = null;
        String url = "http://oa.inke.cn:8080/seeyon/rest/orgMembers";
//        String param = null;
        List<String> companyIDList = GetAllCompany.getCompanyID();
        String token = GetToken.getToken();
        for(String companyID : companyIDList){
//            param = companyID;
            membersString = HttpReqUtil.sendGet(url, companyID, token);
            if(membersString != null){
                list.add(membersString);
            }

        }

        return list;
    }

    public static List<Map<String,String>> getMemberInfo(List<String> membersList){
        List<Map<String,String>> list = new ArrayList<>();
        Map<String,String> map = new HashMap<>();
        String wholeName = null;

        String dePartmentUrl = "http://oa.inke.cn:8080/seeyon/rest/orgDepartment";
        String token = GetToken.getToken();

        for(String members : membersList){
            JSONArray jsonArray = JSONObject.parseArray(members);
            if(jsonArray.size()>0){
                for(int i=0;i<jsonArray.size();i++){
                    JSONObject jsonObject = jsonArray.getJSONObject(i);
                    String loginName = jsonObject.getString("loginName");
                    String emailAddress = jsonObject.getString("emailAddress");
                    String name = jsonObject.getString("name");
                    String orgDepartmentId = jsonObject.getString("orgDepartmentId");
                    //查询部门信息
                    String departmentString = HttpReqUtil.sendGet(dePartmentUrl, orgDepartmentId, token);
                    JSONObject object = JSONObject.parseObject(departmentString);
                    wholeName = object.getString("wholeName");
                    String wName = wholeName.replaceAll(",", "-");


                    String info = emailAddress + " : " + name + " : " + wName;
                    map.put(loginName,info);

                }

            }
        }
        list.add(map);

        return list;
    }




    public static void main(String[] args) {
//        Map<String, String> mysqlData = GetDbData.getData();
//        String dbValue = null;

        List<String> membersList = getMembers();
        List<Map<String, String>> memberInfoList = getMemberInfo(membersList);

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

