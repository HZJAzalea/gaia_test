package com.cn.gateway;

import com.cn.utils.*;

import java.io.File;

/**
 * @ClassName: 1
 * @Author: huangzhijuan
 * @Description: TODO 限流服务线上接口测试
 * @Date: Create in 1 1
 * @Version 1.0
 */
public class GatewayUtil {
    private static String gateWayTxt = null;
    private static String host = null;

    static {
        PropertiesGetUtil propertiesGet = new PropertiesGetUtil();
        gateWayTxt = propertiesGet.getProperties("gateWayTxt");
        host = propertiesGet.getProperties("host");
    }
    public static void main(String[] args) {
//        gateWayTxt = "/Users/huangzhijuan/Documents/gateway.txt";
//        host = "http://10.100.28.36:10087";
        File file = new File(gateWayTxt);
        String str = TxtReaderUtil.txtToString(file);
        String param_url = null;
        String uri = null;
        int flagGet = 0;
        int flagPost = 0;
//        String[] restServes = str.split("\\nRestServe");
        String[] restServes = str.split("\\n");
        for(String re : restServes){


            if(re != "" && re != null){
                uri = re.replaceAll("\\.", "\\/");
                param_url = host + uri;
            }

            try {
                String getResult = HttpReqUtil.sendGet(param_url, "");
                Thread.sleep(1000);
                //判断result是否为json格式，如果是，则和预期dm_error值进行比较
                if(ParseJsonToMapUtil.isJsonString(getResult)) {
                    flagGet = PatternUtil.compareResult("dm_error=499", getResult);
                    if (flagGet != 1) {
                        flagGet = PatternUtil.compareResult("dm_error=0", getResult);
                        if (flagGet != 1) {
                            flagGet = PatternUtil.compareResult("dm_error=1300",getResult);
                            if(flagGet != 1){

                                String postResult = HttpReqUtil.sendPost(param_url, "{}");
                                Thread.sleep(1000);
                                flagPost = PatternUtil.compareResult("dm_error=499", postResult);
                                if (flagPost != 1) {
                                    flagPost = PatternUtil.compareResult("dm_error=0", postResult);
                                    if (flagPost != 1) {
                                        flagPost = PatternUtil.compareResult("dm_error=1300",postResult);
                                        if(flagPost != 1){
                                            System.out.println(param_url + "    接口返回值:  " + postResult);
                                        }

                                    }
                                }
                            }
                        }
                    }
                }else {
                    System.out.println(param_url + "    接口返回值:  " + getResult);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }

        }
    }

}
