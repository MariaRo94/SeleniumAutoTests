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

    @FindBy(tagName = "h1")
    private WebElement header;

    @FindBy(xpath = "//button[@id ='openModalBtn']")
    private WebElement buttonOpenModal;


    @Getter
    @FindBy(xpath = "//input[@type='text' and @id ='textInput']")
    private WebElement textInputArea;

    @FindBy(xpath = "//div[@class ='modal-content']")
    private WebElement modalWindow;

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

    public TrainingPage clickToOpeModalButton(){
        explicityWaitElement(buttonOpenModal);
        buttonOpenModal.click();
        return this;
    }

    public TrainingPage openModalWindow(){
        explicityWaitElement(modalWindow);
        return this;
    }

    public boolean isModalWindowOpened(){
       try {
           return modalWindow.isDisplayed();
       }
       catch (NoSuchElementException e){
           log.error(e.getMessage()+ "Модальное окно не открылось");
       }
        return false;
    }

    public TrainingPage closeModalWindow(){
        explicityWaitElement(closeModalWindowBtn);
        closeModalWindowBtn.click();
        return this;
    }


    public TrainingPage open() {
        pageBeLoaded();
        driver.get(URL);
        log.info("Выполнен переход по указанному URL");
        return this;
    }


    public TrainingPage typeIntoInputArea(WebElement element, String text) {
        explicityWaitElement(element);
        log.info("Поле ввода найдено");
        element.clear();
        log.info("Поле ввода очищено");
        element.sendKeys(text);
        log.info("Текст введен в поле ввода");
        return this;
    }



    public String getInputValue() {
        new WebDriverWait(driver, Duration.ofSeconds(5))
                .until(ExpectedConditions.visibilityOf(textInputArea));
        return textInputArea.getAttribute("value");
    }

    public void getAlert(){
        Alert alert = driver.switchTo().alert();

    }
}

