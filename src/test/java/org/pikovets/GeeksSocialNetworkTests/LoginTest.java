package org.pikovets.GeeksSocialNetworkTests;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.pikovets.GeeksSocialNetworkTests.pages.LoginPage;

import java.time.Duration;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.pikovets.GeeksSocialNetworkTests.utils.AuthUtil.EMAIL;
import static org.pikovets.GeeksSocialNetworkTests.utils.AuthUtil.PASSWORD;

class LoginTest extends BaseTest {

    private static final String LOGIN_URL = "http://frontend:8080/#/login/";

    private static final String INVALID_EMAIL = "test";
    private static final String INVALID_PASSWORD = "qwerty";
    private static final String WRONG_PASSWORD = "qwertyQ1";

    private LoginPage loginPage;

    @BeforeEach
    void initPageObjects() {
        loginPage = new LoginPage(driver);
    }

    @Test
    void testSuccessfulUserLogin() {
        driver.get(LOGIN_URL);

        loginPage.enterEmail(EMAIL);
        loginPage.enterPassword(PASSWORD);
        loginPage.clickLogInBtn();

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.urlToBe(LOGIN_URL));
        assertEquals(LOGIN_URL, driver.getCurrentUrl());
    }

    @Test
    void testInvalidEmailLogin() {
        driver.get(LOGIN_URL);

        loginPage.enterEmail(INVALID_EMAIL);
        loginPage.enterPassword(PASSWORD);
        loginPage.clickLogInBtn();

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        Boolean hasFailed = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@class='container email']/p[@id='validation-error-msg']"))).isDisplayed();
        assertEquals(true, hasFailed);
    }

    @Test
    void testInvalidPasswordLogin() {
        driver.get(LOGIN_URL);

        loginPage.enterEmail(EMAIL);
        loginPage.enterPassword(INVALID_PASSWORD);
        loginPage.clickLogInBtn();

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        Boolean hasFailed = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@class='container password']/p[@id='validation-error-msg']"))).isDisplayed();
        assertEquals(true, hasFailed);
    }

    @Test
    void testWrongPasswordLogin() {
        driver.get(LOGIN_URL);

        loginPage.enterEmail(EMAIL);
        loginPage.enterPassword(WRONG_PASSWORD);
        loginPage.clickLogInBtn();

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(100));
        Boolean hasFailed = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@class='container email']/p[@id='backend-error-msg']"))).isDisplayed();
        assertEquals(true, hasFailed);
    }
}