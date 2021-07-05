package com.cn.test.thread;

import org.apache.poi.ss.formula.functions.T;

/**
 * @ClassName: 1
 * @Author: huangzhijuan
 * @Description: TODO
 * @Date: Create in 1 1
 * @Version 1.0
 */
public class SaleTicketTest implements Runnable{

    private static int ticket = 100;

    Object obj = new Object();

    //第一种同步方式：同步代码块
//    @Override
//    public void run() {
//        synchronized (obj){
//            while(true){
//                if(ticket > 0){
//                    System.out.println(Thread.currentThread().getName() + "正在卖第" + ticket + "张票");
//                    ticket--;
//                }
//            }
//        }
//
//    }


    @Override
    public void run() {
        saleTicket();
    }
    public synchronized void saleTicket(){
        while (true){
            if(ticket > 0){
//                try {
//                    Thread.sleep(1000);
//                } catch (InterruptedException e) {
//                    e.printStackTrace();
//                }
                System.out.println(Thread.currentThread().getName() + "正在卖第" + ticket + "张票");
                ticket--;
            }
        }
    }

    public static void main(String[] args) {
        SaleTicketTest st = new SaleTicketTest();
        Thread t0 = new Thread(st);
        Thread t1 = new Thread(st);
        Thread t2 = new Thread(st);

        t1.start();
        t0.start();

        t2.start();
    }
}
