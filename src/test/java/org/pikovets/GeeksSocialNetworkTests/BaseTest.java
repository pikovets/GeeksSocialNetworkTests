package org.pikovets.GeeksSocialNetworkTests;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.pikovets.GeeksSocialNetworkTests.pages.HomePage;
import org.pikovets.GeeksSocialNetworkTests.utils.AuthUtil;

import java.net.MalformedURLException;
import java.net.URL;
import java.time.Duration;

public abstract class BaseTest {
    protected WebDriver driver;

    @BeforeEach
    void setUp() throws MalformedURLException {
        String seleniumUrl = "http://localhost:4444";

        ChromeOptions options = new ChromeOptions();
        options.addArguments("--no-sandbox");
        options.addArguments("--disable-dev-shm-usage");
        options.addArguments("--disable-web-security");
        options.setCapability("se:name", "Geeks Social Network Test");

        driver = new RemoteWebDriver(new URL(seleniumUrl), options);

        if (this.getClass() != LoginTest.class && this.getClass() != RegisterTest.class) {
            AuthUtil.authenticateUser(driver);

            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
            wait.until(ExpectedConditions.urlToBe(HomePage.HOME_URL));
        }
    }

    @AfterEach
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
}