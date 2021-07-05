package com.cn.utils;

import org.apache.http.HttpEntity;
import org.apache.http.HttpStatus;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpRequestBase;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.BasicCookieStore;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.apache.log4j.Logger;

import java.io.IOException;
import java.net.URI;
import java.net.URL;


/**
 * @ClassName: HttpReqUtil
 * @Author: huangzhijuan
 * @Description: TODO
 * @Date: Create in 17:34 2019.10.29
 * @Version 1.0
 */
public class HttpReqUtil {

    private static BasicCookieStore cookieStore = new BasicCookieStore();
    private static Logger log = Logger.getLogger(HttpReqUtil.class);

    public static String[] getHeader(){
        String[] headerArr = null;
        PropertiesGetUtil propertiesGet = new PropertiesGetUtil();
        String headers = propertiesGet.getProperties("namespace");
        headerArr = headers.split(",");
//        for (String head:headerArr) {
////            System.out.println(header);
////            httpRequestBase.setHeader("uberctx-_namespace_appkey_", header);
//            header = head;
//        }
//        System.out.println(header);
        return headerArr;
    }

    /**
     *
     * @Title: httpReqConfig
     * @Description: TODO
     * @param: @param httpRequestBase
     * @return: void
     * @throws
     */
    public static void httpReqConfig(HttpRequestBase httpRequestBase){
        PropertiesGetUtil propertiesGet = new PropertiesGetUtil();
        String header = propertiesGet.getProperties("header");
        String token = propertiesGet.getProperties("token");

        httpRequestBase.setHeader("Content-Type", "application/json");
        httpRequestBase.setHeader("uberctx-_namespace_appkey_", header);
        httpRequestBase.setHeader("Gwtoken_", token);

        RequestConfig requestConfig = RequestConfig.custom()
                .setConnectionRequestTimeout(2000)
                .build();

        httpRequestBase.setConfig(requestConfig);

    }

    public static void httpReqConfigByHeader(HttpRequestBase httpRequestBase,String header){

        httpRequestBase.setHeader("Content-Type", "application/json");
        httpRequestBase.setHeader("uberctx-_namespace_appkey_", header);

        RequestConfig requestConfig = RequestConfig.custom()
                .setConnectionRequestTimeout(2000)
                .build();

        httpRequestBase.setConfig(requestConfig);

    }

    /**
     *
     * @Title: sendGet
     * @Description: TODO
     * @param: @param url
     * @param: @param param
     * @param: @return
     * @return: String
     * @throws
     */
    public static String sendGet(String url,String param){

        String result = null;
        CloseableHttpResponse response = null;
        String reqUrl = url + "?" + param;

            try {

                URL ur = new URL(reqUrl.toString());
                URI uri = new URI("http",ur.getUserInfo(),ur.getHost(),ur.getPort(),ur.getPath(),ur.getQuery(),null);


                CloseableHttpClient httpclient = HttpClients.custom()
                        .setDefaultCookieStore(cookieStore)
                        .build();

                HttpGet httpGet = new HttpGet(uri);

            httpReqConfig(httpGet);
            response = httpclient.execute(httpGet);
            if(response.getStatusLine().getStatusCode() == HttpStatus.SC_OK){
                HttpEntity entity = response.getEntity();
                result = EntityUtils.toString(entity, "utf-8");

            }
            } catch (Exception e) {
                e.printStackTrace();
            } finally{
                try {
                    response.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }

            }

        return result;
    }

    //根据header发送get请求  勿动！！！
    public static String sendGetByHeader(String url,String param,String header){

        String result = null;
        CloseableHttpResponse response = null;
        String reqUrl = url + "?" + param;

        try {

            URL ur = new URL(reqUrl.toString());
            URI uri = new URI("http",ur.getUserInfo(),ur.getHost(),ur.getPort(),ur.getPath(),ur.getQuery(),null);


            CloseableHttpClient httpclient = HttpClients.custom()
                    .setDefaultCookieStore(cookieStore)
                    .build();

            HttpGet httpGet = new HttpGet(uri);

            httpReqConfigByHeader(httpGet,header);
            response = httpclient.execute(httpGet);
            if(response.getStatusLine().getStatusCode() == HttpStatus.SC_OK){
                HttpEntity entity = response.getEntity();
                result = EntityUtils.toString(entity, "utf-8");

            }
        } catch (Exception e) {
//            e.printStackTrace();
            log.info(e.getMessage());
        } finally{
            try {
                if(response != null ){
                    response.close();
                }

            } catch (IOException e) {
                e.printStackTrace();
            }

        }

        return result;
    }

    /**
     *
     *
     * @Title: sendPost
     * @Description: TODO
     * @param: @param url
     * @param: @param param
     * @param: @return
     * @return: String
     * @throws
     */
    public static String sendPost(String url,String param){

        String result = null;
        CloseableHttpResponse response = null;

        CloseableHttpClient httpclient = HttpClients.custom()
                .setDefaultCookieStore(cookieStore)
                .build();

        HttpPost httpPost = new HttpPost(url);
        //String[] headerArr = getHeader();


        try {

                httpReqConfig(httpPost);
                StringEntity stringEntity = new StringEntity(param);

                if(new ParseJsonToMapUtil().isJsonString(param)){
                    //json
                    stringEntity.setContentType("application/json; charset=UTF-8");
                }else{

                    stringEntity.setContentType("application/x-www-form-urlencoded; charset=UTF-8");
                }

                httpPost.setEntity(stringEntity);

                response = httpclient.execute(httpPost);

                if(response.getStatusLine().getStatusCode() == HttpStatus.SC_OK){
                    HttpEntity entity = response.getEntity();
                    result = EntityUtils.toString(entity, "utf-8");

            }
//            httpReqConfig(httpPost,header);
//            StringEntity stringEntity = new StringEntity(param);
//
//            if(new ParseJsonToMapUtil().isJsonString(param)){
//                //json
//                stringEntity.setContentType("application/json; charset=UTF-8");
//            }else{
//
//                stringEntity.setContentType("application/x-www-form-urlencoded; charset=UTF-8");
//            }
//
//            httpPost.setEntity(stringEntity);
//
//            response = httpclient.execute(httpPost);
//
//            if(response.getStatusLine().getStatusCode() == HttpStatus.SC_OK){
//                HttpEntity entity = response.getEntity();
//                result = EntityUtils.toString(entity, "utf-8");
//            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally{
            try {
                response.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        return result;
    }



}
