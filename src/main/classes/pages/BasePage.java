package classes.pages;

import classes.utils.WebDriverFactory;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public abstract class BasePage {
    protected final WebDriver driver;

    BasePage(WebDriverFactory driverFactory) {
        WebDriver existing = driverFactory.getDriver();
        if (existing == null) {
            WebDriverFactory.startDriver();
            existing = driverFactory.getDriver();
        }
        this.driver = existing;
        PageFactory.initElements(this.driver, this);
    }

    void explicityWaitElement(WebElement element) {
        new WebDriverWait(driver, Duration.ofSeconds(5))
                .until(ExpectedConditions.visibilityOf(element));
    }

    void pageBeLoaded() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        wait.until(webDriver ->
                "complete".equals(((JavascriptExecutor) webDriver)
                        .executeScript("return document.readyState"))
        );
    }
}


