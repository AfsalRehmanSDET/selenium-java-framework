package com.orangehrm.base;

import com.orangehrm.utils.ConfigReader;
import com.orangehrm.utils.ExtentReportManager;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import java.lang.reflect.Method;

import java.time.Duration;



public class BaseTest
{
    protected WebDriver driver;

    @BeforeMethod
    public void setUp(Method method){;

        DriverFactory.initDriver(ConfigReader.getBrowser());
        driver = DriverFactory.getDriver();

        driver.manage().timeouts()
                .implicitlyWait(
                        Duration.ofSeconds(ConfigReader.getImplicitWait())
                );

        driver.get(ConfigReader.getUrl());
    }

    @AfterMethod
    public void tearDown(){
        DriverFactory.quitDriver();
    }
}


