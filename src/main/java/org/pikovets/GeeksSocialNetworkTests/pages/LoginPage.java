package org.pikovets.GeeksSocialNetworkTests.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class LoginPage extends BasePage {
    public static final String LOGIN_URL = "http://frontend:8080/#/login";

    @FindBy(xpath = "//a[@href='/signup']")
    private WebElement signUpHref;

    @FindBy(xpath = "//input[@placeholder='Email']")
    private WebElement emailField;

    @FindBy(xpath = "//input[@placeholder='Password']")
    private WebElement passwordField;

    @FindBy(className = "log-in-btn")
    private WebElement logInBtn;

    public LoginPage(WebDriver webDriver) {
        super(webDriver);
    }

    public void enterEmail(String email) {
        emailField.sendKeys(email);
    }

    public void enterPassword(String password) {
        passwordField.sendKeys(password);
    }

    public void clickLogInBtn() {
        logInBtn.click();
    }

    public void clickSignUpHref() {
        signUpHref.click();
    }
}
