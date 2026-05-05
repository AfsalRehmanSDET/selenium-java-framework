package com.orangehrm.test;

import com.orangehrm.base.BaseTest;
import com.orangehrm.pages.DashboardPage;
import com.orangehrm.pages.LoginPage;
import com.orangehrm.utils.ExtentReportManager;
import org.testng.Assert;
import org.testng.annotations.Test;
import org.testng.annotations.Listeners;
import com.orangehrm.utils.TestListener;

@Listeners(TestListener.class)
public class LogInTest extends BaseTest {

    @Test(
            description = "Verify successful login with valid credentials"
    )
    public void testValidLogin() {
        ExtentReportManager.getTest()
                .info("Navigating to login page");

        LoginPage loginPage = new LoginPage(driver);

        ExtentReportManager.getTest()
                .info("Entering valid credentials");

        DashboardPage dashboardPage = loginPage.loginAs(
                "Admin",
                "admin123"
        );

        ExtentReportManager.getTest()
                .info("Verifying dashboard is displayed");

        Assert.assertTrue(
                dashboardPage.isDashboardDisplayed(),
                "Dashboard should be displayed after valid login"
        );

        ExtentReportManager.getTest()
                .pass("Valid login test passed successfully");
    }

    @Test(
            description = "Verify error message with invalid credentials"
    )
    public void testInvalidLogin() {
        ExtentReportManager.getTest()
                .info("Entering invalid credentials");

        LoginPage loginPage = new LoginPage(driver);

        loginPage.enterUsername("wronguser");
        loginPage.enterPassword("wrongpass");
        loginPage.clickLoginButton();

        ExtentReportManager.getTest()
                .info("Verifying error message appears");

        Assert.assertEquals(
                loginPage.getInvalidCredentialMessage(),
                "Invalid credentials",
                "Error message should appear for invalid login"
        );
    }

    @Test(
            description = "Verify error with empty username"
    )
    public void testEmptyUsername() {
        ExtentReportManager.getTest()
                .info("Submitting form with empty username");

        LoginPage loginPage = new LoginPage(driver);

        loginPage.enterUsername("");
        loginPage.enterPassword("admin123");
        loginPage.clickLoginButton();

        Assert.assertEquals(
                loginPage.getErrorMessage(),
                "Required",
                "Required validation should appear"
        );
    }

    @Test(
            description = "Verify error with empty password"
    )
    public void testEmptyPassword() {
        ExtentReportManager.getTest()
                .info("Submitting form with empty password");

        LoginPage loginPage = new LoginPage(driver);

        loginPage.enterUsername("Admin");
        loginPage.enterPassword(" ");
        loginPage.clickLoginButton();

        Assert.assertEquals(
                loginPage.getErrorMessage(),
                "Required",
                "Required validation should appear"
        );
    }
}