package org.pikovets.GeeksSocialNetworkTests.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class RegistrationPage {
    @FindBy(xpath = "//input[@placeholder='Full name']")
    private WebElement fullNameField;

    @FindBy(xpath = "//input[@placeholder='Email']")
    public WebElement emailField;

    @FindBy(xpath = "//input[@placeholder='Password']")
    private WebElement passwordField;

    @FindBy(className = "create-account-btn")
    private WebElement createAccountBtn;

    public RegistrationPage(WebDriver webDriver) {
        PageFactory.initElements(webDriver, this);
    }

    public void enterFullName(String fullName) {
        fullNameField.sendKeys(fullName);
    }

    public void enterEmail(String email) {
        emailField.sendKeys(email);
    }

    public void enterPassword(String password) {
        passwordField.sendKeys(password);
    }

    public void clickCreateAccountButton() {
        createAccountBtn.click();
    }
}