import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import java.util.List;

public class LocatorTests extends BaseTest{

    @Test
    @Tag("@1234")
    @Tag("@PHONEBOOK")
    @DisplayName("Поиск элементов на странице Phonebook")
    void test1234() {
//        <h1>PHONEBOOK</h1>
        WebElement tagElementH1 = driver.findElement(By.tagName("h1"));
        Assertions.assertEquals("PHONEBOOK",tagElementH1.getText(), "Заголовок страницы не PHONEBOOK" );

        List<WebElement> listAElements = driver.findElements(By.tagName("a"));
        Assertions.assertTrue(!listAElements.isEmpty(), "Список элементов пуст");

//        <a href="/login" style="">LOGIN</a>
//        особенность данного метода, и почему его почти не используют, он ищет текст, только когда есть атрибуты в тэге
        WebElement linkTextElement = driver.findElement(By.linkText("LOGIN"));
//        Надо быть внимательнее с аттрибутом href, может возвращать полную ссылку
        Assertions.assertEquals("/login", linkTextElement.getDomAttribute("href"),"Аттрибут элемента не соответствует ожидаемому");

        WebElement partialLinkTextElement = driver.findElement(By.partialLinkText("LOG"));
        Assertions.assertEquals("LOGIN", partialLinkTextElement.getText(), "Текст элемента не соответствует ожидаемому");

//        <button type="submit" name="login">Login</button>
        WebElement elementByName = driver.findElement(By.name("login"));
        Assertions.assertEquals("submit", elementByName.getAttribute("type"),"Аттрибут элемента не соответствует ожидаемому");

        WebElement classNameElement = driver.findElement(By.className("container"));
        Assertions.assertTrue(classNameElement.isDisplayed(), "Элемент по имени класса отсутствует");

        WebElement elementById = driver.findElement(By.id("root"));
        Assertions.assertTrue(elementById.isDisplayed(), "Элемент по id отсутствует");
    }

    @Test
    void test2() {
        WebElement parentElement = driver.findElement(By.tagName("form"));
        WebElement loginButton = parentElement.findElement(By.name("login"));
        Assertions.assertTrue(loginButton.isDisplayed(), "Элемент loginButton отсутствует");

    }
}