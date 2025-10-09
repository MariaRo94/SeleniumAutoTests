package org.utils;

import org.openqa.selenium.By;
import org.openqa.selenium.Cookie;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import io.github.bonigarcia.wdm.WebDriverManager;

import java.time.Duration;

public class ChromeWebDriverFactory {
    private static WebDriver driver;

    public static void createWebDriver() {
        WebDriverManager.chromedriver().setup();
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--start-maximized");
        driver = new ChromeDriver(options);
    }

    public static void startDriver(){
        driver = new ChromeDriver();
    }

    public void navigateToUrl(String url) {
        if (driver != null) {
            driver.get(url);
        }
    }

    public WebElement findElement(By locator) {
        return driver.findElement(locator);
    }

    public void quit() {
        if (driver != null) {
            driver.quit();
            driver = null;
        }
    }

    public void setCookies(String name, String value){
        driver.manage().addCookie(new Cookie(name, value));
    }

    public void setTimeouts(int seconds){
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(seconds));
    }

public void windowMaximize(){
        driver.manage().window().maximize();
}
}