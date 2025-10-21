package classes.utils;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;

public class WebDriverFactory  {
    private static final Logger log = LoggerFactory.getLogger(WebDriverFactory.class);
    private static WebDriver driver;
    private static boolean isInitialized = false;
    private static final List<String> chromeOptionsList = new ArrayList<>();
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

    public static void startDriver() {
        if (driver == null) {
            log.info("Starting WebDriver");
            createWebDriver();
        } else {
            log.info("WebDriver is already started");
        }
    }

    public WebDriver getDriver() {
        return driver;
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
}