package com.cn.test.thread;

/**
 * @ClassName: RunnableDemo
 * @Author: huangzhijuan
 * @Description: TODO
 * @Date: Create in 11:52 2019.10.28
 * @Version 1.0
 */

class RunnableTest implements Runnable{
    private int ticket = 5;


    public  void run() {

        while(ticket>0){
            System.out.println("Running" + Thread.currentThread().getName() + ": tikcet=" + ticket--);
        }

       // System.out.println(Thread.currentThread().getName() + ": tikcet=" + ticket--);
    }
}
public class RunnableDemo {
    public static void main(String[] args) {
        RunnableTest rt = new RunnableTest();
        for(int i=0;i<3;i++){
            new Thread(rt).start();
        }
    }
}
