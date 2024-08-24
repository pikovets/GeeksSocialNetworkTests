package org.pikovets.GeeksSocialNetworkTests;

import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

import static org.junit.jupiter.api.Assertions.assertEquals;

class LoginTest extends BaseTest {

    private static final String BASE_URL = "http://frontend:8080/";
    private static final String EMAIL = "test@gmail.com";
    private static final String PASSWORD = "SqjeiQBeEHlw3Lz";

    @Test
    void testUserRegister() {
        driver.get(BASE_URL);

        driver.findElement(By.xpath("//input[@placeholder='Email']")).sendKeys(EMAIL);
        driver.findElement(By.xpath("//input[@placeholder='Password']")).sendKeys(PASSWORD);
        driver.findElement(By.className("log-in-btn")).click();

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.urlToBe(BASE_URL));
        assertEquals(BASE_URL, driver.getCurrentUrl());
    }
}