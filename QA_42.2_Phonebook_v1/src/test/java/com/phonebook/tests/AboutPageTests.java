package com.phonebook.tests;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;

public class AboutPageTests extends BaseTest {
    @Test
    @DisplayName("Проверка отображения информации на странице About")
    void test1() {
        clickOnElement(By.cssSelector("[href='/about']"));
        Assertions.assertTrue(getElementBy(By.xpath("//*[contains(text(),'Contacts Web Application')]")).isDisplayed(), "На странице about отсутствует элемент с текстом 'Contacts Web Application'");
        Assertions.assertTrue(getElementBy(By.xpath("//*[contains(text(),'Test App')]")).isDisplayed(), "На странице about отсутствует элемент с текстом 'Test App'");
        Assertions.assertTrue(getElementBy(By.xpath("//*[contains(text(),'Use DevTools')]")).isDisplayed(), "На странице about отсутствует элемент с текстом 'Use DevTools'");
        Assertions.assertTrue(getElementBy(By.xpath("//*[contains(text(),'Powered by ©Tel-Ran')]")).isDisplayed(), "На странице about отсутствует элемент с текстом 'Use DevTools'");
    }

}
