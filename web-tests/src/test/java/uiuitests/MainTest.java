package uiuitests;

import io.github.bonigarcia.wdm.WebDriverManager;
import io.github.bonigarcia.wdm.managers.ChromeDriverManager;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebDriver;
import utils.ChromeWebDriverFactory;

public class MainTest {
    WebDriver driver;

    @Test
    public void setup(){
        ChromeWebDriverFactory.createWebDriver();
    }
}
