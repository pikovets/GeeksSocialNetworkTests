package org.pikovets.GeeksSocialNetworkTests.utils;

import org.openqa.selenium.WebDriver;
import org.pikovets.GeeksSocialNetworkTests.pages.LoginPage;

public class AuthUtil {
    public static final String EMAIL = "test@gmail.com";
    public static final String PASSWORD = "SqjeiQBeEHlw3Lz";

    private AuthUtil() {
    }

    public static void authenticateUser(WebDriver driver) {
        LoginPage loginPage = new LoginPage(driver);
        driver.get("http://frontend:8080/#/login");
        loginPage.enterEmail(EMAIL);
        loginPage.enterPassword(PASSWORD);
        loginPage.clickLogInBtn();
    }
}
