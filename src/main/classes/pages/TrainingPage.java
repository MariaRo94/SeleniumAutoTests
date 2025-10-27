package classes.pages;

import classes.utils.WebDriverFactory;
import lombok.Getter;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.Duration;

@Getter
public class TrainingPage extends BasePage {

    private static final Logger log = LoggerFactory.getLogger(TrainingPage.class);

    private static final String URL = "https://otus.home.kartushin.su/training.html";

    public TrainingPage(WebDriverFactory driverFactory) {
        super(driverFactory);
    }

    @FindBy(css = "#openModalBtn")
    private WebElement buttonOpenModal;

    @FindBy(css = "#textInput")
    private WebElement textInputArea;

    @FindBy(css = ".modal-content")
    private WebElement modalWindow;

    @FindBy(css = "#closeModal#closeModal")
    private WebElement closeModalWindowBtn;

    @FindBy(css = "#name")
    private WebElement inputNameArea;

    @FindBy(css = "#email")
    private WebElement inputEmailArea;

    @FindBy(css = "#messageBox.message")
    private WebElement messageBox;

    @FindBy(css = "button[type=\"submit\"]")
    private WebElement submitNameEmailButton;

    public void clickToButton(WebElement button) {
        explicityWaitElement(button);
        button.click();
    }

    public void openModalWindow(WebElement elementModalWindow) {
        explicityWaitElement(elementModalWindow);
    }

    public boolean isModalWindowOpened(WebElement modalWindow) {
        try {
            return modalWindow.isDisplayed();
        } catch (NoSuchElementException e) {
            log.error(e.getMessage() + "Modal window is not opened");
        }
        return false;
    }

    public TrainingPage open() {
        pageBeLoaded();
        driver.get(URL);
        log.info("The specified URL has been navigated to.");
        return this;
    }

    public void typeIntoInputArea(WebElement element, String text) {
        explicityWaitElement(element);
        log.info("Input field is finded");
        element.clear();
        log.info("Input field is cleared");
        element.sendKeys(text);
        log.info("Text is input to input field");
    }

    public String getInputValue(WebElement input) {
        new WebDriverWait(driver, Duration.ofSeconds(5))
                .until(ExpectedConditions.visibilityOf(input));
        return textInputArea.getAttribute("value");
    }
}

