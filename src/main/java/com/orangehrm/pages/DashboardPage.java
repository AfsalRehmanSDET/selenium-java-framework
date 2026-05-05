package com.orangehrm.pages;

import com.orangehrm.utils.ConfigReader;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class DashboardPage {

    private WebDriver driver;
    private WebDriverWait wait;

    @FindBy(xpath = "//h6[contains(@class,'oxd-text--h6')]")
    private WebElement dashboardTitle;

    @FindBy(xpath = "//span[text()='PIM']")
    private WebElement pimMenu;

    @FindBy(xpath = "//a[text()='Employee List']")
    private WebElement employeeListMenu;

    @FindBy(xpath = "//a[text()='Add Employee']")
    private WebElement addEmployeeMenu;

    @FindBy(
            xpath = "//p[@class='oxd-userdropdown-name']"
    )
    private WebElement loggedInUsername;

    public DashboardPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(
                driver,
                Duration.ofSeconds(ConfigReader.getExplicitWait())
        );
        PageFactory.initElements(driver, this);
    }

    // Verify login was successful
    public boolean isDashboardDisplayed() {
        wait.until(
                ExpectedConditions.visibilityOf(dashboardTitle)
        );
        return dashboardTitle.isDisplayed();
    }

    public String getDashboardTitle() {
        wait.until(
                ExpectedConditions.visibilityOf(dashboardTitle)
        );
        return dashboardTitle.getText();
    }

    public String getLoggedInUsername() {
        wait.until(
                ExpectedConditions.visibilityOf(loggedInUsername)
        );
        return loggedInUsername.getText();
    }

    // Navigate to Add Employee page
    public EmployeePage navigateToAddEmployee() {
        wait.until(
                ExpectedConditions.elementToBeClickable(pimMenu)
        );
        pimMenu.click();

        wait.until(
                ExpectedConditions.elementToBeClickable(addEmployeeMenu)
        );
        addEmployeeMenu.click();

        return new EmployeePage(driver);
    }

    // Navigate to Employee List page
    public EmployeePage navigateToEmployeeList() {
        wait.until(
                ExpectedConditions.elementToBeClickable(pimMenu)
        );
        pimMenu.click();

        wait.until(
                ExpectedConditions.elementToBeClickable(employeeListMenu)
        );
        employeeListMenu.click();

        return new EmployeePage(driver);
    }
}