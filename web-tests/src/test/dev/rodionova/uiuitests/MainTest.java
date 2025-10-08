package dev.rodionova.uiuitests;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import utils.ChromeWebDriverFactory;

import java.time.Duration;

public class MainTest {
    private static ChromeWebDriverFactory driver;
    utils.BrowserActions browser = new ChromeWebDriverFactory(true);



    @BeforeAll
    public static void setup() {
        driver.createWebDriver();
    }

    @Test
    public void testTitle() {
        driver.navigateToUrl("https://otus.home.kartushin.su/training.html");
        driver.waitForVisible(By.cssSelector("h1"), Duration.ofSeconds(5));
        String title = driver.getText(By.xpath("h1[text()='Тренажёр для оттачивания навыков работы с Selenium']"));
        Assertions.assertEquals("Тренажёр для оттачивания навыков работы с Selenium", title);
    }


        @AfterAll
        public static void tearDown() {
            driver.quit();
        }

    }

