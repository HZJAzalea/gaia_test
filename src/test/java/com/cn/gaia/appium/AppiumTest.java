package com.cn.gaia.appium;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.net.URL;



public class AppiumTest {

    private AppiumDriver driver;

    @BeforeClass
    public void setup() throws Exception {
        DesiredCapabilities cap = new DesiredCapabilities();
        cap.setCapability(CapabilityType.BROWSER_NAME, "");
        cap.setCapability("platformName", "Android"); //指定测试平台
        cap.setCapability("deviceName", "QMS4C16407000203"); //指定测试机的ID,通过adb命令`adb devices`获取
        cap.setCapability("platformVersion", "6.0.1");

        //将上面获取到的包名和Activity名设置为值
        cap.setCapability("appPackage", "com.inke.gaia");
        cap.setCapability("appActivity", "com.inke.gaia.mainpage.MainActivity");

        //A new session could not be created的解决方法
        //cap.setCapability("appWaitActivity","com.inke.gaia.mainpage.MainActivity");
        //每次启动时覆盖session，否则第二次后运行会报错不能新建session
        cap.setCapability("sessionOverride", true);

        driver = new AndroidDriver(new URL("http://127.0.0.1:4723/wd/hub"), cap);
    }

    @Test
    public void clickTest(){

        //获取1
        //driver.findElementById("com.inke.gaia:id/ad_download_widget_btn").click();
        try {
            Thread.sleep(2000);
            //获取+
            driver.findElementById("com.inke.gaia:id/fab_button").click();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        //获取2
        //driver.findElementById("com.meizu.flyme.calculator:id/digit2").click();
        //获取=
        //driver.findElementById("com.meizu.flyme.calculator:id/eq").click();
        //driver.findelementby


    }

    @AfterClass
    public void tearDown() throws Exception {

        //wait(2000);

        driver.quit();

    }

}
