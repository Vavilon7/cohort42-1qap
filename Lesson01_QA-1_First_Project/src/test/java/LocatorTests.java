import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.util.List;

public class LocatorTests extends BaseTest {

    @Test
    @DisplayName("Поиск элемента по tagName и name")
    void test1() {
        // Открываем страницу
        driver.get(URL_PHONE_BOOK);
        // Ищем элемент по тегу
        WebElement elementH1 = driver.findElement(By.tagName("h1"));
        // Проверяем, что текст элемента соответствует ожидаемому
        Assertions.assertEquals("PHONEBOOK", elementH1.getText(), "Заголовок страницы не PHONEBOOK");

        // Ищем все элементы с тегом "a"
        List<WebElement> listAElements = driver.findElements(By.tagName("a"));
        // Проверяем, что список элементов не пуст
        Assertions.assertTrue(!listAElements.isEmpty(), "Нет элементов с тэгом \"а\"");

        // Кликаем по элементу с текстом "LOGIN"
        getElementBy(By.linkText("LOGIN")).click();

        // Ищем элемент по имени
        WebElement elementByName = getElementBy(By.name("email"));
        WebElement elementByCss = getElementBy(By.cssSelector("[name='email']"));
        WebElement elementByxPath = getElementBy(By.xpath("//*[@name='email']"));
        Assertions.assertTrue(elementByName.isDisplayed(), "elementByName нет на странице");
    }

    @Test
    @DisplayName("Поиск элемента по локаторам")
    void test2() {
        // Открываем страницу
        driver.get(URL_ILCARRO);
        // Ищем элемент по id
        //[id="city"]
        WebElement elementById = getElementBy(By.id("city"));
        // Проверяем, что элемент отображается на странице
        Assertions.assertTrue(elementById.isDisplayed(), "elementById нет на странице");

        // Ищем элемент по className
        WebElement elementByClassName = getElementBy(By.className("input-container"));
        Assertions.assertTrue(elementByClassName.isDisplayed(), "elementByClassName нет на странице");

        // Ищем элемент по className внутри элемента
        WebElement elementByClassNameByClassName = elementByClassName.findElement(By.className("input-label"));
        Assertions.assertTrue(elementByClassNameByClassName.isDisplayed(), "elementByClassName нет на странице");

        // Ищем элемент по linkText
        WebElement elementByLinkText = getElementBy(By.linkText("Let the car work"));
        Assertions.assertTrue(elementByLinkText.isDisplayed(), "elementByLinkText нет на странице");

        // Ищем элемент по partialLinkText
        WebElement elementByPartialLinkText = getElementBy(By.partialLinkText("Let the"));
        Assertions.assertTrue(elementByPartialLinkText.isDisplayed(), "elementByPartialLinkText нет на странице");
    }
}
