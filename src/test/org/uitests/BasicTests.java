package org.uitests;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.NoSuchElementException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import classes.utils.WebDriverFactory;
import classes.pages.TrainingPage;

public class BasicTests {
    private static final Logger log = LoggerFactory.getLogger(BasicTests.class);
    private static final WebDriverFactory driverFactory = new WebDriverFactory();

    @BeforeAll
    static void setup(){

        WebDriverFactory.createWebDriver();
    }

    @BeforeEach
    void startDriver(){

        WebDriverFactory.startDriver();
    }

    @AfterEach
    void tearDown(){
        WebDriverFactory.quit();
    }

    @Test
    @DisplayName("Открытие браузера в headless-режиме")
    public void firstBasicTest() throws NoSuchElementException{
        WebDriverFactory.addChromeOption("--headless");
        TrainingPage trainingPage = new TrainingPage(driverFactory).open();
        trainingPage.typeIntoInputArea(trainingPage.getTextInputArea(), "OTUS");
        Assertions.assertEquals("OTUS", trainingPage.getInputValue(trainingPage.getTextInputArea()));
    }

    @Test
    @DisplayName("Открытие модального окна")
    public void secondBaseTest() throws NoSuchElementException{
        WebDriverFactory.addChromeOption("--kiosk");
        TrainingPage trainingPage = new TrainingPage(driverFactory).open();
        trainingPage.clickToButton(trainingPage.getButtonOpenModal());
        trainingPage.openModalWindow(trainingPage.getModalWindow());
        Assertions.assertTrue(trainingPage.isModalWindowOpened(trainingPage.getModalWindow()),
                "Модальное окно не открылось после клика на кнопку");
        trainingPage.clickToButton(trainingPage.getCloseModalWindowBtn());
    }

    @Test
    @DisplayName("Ввод ФИО и email в поле ввода")
    public void thirdTest() throws NoSuchElementException{
        final String name = "Maria Rodionova";
        final String email = "masha@mail.ru";

        WebDriverFactory.addChromeOption("--start-fullscreen");


        TrainingPage trainingPage = new TrainingPage(driverFactory).open();
        trainingPage.typeIntoInputArea(trainingPage.getInputNameArea(), name);
        trainingPage.typeIntoInputArea(trainingPage.getInputEmailArea(), email);
        trainingPage.clickToButton(trainingPage.getSubmitNameEmailButton());
        Assertions.assertTrue(trainingPage.getMessageBox().getText().contains(name));
        Assertions.assertTrue(trainingPage.getMessageBox().getText().contains(email));
    }
}