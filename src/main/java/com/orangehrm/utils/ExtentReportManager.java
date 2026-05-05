package com.orangehrm.utils;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

public class ExtentReportManager {

    private static ExtentReports extent;

    private static ThreadLocal<ExtentTest> test =
            new ThreadLocal<>();

    public static ExtentReports getInstance() {
        if (extent == null) {
            createInstance();
        }
        return extent;
    }

    public static void setup() {
        ExtentSparkReporter spark = new ExtentSparkReporter("extent-report.html");
        extent = new ExtentReports();
        extent.attachReporter(spark);
    }

    private static void createInstance() {
        ExtentSparkReporter sparkReporter =
                new ExtentSparkReporter(
                        "reports/ExtentReport.html"
                );

        sparkReporter.config().setTheme(Theme.DARK);
        sparkReporter.config().setDocumentTitle(
                "OrangeHRM Automation Report"
        );
        sparkReporter.config().setReportName(
                "Selenium Test Results"
        );
        sparkReporter.config().setTimeStampFormat(
                "dd/MM/yyyy HH:mm:ss"
        );

        extent = new ExtentReports();
        extent.attachReporter(sparkReporter);

        extent.setSystemInfo(
                "Project", "OrangeHRM Automation"
        );
        extent.setSystemInfo(
                "Author", "AfsalRehmanSDET"
        );
        extent.setSystemInfo("Environment", "QA");
        extent.setSystemInfo(
                "Browser", ConfigReader.getBrowser()
        );
    }

    public static ExtentTest createTest(String testName) {
        ExtentTest extentTest =
                getInstance().createTest(testName);
        test.set(extentTest);
        return extentTest;
    }

    public static ExtentTest getTest() {
        return test.get();
    }

    public static void flushReports() {
        if (extent != null) {
            extent.flush();
        }
    }
}