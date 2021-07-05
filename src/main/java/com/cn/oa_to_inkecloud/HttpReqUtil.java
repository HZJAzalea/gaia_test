package com.cn.oa_to_inkecloud;

import com.cn.utils.ParseJsonToMapUtil;
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

    /**
     *
     * @Title: httpReqConfig
     * @Description: TODO
     * @param: @param httpRequestBase
     * @return: void
     * @throws
     */
    public static void httpReqConfig(HttpRequestBase httpRequestBase){
//        PropertiesGet propertiesGet = new PropertiesGet();
//        String header = propertiesGet.getProperties("header");
//        String token = propertiesGet.getProperties("token");

        httpRequestBase.setHeader("Content-Type", "application/json");
        httpRequestBase.setHeader("Cookie_", "JSESSIONID=20D7D7ED35F94C1CE08518BBE5813CF4");
//        httpRequestBase.setHeader("token", token);

        RequestConfig requestConfig = RequestConfig.custom()
                .setConnectionRequestTimeout(2000)
                .build();

        httpRequestBase.setConfig(requestConfig);

    }

    /**
     *
     * @Title: httpReqConfig
     * @Description: TODO
     * @param: @param httpRequestBase
     * @return: void
     * @throws
     */
    public static void httpReqConfigWithToken(HttpRequestBase httpRequestBase,String token){

        httpRequestBase.setHeader("Content-Type", "application/json");
        httpRequestBase.setHeader("Cookie_", "JSESSIONID=20D7D7ED35F94C1CE08518BBE5813CF4");
        httpRequestBase.setHeader("token", token);

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
    public static String sendGet(String url,String param,String token){

        String result = null;
        CloseableHttpResponse response = null;
        String reqUrl = url + "/" + param;

        try {

            URL ur = new URL(reqUrl.toString());
            URI uri = new URI("http",ur.getUserInfo(),ur.getHost(),ur.getPort(),ur.getPath(),ur.getQuery(),null);


            CloseableHttpClient httpclient = HttpClients.custom()
                    .setDefaultCookieStore(cookieStore)
                    .build();

            HttpGet httpGet = new HttpGet(uri);

            httpReqConfigWithToken(httpGet,token);
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
        httpReqConfig(httpPost);

        try {
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
