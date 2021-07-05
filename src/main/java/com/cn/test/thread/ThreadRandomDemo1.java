package com.cn.test.thread;

/**
 * @ClassName: ThreadRandomDemo1
 * @Author: huangzhijuan
 * @Description: TODO
 * @Date: Create in 11:31 2019.10.28
 * @Version 1.0
 */
class RandomThread extends Thread{
    private int ticket = 5;
    /*
    public RandomThread(String name){
        super(name);
    }

     */

    @Override
    public void run() {
        try {
            //Thread.sleep(1000);
            while(ticket>0){
                System.out.println("Thread" + Thread.currentThread().getName() + ": tikcet=" + ticket--);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
public class ThreadRandomDemo1 {
    public static void main(String[] args) {
        for(int i=0;i<3;i++){
            new RandomThread().start();
        }
    }
}
