package test;

import app.web.demoShopSelenium.data.core.BaseSteps;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
@Tag("@Login")
public class LoginTest extends BaseSteps {
    @Test
    @Tag("@Positive")
    @Tag("@3")
    @DisplayName("Проверка авторизации с валидными данными")
    void test3() {
        clickOnElement(By.cssSelector("a[href='/login']"));
        fillInputField(By.xpath("//*[@name='Email']"), TEST_USER.getEmail());
        fillInputField(By.xpath("//*[@name='Password']"), TEST_USER.getPassword());
        WebElement elementById = driver.findElement(By.id("RememberMe"));
        Assertions.assertTrue(elementById.isDisplayed(), "Элемент по id отсутствует");
        clickOnElement(By.xpath("//input[@id='RememberMe']"));
        waitForClickableElement(By.xpath("//*[@class='button-1 login-button']"));
    }
    @Test
    @Tag("@Negative")
    @Tag("@4")
    @DisplayName("Проверка авторизации с валидными данными")
    void test4() {
        clickOnElement(By.cssSelector("a[href='/login']"));

        fillInputField(By.xpath("//*[@name='Email']"), NEW_USER.getEmail());
        fillInputField(By.xpath("//*[@name='Password']"), TEST_USER.getPassword());
        WebElement elementById = driver.findElement(By.id("RememberMe"));
        Assertions.assertTrue(elementById.isDisplayed(), "Элемент по id отсутствует");
        clickOnElement(By.xpath("//input[@id='RememberMe']"));
        waitInSeconds(2);
        waitForClickableElement(By.xpath("//*[@class='button-1 login-button']"));
    }
}
