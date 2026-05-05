package com.orangehrm.pages;

import com.orangehrm.utils.ConfigReader;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class LoginPage {

    private WebDriver driver;
    private WebDriverWait wait;

    @FindBy(name = "username")
    private WebElement usernameField;

    @FindBy(name = "password")
    private WebElement passwordField;

    @FindBy(xpath = "//button[@type='submit']")
    private WebElement loginButton;

    @FindBy(xpath = "//span[normalize-space()='Required']")
    private WebElement errorMessageEmpty;

    @FindBy(xpath = "//p[normalize-space()='Invalid credentials']")
    private WebElement errorMessageInvalid;

    public LoginPage(WebDriver driver){
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(ConfigReader.getExplicitWait()));
    PageFactory.initElements(driver, this);
    }

    public void enterUsername(String username){
        wait.until(ExpectedConditions.visibilityOf(usernameField));
        usernameField.clear();
        usernameField.sendKeys(username);
    }

    public void enterPassword(String password){
        wait.until(ExpectedConditions.visibilityOf(passwordField));
        passwordField.clear();
        passwordField.sendKeys(password);
    }

    public void clickLoginButton(){
        wait.until(ExpectedConditions.elementToBeClickable(loginButton));
        loginButton.click();
    }

    public DashboardPage loginAs(String username,String password){
        enterUsername(username);
        enterPassword(password);
        clickLoginButton();
        return new DashboardPage(driver);
    }

    public  String getErrorMessage(){
        wait.until(ExpectedConditions.visibilityOf(errorMessageEmpty));
        return errorMessageEmpty.getText();
    }

    public String getInvalidCredentialMessage(){
        wait.until(ExpectedConditions.visibilityOf(errorMessageInvalid));
        return errorMessageInvalid.getText();
    }

    public boolean isLoginPageDisplayed(){
        return loginButton.isDisplayed();
    }





}

