package org.pikovets.GeeksSocialNetworkTests;

import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

import static org.junit.jupiter.api.Assertions.assertEquals;

class RegisterTest extends BaseTest {

    private static final String BASE_URL = "http://frontend:8080";
    private static final String FULL_NAME = "Test test";
    private static final String EMAIL = "test@gmail.com";
    private static final String PASSWORD = "SqjeiQBeEHlw3Lz";

    @Test
    void testUserRegister() {
        driver.get(BASE_URL);
        driver.findElement(By.xpath("//a[contains(text(), 'Sign Up')]")).click();

        driver.findElement(By.xpath("//input[@placeholder='Full name']")).sendKeys(FULL_NAME);
        driver.findElement(By.xpath("//input[@placeholder='Email']")).sendKeys(EMAIL);
        driver.findElement(By.xpath("//input[@placeholder='Password']")).sendKeys(PASSWORD);
        driver.findElement(By.className("create-account-btn")).click();

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        Boolean isRegistered = wait.until(ExpectedConditions.visibilityOfElementLocated(By.className("success-message-container"))).isDisplayed();
        assertEquals(true, isRegistered);
    }
}