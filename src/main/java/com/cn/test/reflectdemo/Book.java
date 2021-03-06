package com.cn.test.reflectdemo;

/**
 * @ClassName: Book
 * @Author: huangzhijuan
 * @Description: TODO 被反射类
 * @Date: Create in 14:13 2019.10.21
 * @Version 1.0
 */
public class Book {
    private final static String TAG = "BookTag";

    private String name;
    private String author;

    public String toString(){
        return "Book{" + "name=" + name + '\'' + ",authou=" + author + '\'' +"}";
    }

    public Book(){

    }

    public Book(String name,String author){
        this.name = name;
        this.author = author;
    }

    public String getName(){
        return name;
    }

    public void setName(String name){
        this.name = name;
    }

    public String getAuthor(){
        return author;
    }

    public void setAuthor(String author){
        this.author = author;
    }

    private String declaredMethod(int index){
        String string = null;
        switch(index){
            case 0:
                string = "I am declaredMethod 1!";
                break;
            case 1:
                string = "I am declaredMethod 2!";
                break;
            default:
                string = "I am declaredMethod 1!";
        }
        return string;
    }
}
