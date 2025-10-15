package org.pages;

import lombok.Getter;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.utils.WebDriverFactory;

import java.time.Duration;

public class TrainingPage extends BasePage {

    private static final Logger log = LoggerFactory.getLogger(TrainingPage.class);

    private static final String URL = "https://otus.home.kartushin.su/training.html";


    public TrainingPage(WebDriverFactory driverFactory) {
        super(driverFactory);
    }


    @Getter
    @FindBy(xpath = "//button[@id ='openModalBtn']")
    private WebElement buttonOpenModal;


    @Getter
    @FindBy(xpath = "//input[@type='text' and @id ='textInput']")
    private WebElement textInputArea;

    @Getter
    @FindBy(xpath = "//div[@class ='modal-content']")
    private WebElement modalWindow;

    @Getter
    @FindBy(xpath = "//span[@class ='close-btn']")
    private WebElement closeModalWindowBtn;

    @Getter
    @FindBy(xpath = "//input[@type ='text' and @id = 'name']")
    private WebElement inputNameArea;


    @Getter
    @FindBy(xpath = "//input[@type ='email' and @id = 'email']")
    private WebElement inputEmailArea;

    @Getter
    @FindBy(xpath = "//div[@class ='message' and @id = 'messageBox']")
    private WebElement messageBox;

    @Getter
    @FindBy(xpath = "//button[@type='submit']")
    private WebElement submitNameEmailButton;

    public TrainingPage clickToButton(WebElement button) {
        explicityWaitElement(button);
        button.click();
        return this;
    }

    public TrainingPage openModalWindow(WebElement elementModalWindow) {
        explicityWaitElement(elementModalWindow);
        return this;
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


    public TrainingPage typeIntoInputArea(WebElement element, String text) {
        explicityWaitElement(element);
        log.info("Input field is finded");
        element.clear();
        log.info("Input field is cleared");
        element.sendKeys(text);
        log.info("Text is input to input field");
        return this;
    }

    public String getInputValue(WebElement input) {
        new WebDriverWait(driver, Duration.ofSeconds(5))
                .until(ExpectedConditions.visibilityOf(input));
        return textInputArea.getAttribute("value");
    }

}

