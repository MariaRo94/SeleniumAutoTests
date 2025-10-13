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
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

public class WebDriverFactory implements BrowserActions {
    private static final Logger log = LoggerFactory.getLogger(WebDriverFactory.class);
    private static WebDriver driver;
    private static boolean isInitialized = false;
    private static List<String> chromeOptionsList = new ArrayList<>();
    public static void createWebDriver() {
        if (driver != null) {
            log.info("WebDriver is already created");
            return;
        }
        if (!isInitialized) {
            log.info("Setting up ChromeDriver via WebDriverManager");
            WebDriverManager.chromedriver().setup();
            isInitialized = true;
        }

        log.info("Creating new ChromeDriver instance");
        ChromeOptions options = new ChromeOptions();

        for (String option : chromeOptionsList) {
            options.addArguments(option);
        }

        driver = new ChromeDriver(options);
    }

    public static void addChromeOption(String option) {
        if (option != null && !option.trim().isEmpty()) {
            chromeOptionsList.add(option);
            log.info("Chrome option added: {}", option);
        }
    }

    public static void addChromeOptions(List<String> options) {
        if (options != null) {
            for (String option : options) {
                if (option != null && !option.trim().isEmpty()) {
                    chromeOptionsList.add(option);
                    log.info("Chrome option added: {}", option);
                }
            }
        }
    }

    public static void startDriver() {
        if (driver == null) {
            log.info("Starting WebDriver");
            createWebDriver();
        } else {
            log.info("WebDriver is already started");
        }
    }


    @Override
    public WebDriver getDriver() {
        return driver;
    }

    @Override
    public void navigateToUrl(String url) {
        if (driver != null) {
            log.info("Navigating to URL: {}", url);
            driver.get(url);
        } else {
            log.error("navigateToUrl called but driver is null");
        }
    }

    @Override
    public WebElement findElement(By locator) {
        log.info("Finding element: {}", locator);
        return driver.findElement(locator);
    }

    @Override
    public void clickToElement(By locator) {
        waitForVisible(locator, Duration.ofSeconds(10));
        driver.findElement(locator).click();
    }

    @Override
    public void typeText(By locator, String text) {
        waitForVisible(locator, Duration.ofSeconds(5));
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

    public static void quit() {
        if (driver != null) {
            log.info("Quitting WebDriver");
            driver.quit();
            driver = null;
        } else {
            log.info("quit called but driver was already null");
        }
    }

    public void setCookies(String name, String value) {
        log.info("Adding cookie: {}=***", name);
        driver.manage().addCookie(new Cookie(name, value));
    }

    public void setTimeouts(int seconds) {
        log.info("Setting implicit wait to {} seconds", seconds);
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(seconds));
    }

    public void windowMaximize() {
        log.info("Maximizing browser window");
        driver.manage().window().maximize();
    }
}
