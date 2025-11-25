package classes.utils;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;

public class WebDriverFactory {
    private static final Logger log = LoggerFactory.getLogger(WebDriverFactory.class);
    private WebDriver driver;
    private boolean isChromeInitialized = false;
    private static final List<String> chromeOptionsList = new ArrayList<>();
    private BrowserType currentBrowserType;


    public void createWebDriver(BrowserType browserType) {
        if (driver != null) {
            log.info("WebDriver is already created for browser: {}", currentBrowserType);
            return;
        }

        log.info("Creating new WebDriver instance for browser: {}", browserType);
        switch (browserType) {
            case CHROME:
                createChromeDriver();
                break;
//                case SAFARI:  Для примера использования фабрики, в случае если добавим еще и другой браузер
//                    createSafariDriver();
//                    break;
            default:
                throw new IllegalArgumentException("Unsupported browser type: " + browserType);
        }

        this.currentBrowserType = browserType;
    }

    private void createChromeDriver() {
        if (!isChromeInitialized) {
            log.info("Setting up ChromeDriver via WebDriverManager");
            WebDriverManager.chromedriver().setup();
            isChromeInitialized = true;
        }

        ChromeOptions options = new ChromeOptions();

        for (String option : chromeOptionsList) {
            options.addArguments(option);
        }

        options.addArguments("--start-maximized");

        driver = new ChromeDriver(options);
        log.info("ChromeDriver created successfully with headless mode: {}", false);
    }


    public void addChromeOption(String option) {
        if (option != null && !option.trim().isEmpty()) {
            chromeOptionsList.add(option);
            log.info("Chrome option added: {}", option);
        }
    }


    public void startDriver() {
        String browserName = System.getProperty("browser", "CHROME");
        startDriver(browserName);
    }

    public void startDriver(String browserName) {
        if (driver == null) {
            log.info("Starting WebDriver for browser: {}", browserName);
            createWebDriver(BrowserType.valueOf(browserName));
        } else {
            log.info("WebDriver is already started for browser: {}", currentBrowserType);
        }
    }

    public WebDriver getDriver() {
        if (driver == null) {
            log.warn("WebDriver is null. Call startDriver() first.");
        }
        return driver;
    }


    public void quit() {
        if (driver != null) {
            log.info("Quitting WebDriver for browser: {}", currentBrowserType);
            driver.quit();
            driver = null;
            currentBrowserType = null;
        } else {
            log.info("quit called but driver was already null");
        }
    }


    public static boolean isBrowserSupported(String browserName) {
        try {
            BrowserType.valueOf(browserName.toUpperCase());
            return true;
        } catch (IllegalArgumentException e) {
            return false;
        }
    }
}
