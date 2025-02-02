package org.pikovets.GeeksSocialNetworkTests;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.pikovets.GeeksSocialNetworkTests.pages.HomePage;

import java.time.Duration;

import static org.junit.jupiter.api.Assertions.*;
import static org.pikovets.GeeksSocialNetworkTests.pages.HomePage.HOME_URL;

public class AddPostTest extends BaseTest {
    private static final String POST_TEXT = "Test text";
    private static final String TEST_IMAGE_URL = "https://cdn.pixabay.com/photo/2014/06/03/19/38/board-361516_640.jpg";
    private static final String INVALID_TEST_IMAGE_URL = "test-image";

    private static final By WARNING_POST_TEXT = By.className("post-text-validation-container");
    private static final By ERROR_POST_IMAGE_URL = By.className("post-image-url-validation-container");

    private HomePage homePage;

    @BeforeEach
    void initPageObjects() {
        homePage = new HomePage(driver);
    }

    private boolean isElementVisible(By locator) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        try {
            return wait.until(ExpectedConditions.visibilityOfElementLocated(locator)).isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }

    @Test
    void testSendTextPost() {
        driver.get(HOME_URL);
        homePage.enterPostText(POST_TEXT);

        assertFalse(isElementVisible(WARNING_POST_TEXT), "Warning message should not be displayed for valid post text");
    }

    @Test
    void testInvalidShortSendTextPost() {
        driver.get(HOME_URL);
        homePage.enterPostText("");

        assertTrue(isElementVisible(WARNING_POST_TEXT), "Warning message should be displayed for short post text");
    }

    @Test
    void testInvalidLongSendTextPost() {
        driver.get(HOME_URL);
        String longText = "A".repeat(2201);
        homePage.enterPostText(longText);

        assertTrue(isElementVisible(WARNING_POST_TEXT), "Warning message should be displayed for overly long post text");
    }

    @Test
    void testSendImagePost() {
        driver.get(HOME_URL);
        homePage.clickAddPhotoBtn();
        homePage.enterPostImageUrl(TEST_IMAGE_URL);
        homePage.enterPostText(POST_TEXT);

        assertFalse(isElementVisible(ERROR_POST_IMAGE_URL), "Error message should not be displayed for a valid image URL");
    }

    @Test
    void testSendInvalidImagePost() {
        driver.get(HOME_URL);
        homePage.clickAddPhotoBtn();
        homePage.enterPostImageUrl(INVALID_TEST_IMAGE_URL);
        homePage.enterPostText(POST_TEXT);

        assertTrue(isElementVisible(ERROR_POST_IMAGE_URL), "Error message should be displayed for an invalid image URL");
    }
}
