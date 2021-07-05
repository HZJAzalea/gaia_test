package com.cn.gaia.testshare;

import org.testng.annotations.Test;

/**
 * @ClassName: TestGroups
 * @Author: huangzhijuan
 * @Description: TODO
 * @Date: Create in 14:45 2019.09.24
 * @Version 1.0
 */
public class TestGroups {
    /*

    @Test(groups = "info")
    public void testGroup3(){
        System.out.println("testGroup3");
    }

    @Test(groups = {"init"})
    public void testGroup1(){
        System.out.println("testGroup1");
    }

    @Test(groups = {"init"})
    public void testGroup2(){
        System.out.println("testGroup2");
    }


    @Test(dependsOnGroups = {"init.*","info"})
    public void testDependsOnGroups(){
        System.out.println("testDependsOnGroups");
    }
    */



    @Test()
    public void testGroup3(){
        System.out.println("testGroup3");
    }

    @Test(enabled = false)
    public void testGroup1(){
        System.out.println("testGroup1");
    }

    @Test()
    public void testGroup2(){
        System.out.println("testGroup2");
    }


    @Test(dependsOnMethods = {"testGroup3"})
    public void testDependsOnMethods(){
        System.out.println("testDependsOnMethods");
    }





    /*
    @Test(priority = 2)
    public void testGroup3(){
        System.out.println("testGroup3");
    }

    @Test(priority = 3)
    public void testGroup1(){
        System.out.println("testGroup1");
    }

    @Test(priority = 1)
    public void testGroup2(){
        System.out.println("testGroup2");
    }

     */

}
