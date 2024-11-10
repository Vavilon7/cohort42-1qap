package com.ait.qa;

import app.web.demoShopSelenium.data.core.BaseSteps;
import app.web.demoShopSelenium.data.core.BaseTest;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

@Tag("@Registration")
public class RegisterPageTest extends BaseSteps {
    @BeforeEach
    void precondition() {
        driver.get("https://demowebshop.tricentis.com/");
    }

    @Test
    @Tag("@Positive")
    @Tag("@SMOKE")
    @DisplayName("Регистрация с валидными данными")
    void test1() {
        clickOnElement(By.cssSelector("a[href='/register']"));
        fillInputField(By.xpath("//*[@name='FirstName']"), "Ivanova");
        fillInputField(By.xpath("//*[@name='LastName']"), "Vika");
        fillInputField(By.xpath("//*[@name='Email']"), "bodak87@gmail.com");
        fillInputField(By.xpath("//*[@name='Password']"), "123456");
        fillInputField(By.xpath("//*[@name='ConfirmPassword']"), "123456");
        waitForClickableElement(By.xpath("//*[@name='register-button']"));

    }

    @Test
    @Tag("@Negative")
    @DisplayName("Проверка регистрации с невалидными данными")
    void test2() {
        clickOnElement(By.cssSelector("a[href='/register']"));
        fillInputField(By.xpath("//*[@name='FirstName']"), "Ivan");
        fillInputField(By.xpath("//*[@name='LastName']"), "Ivanov");
        fillInputField(By.xpath("//*[@name='Email']"), "bodak83gmail.com");
        fillInputField(By.xpath("//*[@name='Password']"), "12346");
        fillInputField(By.xpath("//*[@name='ConfirmPassword']"), "123456");
        WebElement classNameElement = driver.findElement(By.className("field-validation-error"));
        Assertions.assertEquals("Wrong email", classNameElement.getText(), "Заголовок элемента не соответствует 'Wrong email'");
        fillInputField(By.xpath("//*[@name='Password']"), "");

        // Проверка сообщения об ошибке для некорректного email
        WebElement emailError = driver.findElement(By.cssSelector(".field-validation-error[data-valmsg-for='Email']"));
        Assertions.assertEquals("Wrong email", emailError.getText().trim(), "Сообщение об ошибке для Email не соответствует ожидаемому");

        // Очищаем поле Password и проверяем сообщение об ошибке для пустого пароля
        fillInputField(By.xpath("//*[@name='Password']"), "");
        WebElement passwordError = driver.findElement(By.cssSelector(".field-validation-error[data-valmsg-for='Password']"));
        Assertions.assertEquals("Password is required.", passwordError.getText().trim(), "Сообщение об ошибке для Password не соответствует ожидаемому");

        // Очищаем поле Password и проверяем сообщение об ошибке для не корректного пароля
        fillInputField(By.xpath("//*[@name='Password']"), "12345");
        WebElement passwordErrorElement = driver.findElement(By.cssSelector(".field-validation-error[data-valmsg-for='Password']"));
        Assertions.assertEquals("The password should have at least 6 characters.", passwordErrorElement.getText().trim(), "Сообщение об ошибке для Password не соответствует ожидаемому");

        // Проверка сообщения об ошибке для несовпадающего подтверждения пароля
        WebElement confirmPasswordError = driver.findElement(By.cssSelector(".field-validation-error[data-valmsg-for='ConfirmPassword']"));
        Assertions.assertEquals("The password and confirmation password do not match.", confirmPasswordError.getText().trim(), "Сообщение об ошибке для ConfirmPassword не соответствует ожидаемому");
    }
}
