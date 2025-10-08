package utils;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.time.Duration;

public interface BrowserActions {
    WebDriver getDriver();

    void navigateToUrl(String url);

    WebElement findElement(By locator);

    void clickToElement(By locator);

    void type(By locator, String text);

    String getText(By locator);

    void waitForVisible(By locator, Duration timeout);

    void quit();
}


