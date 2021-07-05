package com.cn.test.HandOutRedEnvelopes;

import java.util.List;
import java.util.Random;

/**
 * @ClassName: Members
 * @Author: huangzhijuan
 * @Description: TODO
 * @Date: Create in 1 1
 * @Version 1.0
 */
public class Members extends User {
    public Members(){

    }

    public Members(String name,int money){
        super(name, money);
    }

    public void receive(List<Integer> list){

        //从红包列表中随机抽取一个，给我自己

        //随机获取一个红包集合中的索引编号
        int index = new Random().nextInt(list.size());
        //根据索引，从红包列表移除抽取的红包
        int delta = list.remove(index);
        //查询一下成员原有账户余额
        int money = super.getMoney();
        //将抽取的红包，加到成员账户
        super.setMoney(money + delta);
    }
}
