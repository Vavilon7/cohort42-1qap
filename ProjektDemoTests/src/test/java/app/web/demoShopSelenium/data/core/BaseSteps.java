package app.web.demoShopSelenium.data.core;
import io.qameta.allure.Step;
import org.junit.jupiter.api.Assertions;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

public class BaseSteps extends BaseTest {
    //заполняем поле ввода по локатору, конкретным значением.
    //проверяем что введенные значения совпадают со значением элемента
    @Step("Заполняем поле {0}, значением {0}")
    public void fillInputField(By locator, String value) {
        //получаем элемент и кликаем на него
        WebElement element = waitForClickableElement(locator);
        element.click();
        //очищаем поле и вводим новые данные
        element.clear();
        element.sendKeys(value);

    }

    @Step("Кликаем на элемент с локатором - {0}")
    //кликаем на элемент по локатору
    public void clickOnElement(By locator) {
        waitForClickableElement(locator).click();
    }

    protected void loginTestUser() {
        clickOnElement(By.cssSelector("a[href='/login']"));
        fillInputField(By.xpath("//*[@name='Email']"), TEST_USER.getEmail());
        fillInputField(By.xpath("//*[@name='Password']"), TEST_USER.getPassword());
        WebElement elementById = driver.findElement(By.id("RememberMe"));
        Assertions.assertTrue(elementById.isDisplayed(), "Элемент по id отсутствует");
        clickOnElement(By.xpath("//input[@id='RememberMe']"));
        waitForClickableElement(By.xpath("//*[@class='button-1 login-button']"));
    }
}