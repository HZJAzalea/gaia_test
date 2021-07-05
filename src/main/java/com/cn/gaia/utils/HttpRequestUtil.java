package com.cn.gaia.utils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.Semaphore;

/**
 * @ClassName: 1
 * @Author: huangzhijuan
 * @Description: TODO
 * @Date: Create in 1 1
 * @Version 1.0
 */
public class HttpRequestUtil implements Runnable{

    long startTime = 0;
    String endTime = null;
    int count0 = 0;
    int count509 = 0;
    StringBuffer result = null;

    @Override
    public void run() {
        for(int i=0;i<50;i++){
            //System.out.println(Thread.currentThread().getName());
            startTime = new Date().getTime();

            String methodUrl = "http://10.100.130.28:10087/api/v1/user/online/dev/list";
            String params = "did=smid0abcde0";
            HttpURLConnection connection = null;
            BufferedReader bfReader = null;
            String line = null;
            try{

                URL url = new URL(methodUrl + "?" + params);
                //根据url生成HttpURLConnection
                connection = (HttpURLConnection)url.openConnection();
                //默认get请求
                connection.setRequestMethod("GET");
                //设置请求头
//            setHeaders(connection);
                connection.setRequestProperty("uberctx-_namespace_appkey_","eos");
                connection.setRequestProperty("Gwtoken","usercenteronlinebase001");
                connection.setRequestProperty("Uberctx-Peer_discovery_name","eos.usercenter.online.utils");
                //建立tcp连接
                connection.connect();
                if(connection.getResponseCode() == HttpURLConnection.HTTP_OK){
                    //发送http请求
                    bfReader = new BufferedReader(new InputStreamReader(connection.getInputStream(),"UTF-8"));
                    result = new StringBuffer();
                    //循环读取流
                    while ((line = bfReader.readLine()) != null){
                        result.append(line).append(System.getProperty("line.separator"));
                    }
                    System.out.println(result.toString());

                    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                    endTime = sdf.format(Long.parseLong(String.valueOf(System.currentTimeMillis())));
                    System.out.println(Thread.currentThread().getName() + "完成时间" + endTime);

                    if((result.toString().contains("\"dm_error\":0")) ){
                        count0++;

                    }else if(result.toString().contains("\"dm_error\":509")){
                        count509++;
                    }
                    System.out.println("count0:"+count0);
                    System.out.println("count509:" + count509);

                }
            }catch (Exception e){
                e.printStackTrace();
            }finally {
                try {
                    bfReader.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

    }




    public static void main(String[] args) {

//       for(int i=0;i<50;i++){
//           new Thread(new HttpRequestUtil()).start();
//       }
        new Thread(new HttpRequestUtil()).start();



    }
}
