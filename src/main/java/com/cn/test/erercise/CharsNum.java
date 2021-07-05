package com.cn.test.erercise;

import java.util.HashMap;
import java.util.Scanner;

/**
 * @ClassName: 1
 * @Author: huangzhijuan
 * @Description: TODO 计算字符串中每个字符的个数
 * @Date: Create in 1 1
 * @Version 1.0
 */

public class CharsNum {
    public static void main(String[] args) {
        //1、用Scanner类输入一个字符串
        Scanner sc = new Scanner(System.in);
        System.out.println("请输入一个字符串：");
        String str = sc.next();

        //2、定义一个hashmap集合用于存放字符及其个数
        HashMap<Character,Integer> map = new HashMap<>();

        //3、遍历字符串
        for (char c:str.toCharArray()) {
            //4、判断如果hashmap集合包含该字符，那么value++,再把key和value存入hashmap集合。如果不存在，则把存入字符和1
            if(map.containsKey(c)){
                Integer value = map.get(c);
                value++;
                map.put(c,value);
            }else{
                map.put(c,1);
            }
        }

        //4、遍历hashmap集合
        for (Character key:map.keySet()) {
            Integer value= map.get(key);
            System.out.println(key + ":" + value);
        }
    }
}
