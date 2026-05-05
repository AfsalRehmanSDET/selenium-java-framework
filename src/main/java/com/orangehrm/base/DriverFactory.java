package com.orangehrm.base;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;

public class DriverFactory {

    private static ThreadLocal<WebDriver> driver = new ThreadLocal<>();

    public static WebDriver getDriver(){
        return driver.get();
    }

    public static void initDriver(String browser){
        if(browser.equalsIgnoreCase("chrome")){
            ChromeOptions options = new ChromeOptions();
            options.addArguments("--start-maximized");
            driver.set(new ChromeDriver(options));
        }

        else if(browser.equalsIgnoreCase("firefox")){
            driver.set(new FirefoxDriver());
        }

        else {
            throw new IllegalArgumentException(
                    "Browser Type Not Supported" + browser
            );
        }
    }

    public static void quitDriver(){
        if(driver.get() != null){
            driver.get().quit();
            driver.remove();
        }
    }
}
