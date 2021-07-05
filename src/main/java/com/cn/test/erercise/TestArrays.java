package com.cn.test.erercise;

import java.util.Arrays;

/**
 * @ClassName: 1
 * @Author: huangzhijuan
 * @Description: TODO
 * @Date: Create in 1 1
 * @Version 1.0
 *
 * 题目：
 * 请使用Arrays相关的API，将一个随机字符串的所有字符升序排列，并倒序打印
 */
public class TestArrays {
    public static void main(String[] args) {
        String str = "dshi235hk5nbkjhdk3469dk";

        //使用toCharArray()将字符串变为数组
        char[] chars = str.toCharArray();
        //Arrays.sort()进行升序排序
        Arrays.sort(chars);

        //倒序打印
        for(int i=chars.length-1;i>=0;i--){
            System.out.print(chars[i]);
        }
    }
}
