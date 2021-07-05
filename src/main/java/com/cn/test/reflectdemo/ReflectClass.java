package com.cn.test.reflectdemo;

import com.oracle.tools.packager.Log;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;

/**
 * @ClassName: ReflectClass
 * @Author: huangzhijuan
 * @Description: TODO
 * @Date: Create in 14:25 2019.10.21
 * @Version 1.0
 */
public class ReflectClass {
    private final static String TAG = "com.cn.test.reflectdemo.ReflectClass";

    //创建对象
    public static void reflectNewInstance(){
        try {
            Class<?> classBook = Class.forName("com.cn.test.reflectdemo.Book");
            Object objectBook = classBook.newInstance();
            Book book = (Book)objectBook;
            book.setName("Android进阶之光");
            book.setAuthor("刘望舒");
            //Log.d(TAG,"reflectNewInstance book = " + book.toString());
            System.out.println(TAG + " " + "reflectNewInstance book = " + book.toString());

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    //反射私有的构造方法
    public static void reflectPrivateConstructor(){
        try {
            Class<?> classBook = Class.forName("com.cn.test.reflectdemo.Book");
            Constructor<?> declaredConstructorBook = classBook.getDeclaredConstructor(String.class,String.class);
            declaredConstructorBook.setAccessible(true);
            Object objectBook = declaredConstructorBook.newInstance("Android开发艺术探索","任玉刚");
            Book book = (Book) objectBook;
            System.out.println(TAG + " " + "reflectPrivateConstructor book = " + book.toString());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    //反射私有属性
    public static void reflectPrivateField(){
        try {
            Class<?> classBook = Class.forName("com.cn.test.reflectdemo.Book");
            Object objectBook = classBook.newInstance();
            Field fieldTag = classBook.getDeclaredField("TAG");
            fieldTag.setAccessible(true);
            String tag = (String)fieldTag.get(objectBook);
            System.out.println(TAG + " " + "reflectPrivateField tag = " + tag);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    //反射私有方法
    public static void reflectPrivateMethod(){
        try{
            Class<?> classBook = Class.forName("com.cn.test.reflectdemo.Book");
            Method methodBook = classBook.getDeclaredMethod("declaredMethod", int.class);
            methodBook.setAccessible(true);
            Object objectBook = classBook.newInstance();
            String string = (String) methodBook.invoke(objectBook,0);
            System.out.println(TAG + " " + "reflectPrivateMethod method = " + string);


        }catch(Exception e){
            e.printStackTrace();
        }
    }




}
