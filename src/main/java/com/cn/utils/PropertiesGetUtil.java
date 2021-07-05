package com.cn.utils;

import java.io.InputStream;
import java.util.Properties;

/**
 * @ClassName: 1
 * @Author: huangzhijuan
 * @Description: TODO 获取properties配置
 * @Date: Create in 1 1
 * @Version 1.0
 */
public class PropertiesGetUtil {
    public String getProperties(String key){
        String property = null;
        try{
            Properties pro = new Properties();
            InputStream in = PropertiesGetUtil.class.getClassLoader().getResourceAsStream("config.properties");
            pro.load(in);
            property = pro.getProperty(key);
        }catch (Exception e){
            e.printStackTrace();
        }
       return property;
    }
}
