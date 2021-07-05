package com.cn.gaia.test;

import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.testng.annotations.Test;

import java.io.IOException;

/**
 * @ClassName: PercentTest
 * @Author: huangzhijuan
 * @Description: TODO
 * @Date: Create in 15:23 2019.10.29
 * @Version 1.0
 */
public class PercentTest {

    @Test
    public void percentDemo(){
        int count=100;//请求接口的次数
        for(int i=0;i<100;i++){
            CloseableHttpClient httpClient = HttpClients.createDefault();
            HttpGet get = new HttpGet("http://tx3-gaia-testad01.bj:21588/ad/ad_reception"); //第一次中奖接口，先单个在浏览器中运行看结果是否有异常
            // HttpGet get = new HttpGet("http://**********"); //第二次中奖接口
            try {
                CloseableHttpResponse response = httpClient.execute(get);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
