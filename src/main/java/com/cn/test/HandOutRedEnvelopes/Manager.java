package com.cn.test.HandOutRedEnvelopes;

import java.util.ArrayList;
import java.util.List;

/**
 * @ClassName: Manager
 * @Author: huangzhijuan
 * @Description: TODO
 * @Date: Create in 1 1
 * @Version 1.0
 */
public class Manager extends User {
    public Manager(){

    }

    public Manager(String name, int money) {
        super(name, money);
    }

    public List<Integer> send(int sendTotalMoney,int count){
        //定义一个List，用于存储发送的红包
        List<Integer> redList = new ArrayList<Integer>();
        //先得到账户余额
        int leftMoney = super.getMoney();

        //检查下发送的红包是否大于原来余额，如果大于，则不能发送
        if(sendTotalMoney > leftMoney){
            System.out.println("余额不足");
            return null;
        }

        //账户余额减去发送金额，并更新账户余额
        super.setMoney(leftMoney-sendTotalMoney);

        //将发送的红包分成count份，如果有余数，则加在最后一个红包
        int avgMoney = sendTotalMoney / count;
        int modMoney = sendTotalMoney % count;

        for (int i = 0; i < count-1; i++) {
            redList.add(avgMoney);
        }

        int lastMoney = avgMoney + modMoney;
        redList.add(lastMoney);

        return redList;

    }
}
