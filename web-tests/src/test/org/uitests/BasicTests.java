package org.uitests;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.utils.WebDriverFactory;
import org.pages.Pages;
import org.pages.TrainingPage;

public class BasicTests {
    private static final Logger log = LoggerFactory.getLogger(BasicTests.class);
    private final WebDriverFactory driverFactory = new WebDriverFactory();

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
        TrainingPage trainingPage = Pages.trainingPage(driverFactory).open();
        trainingPage.typeIntoInputArea(trainingPage.getTextInputArea(), "OTUS");
        Assertions.assertEquals("OTUS", trainingPage.getInputValue());
    }

    @Test
    @DisplayName("Открытие модального окна")
    public void secondBaseTest() throws NoSuchElementException{
        WebDriverFactory.addChromeOption("--kiosk");
        TrainingPage trainingPage = Pages.trainingPage(driverFactory).open();
        trainingPage.clickToOpeModalButton();
        trainingPage.openModalWindow();
        Assertions.assertTrue(trainingPage.isModalWindowOpened(),
                "Модальное окно не открылось после клика на кнопку");
        trainingPage.closeModalWindow();
    }

    @Test
    @DisplayName("Ввод ФИО и email в поле ввода")
    public void thirdTest() throws NoSuchElementException{
        String name = "Maria Rodionova";
        String email = "masha@mail.ru";

        WebDriverFactory.addChromeOption("--start-fullscreen");


        TrainingPage trainingPage = Pages.trainingPage(driverFactory).open();
        trainingPage.typeIntoInputArea(trainingPage.getInputNameArea(), name);
        trainingPage.typeIntoInputArea(trainingPage.getInputEmailArea(), email);
        Assertions.assertTrue(trainingPage.getMessageBox().getText().contains(name));
        Assertions.assertTrue(trainingPage.getMessageBox().getText().contains(email));
    }
}
