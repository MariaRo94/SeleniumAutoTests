package org.utils;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.Cookie;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class ChromeWebDriverFactory implements BrowserActions {
    private static WebDriver driver;
    private static boolean isInitialized = false;

    public static void createWebDriver() {
        if (driver != null) {
            return;
        }
        if (!isInitialized) {
            WebDriverManager.chromedriver().setup();
            isInitialized = true;
        }
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--start-maximized");
        driver = new ChromeDriver(options);
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
    }

    public static void startDriver() {
        if (driver == null) {
            createWebDriver();
        }
    }

    @Override
    public WebDriver getDriver() {
        return driver;
    }

    @Override
    public void navigateToUrl(String url) {
        if (driver != null) {
            driver.get(url);
        }
    }

    @Override
    public WebElement findElement(By locator) {
        return driver.findElement(locator);
    }

    @Override
    public void clickToElement(By locator) {
        waitForVisible(locator, Duration.ofSeconds(10));
        driver.findElement(locator).click();
    }

    @Override
    public void typeText(By locator, String text) {
        waitForVisible(locator, Duration.ofSeconds(10));
        WebElement element = driver.findElement(locator);
        element.clear();
        element.sendKeys(text);
    }

    @Override
    public String getText(By locator) {
        waitForVisible(locator, Duration.ofSeconds(10));
        return driver.findElement(locator).getText();
    }

    @Override
    public void waitForVisible(By locator, Duration timeout) {
        new WebDriverWait(driver, timeout)
                .until(ExpectedConditions.visibilityOfElementLocated(locator));
    }

    public void quit() {
        if (driver != null) {
            driver.quit();
            driver = null;
        }
    }

    public void setCookies(String name, String value) {
        driver.manage().addCookie(new Cookie(name, value));
    }

    public void setTimeouts(int seconds) {
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(seconds));
    }

    public void windowMaximize() {
        driver.manage().window().maximize();
    }
}