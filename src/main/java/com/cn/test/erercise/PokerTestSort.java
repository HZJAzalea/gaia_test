package com.cn.test.erercise;

import com.sun.tools.javac.util.List;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;

/**
 * @ClassName: 1
 * @Author: huangzhijuan
 * @Description: TODO 扑克牌游戏，规则：共54张牌（排数字排序），3个人每人17张（排好序），剩下3张作为底牌（按数字排序）
 * @Date: Create in 1 1
 * @Version 1.0
 */
public class PokerTestSort {
    public static void main(String[] args) {
        //1、准备牌
        //定义一个hashmap用于存放牌的序号和牌本身
        HashMap<Integer,String> poker = new HashMap<>();
        //定义一个list集合存放牌的序号
        ArrayList<Integer> pokerIndex = new ArrayList<>();
        //List.of()分别存放花色和数字
        List<String> colors = List.of("♥️", "♠️", "♦️", "♣️");
        List<String> numbers = List.of("2", "A", "K", "Q", "J", "10", "9", "8", "7", "6", "5", "4", "3");
        //先往poker里存放大小王
        int index = 0;//index作为牌的序号存放在poker集合和pokerIndex集合
        poker.put(index,"大王");
        pokerIndex.add(index);
        index++;
        poker.put(index,"小王");
        pokerIndex.add(index);
        index++;
        for (String number:numbers) {
            for(String color:colors){
                poker.put(index,color+number);
                pokerIndex.add(index);
                index++;
            }
        }
        //System.out.println(poker);
        //System.out.println(pokerIndex);

        //2、洗牌
        Collections.shuffle(pokerIndex);

        //3、发牌
        //先定义4个list集合存放玩家和底牌
        ArrayList<Integer> player01 = new ArrayList<>();
        ArrayList<Integer> player02 = new ArrayList<>();
        ArrayList<Integer> player03 = new ArrayList<>();
        ArrayList<Integer> dipai = new ArrayList<>();
        for(Integer i:pokerIndex){
            Integer in = pokerIndex.get(i);
            if(in >50){
                dipai.add(in);
            }else if(in % 3 == 0){
                player01.add(in);
            }else if(in % 3 == 1){
                player02.add(in);
            }else if(in % 3 ==2){
                player03.add(in);
            }
        }

        //4、排序
        Collections.sort(player01);
        Collections.sort(player02);
        Collections.sort(player03);
        Collections.sort(dipai);

        //5、看牌
        lookPoker("张三",poker,player01);
        lookPoker("李四",poker,player02);
        lookPoker("王五",poker,player03);
        lookPoker("底牌",poker,dipai);
    }

    public static void lookPoker(String name,HashMap<Integer,String> poker,ArrayList<Integer> list){
        System.out.print(name + "：");
        for(Integer key:list){
            String value = poker.get(key);
            System.out.print(value + ",");
        }
        System.out.println();
    }

}
