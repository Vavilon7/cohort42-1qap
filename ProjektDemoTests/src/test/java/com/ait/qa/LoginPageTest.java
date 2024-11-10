package com.ait.qa;

import app.web.demoShopSelenium.data.core.BaseSteps;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

public class LoginPageTest extends BaseSteps  {
    @Test
    @Tag("@Positive")
    @Tag("@SMOKE")
    @DisplayName("Вход с валидными данными")
    void test1() {
        clickOnElement(By.cssSelector("a[href='/login']"));
        WebElement tagElementH1 = driver.findElement(By.tagName("h1"));
        Assertions.assertEquals("Welcome, Please Sign In!",tagElementH1.getText(), "Заголовок страницы не Welcome, Please Sign In!" );

        fillInputField(By.xpath("//*[@name='Email']"), "bodak83@gmail.com");
        fillInputField(By.xpath("//*[@name='Password']"), "123456");
        WebElement elementById = driver.findElement(By.id("RememberMe"));
        Assertions.assertTrue(elementById.isDisplayed(), "Элемент по id отсутствует");
        clickOnElement(By.xpath("//input[@id='RememberMe']"));
        waitForClickableElement(By.xpath("//*[@class='button-1 login-button']"));
    }
}
