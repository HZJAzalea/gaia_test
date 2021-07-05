package com.cn.nvwafile_compare_appfile;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.cn.utils.HttpReqUtil;
import com.cn.utils.ParseJsonToMapUtil;
import com.cn.utils.PatternUtil;
import org.dom4j.Document;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

import java.io.File;
import java.util.*;

/**
 * @ClassName: 1
 * @Author: huangzhijuan
 * @Description: TODO
 * @Date: Create in 1 1
 * @Version 1.0
 */
public class FileCompare {

    //将android配置和中台接口返回的key和url分别放入map集合
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

    //将ios配置的key和url放入map集合
    public static Map<String,String> putIosTpMap(File iosFile){
        List<String> valueList = new ArrayList<>();
        Map<String,String> keyMap = new HashMap<>();

        SAXReader sax = new SAXReader();
        try {
            Document document = sax.read(iosFile);
            //获取根结点
            Element rootElement = document.getRootElement();

            List<Element> childNodes = rootElement.elements();
            for(Element element : childNodes){
                List<Element> elements = element.elements();
                for(Element ele : elements){
                    List<Element> elementsList = ele.elements("string");
                    for(Element e : elementsList){
                        valueList.add(e.getStringValue());
                        for(int i=0;i<valueList.size()-1;i++){
                            if(i%2==0){
                                keyMap.put(valueList.get(i),valueList.get(i+1));
                            }
                        }
                    }
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return keyMap;
    }

//    public Element getXMLElement(List<Element> childNodes){
//        Element e = null;
//        for(Element element : childNodes){
////            List<Element> elements = element.elements();
//            if(isElement(element)){
//
//            }else {
//                getXMLElement(elements);
//            }
//
//
//        }
//        return e;
//    }

    public static boolean isElement(Element element){
        boolean flag = true;
        try {
            element.elements();
        } catch (Exception e) {
            flag = false;
        }
        return flag;
    }
    //判断业务方配置和中台接口返回值是否一致
    public static void compareResult(Map<String,String> nvwaMap,Map<String,String> map){
        Set<String> nvwaKeySet = nvwaMap.keySet();
        String nvwaUrl = null;
        String url = null;
        String uri = null;
        //判断android配置中是否存在中台接口返回的key
        for(String nvwaKey:nvwaKeySet){
            if(map.containsKey(nvwaKey)){
                nvwaUrl = nvwaMap.get(nvwaKey);
                url = map.get(nvwaKey);

                //将中台接口返回的url域名截掉
                String[] nvwaSplit = nvwaUrl.split("\\.com");
                String nvwaUri = nvwaSplit[1].toString();
                //将业务方配置中的url域名截掉
                if(url.contains(".com")){
                    String[] urlSplit = url.split("\\.com");
                    uri = urlSplit[1];
                }else if(url.contains(".cn")){
                    String[] urlSplit = url.split("\\.cn");
                    uri = urlSplit[1];
                }else {
                    uri = url;
                }

                if(!nvwaUri.equals(uri)){
                    System.out.println("对比结果false " +" key:" + nvwaKey +  "   业务方url：" + url + "   中台url： " + nvwaUrl);
                }

            }else {
                nvwaUrl = nvwaMap.get(nvwaKey);
                System.out.println("业务方配置不存在： "+ " key: " + nvwaKey + "  中台url： " +nvwaUrl );
            }


        }
    }

    //获取接口返回值，将请求不通的接口返回
    public static void nvwaApiReturnValue(Map<String,String> map){
        int flag = 0;
        String url = null;
        String actUrl = null;

        Set<String> keySet = map.keySet();
        for(String key : keySet){
            url = map.get(key);
            //判断如果url包含域名，则不做
            if(url.contains(".com" ) || url.contains(".cn")){
                actUrl = url;
            }else {
                actUrl = "https://ztapi.thlrs.com" + url;
            }
            String result = HttpReqUtil.sendGet(actUrl, "");
            //判断result是否为json格式，如果是，则和预期dm_error值进行比较
            if(ParseJsonToMapUtil.isJsonString(result)){
                flag = PatternUtil.compareResult("dm_error=499", result);
                if(flag != 1){
                    flag = PatternUtil.compareResult("dm_error=0",result);
                    if(flag != 1){
                        System.out.println("key: "+ key + "    接口返回值:  " + result);
                    }
                }
            }else {
                System.out.println("key: "+ key + "    接口返回值:  " + result);
            }


        }
    }

}
