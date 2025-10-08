package utils;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class ChromeWebDriverFactory implements utils.BrowserActions {
    private final WebDriver driver;

    public ChromeWebDriverFactory() {
        this(true);
    }

    public ChromeWebDriverFactory(boolean headless) {
        WebDriverManager.chromedriver().setup();
        ChromeOptions chromeOptions = new ChromeOptions();
        if (headless) {
            chromeOptions.addArguments("--headless=new");
        }
        this.driver = new ChromeDriver(chromeOptions);
    }

    public static WebDriver createWebDriver() {
        WebDriverManager.chromedriver().setup();
        ChromeOptions chromeOptions = new ChromeOptions();
        chromeOptions.addArguments("--headless=new");
        return new ChromeDriver(chromeOptions);
    }

    @Override
    public WebDriver getDriver() {
        return driver;
    }

    public void navigateToUrl(String url) {
        driver.navigate().to(url);
    }

    public WebElement findElement(By locator) {
        return driver.findElement(locator);
    }

    public void clickToElement(By locator) {
        findElement(locator).click();
    }

    public void typeText(By locator, String text) {
        WebElement element = findElement(locator);
        element.clear();
        element.sendKeys(text);
    }

    public String getText(By locator) {
        return findElement(locator).getText();
    }

    public void waitForVisible(By locator, Duration timeout) {
        new WebDriverWait(driver, timeout)
                .until(ExpectedConditions.visibilityOfElementLocated(locator));
    }


    public void quit() {
        if (driver != null) {
            driver.quit();
        }
    }
}
