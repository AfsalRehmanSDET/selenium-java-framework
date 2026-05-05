package com.orangehrm.pages;

import com.orangehrm.utils.ConfigReader;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class EmployeePage {

    private WebDriver driver;
    private WebDriverWait wait;

    // Add Employee form fields
    @FindBy(xpath = "//input[@name='firstName']")
    private WebElement firstNameField;

    @FindBy(xpath = "//input[@name='middleName']")
    private WebElement middleNameField;

    @FindBy(xpath = "//input[@name='lastName']")
    private WebElement lastNameField;

    @FindBy(
            xpath = "//div[@class='orangehrm-employee-container']" +
                    "//input[@class='oxd-input oxd-input--active']"
    )
    private WebElement employeeIdField;

    @FindBy(
            xpath = "//button[normalize-space()='Save']"
    )
    private WebElement saveButton;

    @FindBy(
            xpath = "//button[normalize-space()='Cancel']"
    )
    private WebElement cancelButton;

    // Employee List search fields
    @FindBy(
            xpath = "(//input[@class='oxd-input oxd-input--active'])[2]"
    )
    private WebElement searchEmployeeNameField;

    @FindBy(
            xpath = "//button[normalize-space()='Search']"
    )
    private WebElement searchButton;

    @FindBy(
            xpath = "//span[@class='oxd-text oxd-text--span']"
    )
    private WebElement recordsFoundText;

    @FindBy(
            xpath = "//div[@class='oxd-table-body']" +
                    "//div[@role='row'][1]//div[@role='cell'][3]"
    )
    private WebElement firstResultName;

    // Success toast message after saving
    @FindBy(
            xpath = "//div[@id='oxd-toaster_1']"
    )
    private WebElement successToast;

    public EmployeePage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(
                driver,
                Duration.ofSeconds(ConfigReader.getExplicitWait())
        );
        PageFactory.initElements(driver, this);
    }

    public void enterFirstName(String firstName) {
        wait.until(
                ExpectedConditions.visibilityOf(firstNameField)
        );
        firstNameField.clear();
        firstNameField.sendKeys(firstName);
    }

    public void enterMiddleName(String middleName) {
        middleNameField.clear();
        middleNameField.sendKeys(middleName);
    }

    public void enterLastName(String lastName) {
        lastNameField.clear();
        lastNameField.sendKeys(lastName);
    }

    public void enterEmployeeId(String employeeId) {
        employeeIdField.clear();
        employeeIdField.sendKeys(employeeId);
    }

    public void clickSave() {
        wait.until(
                ExpectedConditions.elementToBeClickable(saveButton)
        );
        saveButton.click();
    }

    // Single method to fill and save entire form
    public void addEmployee(
            String firstName,
            String middleName,
            String lastName,
            String employeeId
    ) {
        enterFirstName(firstName);
        enterMiddleName(middleName);
        enterLastName(lastName);
        enterEmployeeId(employeeId);
        clickSave();
    }

    public boolean isSuccessToastDisplayed() {
        wait.until(
                ExpectedConditions.visibilityOf(successToast)
        );
        return successToast.isDisplayed();
    }

    // Search employee by name in Employee List
    public void searchEmployeeByName(String name) {
        wait.until(
                ExpectedConditions
                        .visibilityOf(searchEmployeeNameField)
        );
        searchEmployeeNameField.clear();
        searchEmployeeNameField.sendKeys(name);
        searchButton.click();
    }

    public String getRecordsFoundText() {
        wait.until(
                ExpectedConditions.visibilityOf(recordsFoundText)
        );
        return recordsFoundText.getText();
    }

    public String getFirstResultName() {
        wait.until(
                ExpectedConditions.visibilityOf(firstResultName)
        );
        return firstResultName.getText();
    }

    public boolean isEmployeeFound(String employeeName) {
        searchEmployeeByName(employeeName);
        return getFirstResultName()
                .contains(employeeName);
    }
}