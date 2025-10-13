package org.utils;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.time.Duration;

public interface BrowserActions {
    public WebDriver getDriver();

    public void navigateToUrl(String url);

    WebElement findElement(By locator);

    public void clickToElement(By locator);

    public void typeText(By locator, String text);

    String getText(By locator);

    void waitForVisible(By locator, Duration timeout);

    public static void quit() {};

    public static void startDriver() {};

    public static void createWebDriver() {};
}


