package com.cn.test.erercise;

import java.util.ArrayList;
import java.util.Collections;

/**
 * @ClassName: 1
 * @Author: huangzhijuan
 * @Description: TODO 扑克牌游戏，规则：共54张牌，3个人每人17张，剩下3张作为底牌
 * @Date: Create in 1 1
 * @Version 1.0
 */
public class PokerTest {
    public static ArrayList<String> poker = new ArrayList<>();
    public static ArrayList<String> poker(){
        //先定义牌，有2，A，K。。。3；大王、小王
        //ArrayList<String> poker = new ArrayList<>();
        //定义一个数组存放花色,花色：♥️、♠️、♦️、♣️
        String colors[] = {"♥️","♠️","♦️","♣️"};
        //定义一个数组存放数字
        String numbers[] = {"2","A","K","Q","J","10","9","8","7","6","5","4","3"};
        //大王小王比较特殊，先单独放进集合
        poker.add("大王");
        poker.add("小王");
        //再将其他牌放进集合
        for (String color : colors) {
            for (String number : numbers) {
                poker.add(color + number);
            }
        }
        //洗牌
        Collections.shuffle(poker);
        //System.out.println(poker);
        return poker;
    }



    public static void fapai(){
        PokerTest.poker();

        //发牌
        //一共3个人，每人17张牌，剩下3张作为底牌
        //先定义四个集合，分别表示3个人和底牌共分得的牌
        ArrayList<String> player01 = new ArrayList<>();
        ArrayList<String> player02 = new ArrayList<>();
        ArrayList<String> player03 = new ArrayList<>();
        ArrayList<String> dipai = new ArrayList<>();
        //按照集合索引%3=（0，1，2）来发牌；当索引>=51时，停止发牌，剩下的作为底牌
        for (int i = 0; i <poker.size() ; i++) {
            String p = poker.get(i);
            if(i>=51){
                // String p = poker.get(i);
                dipai.add(p);
            }else if(i%3 == 0){
                player01.add(p);
            }else if(i%3 == 1){
                player02.add(p);
            }else if(i%3 == 2){
                player03.add(p);
            }
        }

        //看牌
        System.out.println("player01的牌：" + player01);
        System.out.println("player02的牌：" + player02);
        System.out.println("player03的牌：" + player03);
        System.out.println("底牌：" + dipai);
    }

    public static void main(String[] args) {
        /*
        //先定义牌，有2，A，K。。。3；大王、小王
       ArrayList<String> poker = new ArrayList<>();
        //定义一个数组存放花色,花色：♥️、♠️、♦️、♣️
        String colors[] = {"♥️","♠️","♦️","♣️"};
        //定义一个数组存放数字
        String numbers[] = {"2","A","K","Q","J","10","9","8","7","6","5","4","3"};
        //大王小王比较特殊，先单独放进集合
        poker.add("大王");
        poker.add("小王");
        //再将其他牌放进集合
        for (String color : colors) {
            for (String number : numbers) {
                poker.add(color + number);
            }
        }
        //System.out.println(poker);


        //洗牌
        Collections.shuffle(poker);
        //System.out.println(poker);


        //发牌
        //一共3个人，每人17张牌，剩下3张作为底牌
        //先定义四个集合，分别表示3个人和底牌共分得的牌
        ArrayList<String> player01 = new ArrayList<>();
        ArrayList<String> player02 = new ArrayList<>();
        ArrayList<String> player03 = new ArrayList<>();
        ArrayList<String> dipai = new ArrayList<>();
        //按照集合索引%3=（0，1，2）来发牌；当索引>=51时，停止发牌，剩下的作为底牌
        for (int i = 0; i <poker.size() ; i++) {
            String p = poker.get(i);
            if(i>=51){
               // String p = poker.get(i);
                dipai.add(p);
            }else if(i%3 == 0){
                player01.add(p);
            }else if(i%3 == 1){
                player02.add(p);
            }else if(i%3 == 2){
                player03.add(p);
            }
        }

        //看牌
        System.out.println("player01的牌：" + player01);
        System.out.println("player02的牌：" + player02);
        System.out.println("player03的牌：" + player03);
        System.out.println("底牌：" + dipai);

         */
        PokerTest.fapai();
    }
}
