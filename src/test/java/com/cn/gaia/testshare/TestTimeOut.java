package com.cn.gaia.testshare;

import org.testng.annotations.Test;

/**
 * @ClassName: TestTimeOut
 * @Author: huangzhijuan
 * @Description: TODO
 * @Date: Create in 15:51 2019.09.24
 * @Version 1.0
 */
public class TestTimeOut {

    //设置了2个线程，执行次数5次，5次执行总共耗时不能超过5100毫秒，否则抛出中断异常。
    @Test(invocationCount = 5,invocationTimeOut = 5100,threadPoolSize = 2,successPercentage = 100)
    public void testTimeOut() throws InterruptedException {

        Thread.sleep(1000);
        System.out.println("testTimeOut");
    }
}
