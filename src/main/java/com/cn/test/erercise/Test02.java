package com.cn.test.erercise;

/**
 * @ClassName: Test02
 * @Author: huangzhijuan
 * @Description: TODO
 * @Date: Create in 14:18 2019.10.22
 * @Version 1.0
 */
public class Test02 {

    private static int a = 100;
    private static int b = 200;

    public static void exchangeAB(){
        int temp = a;
        a = b;
        b = temp;
        System.out.println("a=" + a + ",b=" + b);
    }

    public static void main(String[] args) {
        Test02.exchangeAB();
    }


}
