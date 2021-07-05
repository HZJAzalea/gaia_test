package com.cn.serviceinfo;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.cn.utils.HttpReqUtil;

import java.util.*;

/**
 * @ClassName: 1
 * @Author: huangzhijuan
 * @Description: TODO
 * @Date: Create in 1 1
 * @Version 1.0
 */
public class NewCompareToOld {
    private static String port = "10003";
    private static String uri = "/api/v2/base/serviceinfo/info";
    private static String oldHost = "http://10.100.130.23";
    private static String newHost = "http://10.100.130.127";
    private static Map<String, String> oldKeyUrlmap = new HashMap<>();
    private static Map<String, String> newKeyUrlMap = new HashMap<>();



    //获取各业务线返回值,并将业务线header和返回值放入map集合
    public static Map<String,String> putHeadAndReslutToMap(String host){
        Map<String,String> infoMap = new HashMap<>();
        String result = null;

        String url = host + ":" + port + uri;
        String param = "use_type=3";

        String[] headerArr = HttpReqUtil.getHeader();
        for(String header : headerArr){
            result = HttpReqUtil.sendGetByHeader(url,param,header);
            infoMap.put(header,result);
        }
        return infoMap;
    }

    //获取接口返回值的key和url，并将它们放入map集合
    public static Map<String,String> putResultToMap(String result){
        Map<String,String> map = new HashMap<>();

        JSONObject jsonObject = JSON.parseObject(result);
        JSONArray jsonArray = jsonObject.getJSONObject("data").getJSONArray("server");
        for(int i=0; i < jsonArray.size(); i++){
            String key = jsonArray.getJSONObject(i).getString("key");
            String url = jsonArray.getJSONObject(i).getString("url");
            map.put(key,url);
        }

        return map;
    }

    //新老服务作对比
    public static void compare(Map<String,String> oldKeyUrlmap,Map<String,String> newKeyUrlMap){
        if(oldKeyUrlmap.equals(newKeyUrlMap)){
            System.out.println("【该业务方新老版本返回结果一致】");
        }else {

            for(Map.Entry<String,String> old :oldKeyUrlmap.entrySet()){
                if(newKeyUrlMap.containsKey(old.getKey())){
                    if(!newKeyUrlMap.get(old.getKey()).equals(old.getValue())){
                        System.out.println("【新服务与老服务不一致的部分】");
                        System.out.println("key:" + old.getKey() + "  老服务url:" +old.getValue() + "  新服务url:" + newKeyUrlMap.get(old.getKey()));
                    }
                }else {
                    System.out.println("【新服务缺失部分】" +" key:" + old.getKey()+ "  url: " + old.getValue());
                }
            }

            if(!newKeyUrlMap.keySet().removeAll(oldKeyUrlmap.keySet())){

                Iterator<String> iterator = newKeyUrlMap.keySet().iterator();
                System.out.println("【新服务新增部分】");
                while (iterator.hasNext()){
                    String key = iterator.next();
                    System.out.println("key:" + key + "  url:" + newKeyUrlMap.get(key));
                }

            }
        }
    }
    public static void main(String[] args) {

        //获取老服务各业务线header及返回值
        Map<String, String> oldHeadResponsemap = putHeadAndReslutToMap(oldHost);

        //获取新服务各业务线header及返回值
        Map<String, String> newHeadResponseMap = putHeadAndReslutToMap(newHost);

        Set<String> headerSet = oldHeadResponsemap.keySet();
        for(String header :headerSet){
            System.out.println("业务方为：" + header);
            //根据header获取到老服务的接口返回值
            String oldResponse = oldHeadResponsemap.get(header);
            //根据header获取到新服务的接口返回值
            String newResponse = newHeadResponseMap.get(header);
            if(oldResponse != null && oldResponse.contains("\"dm_error\":0")){
                oldKeyUrlmap = putResultToMap(oldResponse);
            }else {
                System.out.println("【老服务未部署该业务方或存在异常，请检查！】");
            }

            if(newResponse != null && newResponse.contains("\"dm_error\":0")){
                newKeyUrlMap = putResultToMap(newResponse);
               compare(oldKeyUrlmap,newKeyUrlMap);
            }else {
                System.out.println("【新服务未部署该业务方或存在异常，请检查！】");
            }

            System.out.println("==============================================");
        }



    }
}
