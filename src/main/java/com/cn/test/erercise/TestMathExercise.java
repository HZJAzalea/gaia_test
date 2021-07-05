package com.cn.test.erercise;

/**
 * @ClassName: 1
 * @Author: huangzhijuan
 * @Description: TODO
 * @Date: Create in 1 1
 * @Version 1.0
 *
 * 题目：
 * 计算在-10.8到5.9之间，绝对值大于6或者小于2.1的整数有多少个
 */
public class TestMathExercise {
    public static void main(String[] args) {
        double min = -10.8;
        double max = 5.9;

        int count = 0;//用于统计满足条件的整数个数

        /*
        //第1种方法：
        for(int i=(int)min;i<max;i++){//把min强转成int类型
            //得到绝对值
            int num = Math.abs(i);
            //判断如果绝对值大于6或小于2.1，则满足条件
            if(num>6 || num<2.1){
                System.out.println(i);
                count++;
            }
        }
        System.out.println("第一种方法总共有：" + count + "个");
        */


        //第2种方法：
        for(double i=Math.ceil(min);i<max;i++){//Math.ceil(min)将min向上取整
            //得到绝对值
            double num = Math.abs(i);
            if(num>6 || num<2.1){
                System.out.println((int)i);
                count++;
            }
        }
        System.out.println("第二种方法总共有：" + count + "个");

    }
}
