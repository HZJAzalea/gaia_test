package com.cn.test.erercise;

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

/**
 * @ClassName: HttpReqUtil
 * @Author: huangzhijuan
 * @Description: TODO
 * @Date: Create in 15:32 2019.10.29
 * @Version 1.0
 */
public class HttpReqUtil {
    private static BasicCookieStore cookieStore = new BasicCookieStore();
    private static int count = 1;//设置请求接口的次数

    /*
    设置header和请求超时配置
     */
    public static void httpReqConfig(HttpRequestBase httpRequestBase){
        //设置请求header
        httpRequestBase.setHeader("Accept", "application/json, text/javascript; q=0.01");
        httpRequestBase.setHeader("User-Agent", "Mozilla/5.0 (Windows NT 6.3; WOW64; rv:54.0");

        //请求超时配置
        /*
        RequestConfig.Builder builder = RequestConfig.custom();
        RequestConfig.Builder rb = builder.setConnectionRequestTimeout(1000);
        RequestConfig requestConfig = rb.build();
        httpRequestBase.setConfig(requestConfig);
        */
        RequestConfig requestConfig = RequestConfig.custom()
                .setConnectionRequestTimeout(1000)
                .build();
        httpRequestBase.setConfig(requestConfig);
    }

    /*
    发送get请求
     */
    public static String sendGet(String url,String params){
        String reqUrl = url + "?" + params;
        CloseableHttpResponse response = null;
        String result = null;
        //int count=1;//设置请求接口get的次数

        for(int i=0;i<count;i++){
            try {
                //CloseableHttpClient httpclient = HttpClients.createDefault();
                //设置cookie
           /* HttpClientBuilder httpclient1 = HttpClients.custom();
            HttpClientBuilder b = httpclient1.setDefaultCookieStore(cookieStore);
            CloseableHttpClient httpclient = b.build();
            */
                CloseableHttpClient httpclient = HttpClients.custom()
                        .setDefaultCookieStore(cookieStore)
                        .build();

                HttpGet httpGet = new HttpGet(reqUrl);
                httpReqConfig(httpGet);

                //发送请求
                response = httpclient.execute(httpGet);
                if(response.getStatusLine().getStatusCode() == HttpStatus.SC_OK){
                    //获取响应实体
                    HttpEntity entity = response.getEntity();
                    //将响应实体转化成String类型
                    result = EntityUtils.toString(entity,"utf-8");
                }
            } catch (IOException e) {
                e.printStackTrace();
            }finally{
                try {
                    //关闭连接
                    response.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

        return result;
    }

    /*
    发送post请求
     */
    public static String sendPost(String url,String params){

        CloseableHttpResponse response = null;
        String result = null;

        try {
            //CloseableHttpClient httpclient = HttpClients.createDefault();
            //设置cookie
            CloseableHttpClient httpclient = HttpClients.custom()
                    .setDefaultCookieStore(cookieStore)
                    .build();

            HttpPost httpPost = new HttpPost(url);
            httpReqConfig(httpPost);

            StringEntity stringEntity = new StringEntity(params);
            httpPost.setEntity(stringEntity);

           /* if(new ParseJsonToMapUtil().isJsonString(param)){
				//json
				stringEntity.setContentType("application/json; charset=UTF-8");
			}else{*/
            stringEntity.setContentType("application/x-www-form-urlencoded; charset=UTF-8");
            // }


            //发送post请求
            response = httpclient.execute(httpPost);
            if(response.getStatusLine().getStatusCode() == HttpStatus.SC_OK){
                //获取请求响应实体
                HttpEntity entity = response.getEntity();
                //将请求响应实体转换为String类型
                result = EntityUtils.toString(entity,"utf-8");
            }
        }catch(Exception e){
            e.printStackTrace();
        }finally {
            try {
                //关闭连接
                response.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        return result;

    }

    public static void main(String[] args) {
        String actResult = HttpReqUtil.sendGet("http://tx3-gaia-testad01.bj:21588/ad/ad_reception","uid=290327048&cc=TG54137&DeviceId=20190621111305828fde1b37f668f22a374f9d07cae33b0181146f4cfc24a0&AdPlace=32&cv=GA3.8.00_Android");

        int count1 = PatternUtil.compareResultToDb("$.data.cloud_config.switch_lock_screen.321001=https://cpu.baidu.com/1057/d06f5c23?scid=41272",actResult);
        System.out.println(count1);
        //int percent1 = count1/100;
        //System.out.println("概率："+percent1);
    }

}
