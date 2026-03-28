package org.ui.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class LoginPage {

    private final WebDriver driver;
    private final WebDriverWait wait;

    private static final String LOGIN_URL      = "https://the-internet.herokuapp.com/login";
    private static final By     USERNAME_FIELD = By.id("username");
    private static final By     PASSWORD_FIELD = By.id("password");
    private static final By     LOGIN_BUTTON   = By.cssSelector("button[type='submit']");
    private static final By     FLASH_MESSAGE  = By.id("flash");

    public LoginPage(WebDriver driver) {
        this.driver = driver;
        this.wait   = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    public void open() {
        driver.get(LOGIN_URL);
        wait.until(ExpectedConditions.visibilityOfElementLocated(USERNAME_FIELD));
    }

    public void enterUsername(String username) {
        WebElement field = wait.until(
                ExpectedConditions.visibilityOfElementLocated(USERNAME_FIELD));
        field.clear();
        field.sendKeys(username);
    }

    public void enterPassword(String password) {
        WebElement field = wait.until(
                ExpectedConditions.visibilityOfElementLocated(PASSWORD_FIELD));
        field.clear();
        field.sendKeys(password);
    }

    public void clickLogin() {
        wait.until(ExpectedConditions.elementToBeClickable(LOGIN_BUTTON)).click();
    }

    public String getFlashMessage() {
        wait.until(ExpectedConditions.visibilityOfElementLocated(FLASH_MESSAGE));
        return driver.findElement(FLASH_MESSAGE).getText();
    }

    public boolean isOnSecurePage() {
        try {
            wait.until(ExpectedConditions.urlContains("/secure"));
            return driver.getCurrentUrl().contains("/secure");
        } catch (Exception e) {
            return false;
        }
    }
}