package org.uitests;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.utils.ChromeWebDriverFactory;

public class BasicTests {
    ChromeWebDriverFactory chromeWebDriver = new ChromeWebDriverFactory();

    @BeforeAll
    static void setup(){
        ChromeWebDriverFactory.createWebDriver();
    }

    @BeforeEach
    void startDriver(){
        ChromeWebDriverFactory.startDriver();
    }

    @AfterEach
    void tearDown(){
        chromeWebDriver.quit();
    }


    @Test
    void testChrome() throws InterruptedException {
        chromeWebDriver.navigateToUrl("https://otus.home.kartushin.su/training.html");
        WebElement title =chromeWebDriver.findElement(By.tagName("h1"));
        title.click();

    }
    @Test
    @Tag("First Test")
    public void firstBasicTest(){
        chromeWebDriver.navigateToUrl("https://otus.home.kartushin.su/training.html");
        WebElement title =chromeWebDriver.findElement(By.tagName("h1"));
    }

}
