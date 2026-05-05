package com.orangehrm.test;
import java.io.IOException;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import com.orangehrm.base.BaseTest;
import com.orangehrm.pages.DashboardPage;
import com.orangehrm.pages.LoginPage;
import com.orangehrm.utils.ExcelUtils;

public class DataDrivenLoginTest extends BaseTest {

    private static final String EXCEL_FILE_PATH = "src/test/resources/testdata/LoginData.xlsx";

    @DataProvider(name = "loginData")
    public Object[][] getLoginData() throws IOException {

        ExcelUtils excel = new ExcelUtils(EXCEL_FILE_PATH, "Sheet1");
        int rows = excel.getRowCount();
        int cols = excel.getColumnCount();

        Object[][] data = new Object[rows][cols];

        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                data[i][j] = excel.getCellData(i, j);
            }
        }

        excel.close();
        return data;

    }

    @Test(dataProvider = "loginData")
    public void testLoginWithMultipleCredentials(
            String username,
            String password,
            String expectedResult) {
        LoginPage loginPage = new LoginPage(driver);
        loginPage.enterUsername(username);
        loginPage.enterPassword(password);
        loginPage.clickLoginButton();
        if (expectedResult.equalsIgnoreCase("success")) {
            DashboardPage dashboardPage = new DashboardPage(driver);
            Assert.assertTrue(dashboardPage.isDashboardDisplayed(),
                    "Log In should succeed for user" + username);
        } else if (expectedResult.equalsIgnoreCase("failure") && !username.isEmpty() && !password.isEmpty()) {
            String errorMessage = loginPage.getInvalidCredentialMessage();
            Assert.assertTrue(errorMessage.contains("Invalid credentials"),
                    "Error message should indicate invalid credentials for user" + username);
        } else if (expectedResult.equalsIgnoreCase("failure") && (username.isEmpty() || password.isEmpty())) {
            String errorMessage = loginPage.getErrorMessage();
            Assert.assertTrue(errorMessage.contains("Required"),
                    "Error message should indicate required fields for user" + username);
        }
    }

}
