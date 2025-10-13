package org.uitests;

import org.openqa.selenium.NoSuchElementException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.utils.ChromeWebDriverFactory;

import java.time.Duration;

public class BasicTests {
    ChromeWebDriverFactory chromeWebDriver = new ChromeWebDriverFactory();
    private static final Logger log = LoggerFactory.getLogger(BasicTests.class);


    @BeforeAll
    static void setup(){
        ChromeWebDriverFactory.createWebDriver();
    }


    @BeforeEach
    void startDriver(){
        ChromeWebDriverFactory.startDriver();

        chromeWebDriver.getDriver()
                .manage()
                .timeouts()
                .implicitlyWait(Duration.ofSeconds(10));
    }

    @AfterEach
    void tearDown(){
        chromeWebDriver.quit();
    }


    @Test
    public void firstBasicTest() throws NoSuchElementException {
        log.info("Navigating to training page");
        chromeWebDriver.navigateToUrl("https://otus.home.kartushin.su/training.html");

        WebElement title = chromeWebDriver.findElement(By.tagName("h1"));
        log.info("Found header: {}", title.getText());
    }

}
