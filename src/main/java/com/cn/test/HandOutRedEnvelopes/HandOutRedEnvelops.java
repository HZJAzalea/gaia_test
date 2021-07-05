package com.cn.test.HandOutRedEnvelopes;

import java.util.List;

/**
 * @ClassName: HandOutRedEnvelops
 * @Author: huangzhijuan
 * @Description: TODO 发红包案例
 * @Date: Create in 1 1
 * @Version 1.0
 */


/*
发红包案例
 */
public class HandOutRedEnvelops {
    public static void main(String[] args) {
        Manager manager = new Manager("群主",100);
        manager.showMoney();

        Members members1 = new Members("成员A",0);
        Members members2 = new Members("成员B",0);
        Members members3 = new Members("成员C",0);

        members1.showMoney();
        members2.showMoney();
        members3.showMoney();

        System.out.println("==========================");

        List<Integer> list = manager.send(20,3);
        members1.receive(list);
        members2.receive(list);
        members3.receive(list);

        manager.showMoney();

        members1.showMoney();
        members2.showMoney();
        members3.showMoney();

    }
}
