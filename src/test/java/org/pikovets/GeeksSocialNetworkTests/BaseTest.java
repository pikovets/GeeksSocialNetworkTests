package org.pikovets.GeeksSocialNetworkTests;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.net.MalformedURLException;
import java.net.URL;

public abstract class BaseTest {
    protected WebDriver driver;

    @BeforeEach
    void setUp() throws MalformedURLException {
        String seleniumUrl = "http://localhost:4444";

        ChromeOptions options = new ChromeOptions();
        options.addArguments("--no-sandbox");
        options.addArguments("--disable-dev-shm-usage");
        options.setCapability("se:name", "Geeks Social Network Test");

        driver = new RemoteWebDriver(new URL(seleniumUrl), options);
    }

    @AfterEach
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
}