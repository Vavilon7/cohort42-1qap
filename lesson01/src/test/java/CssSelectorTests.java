import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

public class CssSelectorTests extends BaseTest {

    @Test
    @DisplayName("Проверка поиска элемента по css")
    void test1() {
        // tag name
        // Абсолютный путь
        WebElement tagElement1 = driver.findElement(By.cssSelector("html body div div h1"));
        Assertions.assertEquals("PHONEBOOK",tagElement1.getText(), "Заголовок страницы не PHONEBOOK" );
        // Относительный путь
        WebElement tagElement2 = driver.findElement(By.cssSelector("h1"));
        Assertions.assertEquals("PHONEBOOK",tagElement2.getText(), "Заголовок страницы не PHONEBOOK" );
        // Дочерний Элемент - >
        WebElement childElement = driver.findElement(By.cssSelector("form > input"));
        Assertions.assertTrue(childElement.isDisplayed(), "Не найден дочерний элемент");
        //поиск по ID - #
        WebElement idElement = driver.findElement(By.cssSelector("#root"));
//        WebElement idElement = driver.findElement(By.cssSelector("html #root"));
//        WebElement idElement = driver.findElement(By.cssSelector("[id='root']"));
        Assertions.assertTrue(idElement.isDisplayed(), "Не найден элемент по id");
        //поиск по class - .
        WebElement classElement = driver.findElement(By.cssSelector(".container"));
//        WebElement classElement = driver.findElement(By.cssSelector("html .container"));
        Assertions.assertTrue(classElement.isDisplayed(), "Не найден элемент по class");
        //поиск по атрибуту
        WebElement atrElement = driver.findElement(By.cssSelector("[name='email']"));
        Assertions.assertTrue(atrElement.isDisplayed(), "Не найден элемент по атрибуту");
//        WebElement atrElement = driver.findElement(By.cssSelector("input[name='email']"));
//        WebElement atrElement = driver.findElement(By.cssSelector("input[name]"));
        /**
         * contains -> *= By.cssSelector("input[name*='mai']")
         * start with -> ^= By.cssSelector("input[name^='ema']")
         * end on -> $= By.cssSelector("input[name$='ail']")
         */
    }

    @Test
    @DisplayName("Проверка авторизации с валидными данными")
    void test2() {
        //Заполняем поле ввода email
        fillInputField(By.name("email"), "manuel@gm.com");
        //Заполняем поле ввода password
        fillInputField(By.name("password"), "Manuel1234$");
        //Нажимаем кнопку Login
        clickOnElement(By.name("login"));
        //Проверяем отображение элемента
        checkElementOnDisplay(By.cssSelector("[href='/contacts']"));
    }

}
