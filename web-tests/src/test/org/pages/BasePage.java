package org.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import org.utils.WebDriverFactory;

public abstract class BasePage {
    protected final WebDriver driver;

    protected BasePage(WebDriverFactory driverFactory) {
        WebDriver existing = driverFactory.getDriver();
        if (existing == null) {
            WebDriverFactory.startDriver();
            existing = driverFactory.getDriver();
        }
        this.driver = existing;
        PageFactory.initElements(this.driver, this);
    }
}


