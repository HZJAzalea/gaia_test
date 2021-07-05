package com.cn.test.erercise;

/**
 * @ClassName: 1
 * @Author: huangzhijuan
 * @Description: TODO
 * @Date: Create in 1 1
 * @Version 1.0
 *
 * Math工具类
 * 其方法有：
 * public static double abs(double num);//绝对值
 * public static double ceil(double num);//向上取整
 * public static double floor(double num);//向下取整
 * public static long round(double num);//四舍五入
 */
public class TestMath {

    public static void main(String[] args) {
        //绝对值
        System.out.println(Math.abs(-10.2));//10.2
        System.out.println(Math.abs(13.1));//13.1
        System.out.println("====================");

        //向上取整
        System.out.println(Math.ceil(12.1));//13.0
        System.out.println(Math.ceil(12.9));//13.0
        System.out.println("====================");

        //向下取整
        System.out.println(Math.floor(14.1));//14.0
        System.out.println(Math.floor(14.9));//14.0
        System.out.println("====================");

        //四舍五入
        System.out.println(Math.round(15.1));//15
        System.out.println(Math.round(15.5));//16

    }
}
