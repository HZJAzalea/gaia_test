package com.cn.oa_to_inkecloud;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

/**
 * @ClassName: 1
 * @Author: huangzhijuan
 * @Description: TODO
 * @Date: Create in 1 1
 * @Version 1.0
 */
public class GetToken {
    public static String getToken(){
        String url = "http://oa.inke.cn:8080/seeyon/rest/token";
        String param = "{\"userName\":\"inke-cloud\",\"password\":\"ridSVwxXbg27gs6G\"}";
        String tokenString = HttpReqUtil.sendPost(url, param);
        JSONObject jsonObject = JSONArray.parseObject(tokenString);
        String token = jsonObject.getString("id");
        return token;
    }

}
