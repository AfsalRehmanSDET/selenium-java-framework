package com.orangehrm.utils;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class ConfigReader
{
    private static Properties properties;

    static {
            try {
                FileInputStream file = new FileInputStream("src/test/resources/config.properties");
                properties = new Properties();
                properties.load(file);
                file.close();
            }  catch (IOException e) {
                throw new RuntimeException(
                        "config/properties file could not be loaded" , e
                );
            }
    }

    public static String getUrl(){
        return properties.getProperty("url");
    }

    public static String getBrowser(){
        return properties.getProperty("browser");
    }

    public static int getImplicitWait(){
        return Integer.parseInt(properties.getProperty("implicit.wait"));
    }

    public static int getExplicitWait(){
        return Integer.parseInt(properties.getProperty("explicit.wait"));
    }

    public static String getDbURL(){
        return properties.getProperty("db.url");
    }

    public static String getDbUsername(){
        return properties.getProperty("db.username");
    }
    public static String getDbPassword(){
        return properties.getProperty("db.password");
    }
}
