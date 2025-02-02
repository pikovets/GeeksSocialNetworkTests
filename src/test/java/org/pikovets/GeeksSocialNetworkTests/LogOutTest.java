package org.pikovets.GeeksSocialNetworkTests;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.pikovets.GeeksSocialNetworkTests.pages.HomePage;
import org.pikovets.GeeksSocialNetworkTests.pages.LoginPage;

import java.time.Duration;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class LogOutTest extends BaseTest {
    private HomePage homePage;


    @BeforeEach
    void initPageObjects() {
        homePage = new HomePage(driver);
    }

    @Test
    void testLogOut() {
        driver.get(HomePage.HOME_URL);

        homePage.clickLogoutBtn();

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        Boolean hasLoggedOut = wait.until(ExpectedConditions.urlToBe(LoginPage.LOGIN_URL));
        assertTrue(hasLoggedOut);
    }
}
