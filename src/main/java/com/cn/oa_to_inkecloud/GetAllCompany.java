package com.cn.oa_to_inkecloud;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import org.testng.collections.Lists;

import java.util.ArrayList;
import java.util.List;

/**
 * @ClassName: 1
 * @Author: huangzhijuan
 * @Description: TODO
 * @Date: Create in 1 1
 * @Version 1.0
 */
public class GetAllCompany {
    public static List<String> getCompanyID(){
        List<String> orgAccountIdList = new ArrayList<String>();
        String url = "http://oa.inke.cn:8080/seeyon/rest/orgAccounts";
        String param = "";
        String token = GetToken.getToken();
        String allCompanyString = HttpReqUtil.sendGet(url, param, token);
        JSONArray jsonArray = JSONObject.parseArray(allCompanyString);
        if(jsonArray.size()>0){
            for(int i=0;i<jsonArray.size();i++){
                JSONObject jsonObject = jsonArray.getJSONObject(i);
                String orgAccountId = jsonObject.getString("orgAccountId");
                orgAccountIdList.add(orgAccountId);
            }
        }

        return orgAccountIdList;
    }

}
