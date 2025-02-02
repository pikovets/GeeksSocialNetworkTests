package org.pikovets.GeeksSocialNetworkTests;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.pikovets.GeeksSocialNetworkTests.pages.RegistrationPage;

import java.time.Duration;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.pikovets.GeeksSocialNetworkTests.utils.DatabaseUtil.deleteUserByEmail;


class RegisterTest extends BaseTest {

    private static final String REGISTRATION_URL = "http://frontend:8080/#/signup/";
    private static final String FULL_NAME = "Test test";
    private static final String EMAIL = "test@gmail.com";
    private static final String PASSWORD = "SqjeiQBeEHlw3Lz";

    private static final String INVALID_EMAIL = "test";
    private static final String INVALID_PASSWORD = "qwerty";


    private RegistrationPage registrationPage;

    @BeforeEach
    void initPageObjects() {
        registrationPage = new RegistrationPage(driver);
    }

    @Test
    void testSuccessfulUserRegistration() {
        driver.get(REGISTRATION_URL);
        deleteUserByEmail(EMAIL);

        registrationPage.enterFullName(FULL_NAME);
        registrationPage.enterEmail(EMAIL);
        registrationPage.enterPassword(PASSWORD);

        registrationPage.clickCreateAccountButton();

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        Boolean isRegistered = wait.until(ExpectedConditions.visibilityOfElementLocated(By.className("success-message-container"))).isDisplayed();
        assertEquals(true, isRegistered);
    }

    @Test
    void testInvalidEmailRegistration() {
        driver.get(REGISTRATION_URL);

        registrationPage.enterFullName(FULL_NAME);
        registrationPage.enterEmail(INVALID_EMAIL);
        registrationPage.enterPassword(PASSWORD);

        registrationPage.clickCreateAccountButton();

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        Boolean hasFailed = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@class='container email']/p[@id='validation-error-msg']"))).isDisplayed();
        assertEquals(true, hasFailed);
    }

    @Test
    void testEmptyEmailRegistration() {
        driver.get(REGISTRATION_URL);

        registrationPage.enterFullName(FULL_NAME);
        registrationPage.enterEmail("");
        registrationPage.enterPassword(PASSWORD);

        registrationPage.clickCreateAccountButton();

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        Boolean isInvalid = wait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath("//div[@class='container email']/p[@class='valid-field']")));
        assertEquals(true, isInvalid);
    }

    @Test
    void testInvalidPasswordRegistration() {
        driver.get(REGISTRATION_URL);

        registrationPage.enterFullName(FULL_NAME);
        registrationPage.enterEmail(EMAIL);
        registrationPage.enterPassword(INVALID_PASSWORD);

        registrationPage.clickCreateAccountButton();

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        Boolean hasFailed = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@class='container password']/p[@id='validation-error-msg']"))).isDisplayed();
        assertEquals(true, hasFailed);
    }

    @Test
    void testEmptyPasswordRegistration() {
        driver.get(REGISTRATION_URL);

        registrationPage.enterFullName(FULL_NAME);
        registrationPage.enterEmail(EMAIL);
        registrationPage.enterPassword("");

        registrationPage.clickCreateAccountButton();

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        Boolean isInvalid = wait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath("//div[@class='container password']/p[@class='valid-field']")));
        assertEquals(true, isInvalid);
    }

    @Test
    void testAlreadyExistedAccountRegistration() {
        driver.get(REGISTRATION_URL);

        registrationPage.enterFullName(FULL_NAME);
        registrationPage.enterEmail(INVALID_EMAIL);
        registrationPage.enterPassword(PASSWORD);

        registrationPage.clickCreateAccountButton();

        driver.navigate().refresh();

        registrationPage.enterFullName(FULL_NAME);
        registrationPage.enterEmail(EMAIL);
        registrationPage.enterPassword(PASSWORD);

        registrationPage.clickCreateAccountButton();

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        Boolean hasFailed = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@class='container email']/p[@id='backend-error-msg']"))).isDisplayed();
        assertEquals(true, hasFailed);
    }
}