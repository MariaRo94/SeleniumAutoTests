package utils;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeOptions;

public class ChromeWebDriverFactory{
    public static WebDriver createWebDriver(){
        WebDriverManager.chromedriver().setup();

        ChromeOptions chromeOptions = new ChromeOptions();
        chromeOptions.addArguments("--headless");

        WebDriver chromeDriver = new WebDriver(chromeOptions);

        return chromeDriver;
    }


}
