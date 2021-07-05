package com.cn.test.erercise;

/**
 * @ClassName: huangzhijuan
 * @Author: huangzhijuan
 * @Description: TODO
 * @Date: Create in 11:01 2019.10.21
 * @Version 1.0
 */
public class Test01 {

    public static void main(String[] args) {


        //创建一个对象
        Test01 t01 = new Test01();
        //获取该对象的class对象
        Class c1 = t01.getClass();
        //获取类名称
        System.out.println("反射的第一种方式："+c1.getName());




        Class c2 = Test01.class;
        //获取类名称
        System.out.println("反射的第二种方式："+c2.getName());


        try {
            //根据类的全路径名获取
            Class c3 = Class.forName("com.cn.test.erercise.Test01");
            //获取类名称
            System.out.println("反射的第三种方式："+c3.getName());
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}
