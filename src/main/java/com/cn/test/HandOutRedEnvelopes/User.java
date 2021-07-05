package com.cn.test.HandOutRedEnvelopes;

/**
 * @ClassName: 1
 * @Author: huangzhijuan
 * @Description: TODO
 * @Date: Create in 1 1
 * @Version 1.0
 */
public class User {
    String name;
    int money;

    public User(){

    }

    public User(String name, int money) {
        this.name = name;
        this.money = money;
    }

    //显示姓名，及目前余额
    public void showMoney(){
        System.out.println("我是：" + name + " ，我有：" + money + " 元");
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getMoney() {
        return money;
    }

    public void setMoney(int money) {
        this.money = money;
    }
}
