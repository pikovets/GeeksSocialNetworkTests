package org.pikovets.GeeksSocialNetworkTests.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class HomePage extends BasePage {
    public static final String HOME_URL = "http://frontend:8080/#/";

    @FindBy(xpath = "//div[@class='logout-btn']")
    private WebElement logoutBtn;

    @FindBy(xpath = "//div[@class='add-post-container']//input[@class='add-post-text-input']")
    private WebElement postTextField;

    @FindBy(xpath = "//div[@class='add-post-container']//div[@class='photo-btn']")
    private WebElement addPhotoBtn;

    @FindBy(xpath = "//div[@class='add-post-container']//input[@class='photo-url-input']")
    private WebElement addPhotoUrlField;

    @FindBy(xpath = "//div[@class='add-post-container']//div[contains(@class, 'send-btn')]")
    public WebElement sendPostBtn;

    public HomePage(WebDriver webDriver) {
        super(webDriver);
    }

    public void clickLogoutBtn() {
        logoutBtn.click();
    }

    public void clickAddPhotoBtn() {
        addPhotoBtn.click();
    }

    public void clickSendPostBtn() {
        sendPostBtn.click();
    }

    public void enterPostText(String text) {
        postTextField.sendKeys(text);
    }

    public void enterPostImageUrl(String url) {
        addPhotoUrlField.sendKeys(url);
    }
}
