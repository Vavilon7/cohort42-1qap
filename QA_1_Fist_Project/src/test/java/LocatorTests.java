import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertTrue;

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
        assertTrue(!listAElements.isEmpty(), "Нет элементов с тэгом \"а\"");

        // Кликаем по элементу с текстом "LOGIN"
        getElementBy(By.linkText("LOGIN")).click();

        // Ищем элемент по имени
        WebElement elementByName = getElementBy(By.name("email"));
        WebElement elementByCss = getElementBy(By.cssSelector("[name='email']"));
        WebElement elementByxPath = getElementBy(By.xpath("//*[@name='email']"));
        assertTrue(elementByName.isDisplayed(), "elementByName нет на странице");
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
        assertTrue(elementById.isDisplayed(), "elementById нет на странице");

        // Ищем элемент по className
        WebElement elementByClassName = getElementBy(By.className("input-container"));
        assertTrue(elementByClassName.isDisplayed(), "elementByClassName нет на странице");

        // Ищем элемент по className внутри элемента
        WebElement elementByClassNameByClassName = elementByClassName.findElement(By.className("input-label"));
        assertTrue(elementByClassNameByClassName.isDisplayed(), "elementByClassName нет на странице");

        // Ищем элемент по linkText
        WebElement elementByLinkText = getElementBy(By.linkText("Let the car work"));
        assertTrue(elementByLinkText.isDisplayed(), "elementByLinkText нет на странице");

        // Ищем элемент по partialLinkText
        WebElement elementByPartialLinkText = getElementBy(By.partialLinkText("Let the"));
        assertTrue(elementByPartialLinkText.isDisplayed(), "elementByPartialLinkText нет на странице");
    }

    @Test
    @DisplayName("Поиск одного единственного элемента")
    void test3() {
        // Переход на страницу демо веб-магазина
        driver.get(URL_DEMO_WEB_SHOP);

        // Проверка наличия элемента с атрибутом href='/register' с использованием CSS-селектора
        assertTrue(getElementBy(By.cssSelector("[href='/register']")).isDisplayed());

        // Проверка наличия элемента с атрибутом href='/register' с использованием XPath
        assertTrue(getElementBy(By.xpath("//*[@href='/register']")).isDisplayed());

        // Проверка наличия элемента с атрибутом href, содержащим 'register', с использованием XPath
        assertTrue(getElementBy(By.xpath("//*[contains(@href, 'register')]")).isDisplayed());

        // Проверка наличия элемента с классом 'ico-register' с использованием CSS-селектора
        assertTrue(getElementBy(By.cssSelector("[class='ico-register']")).isDisplayed());

        // Проверка наличия элемента с классом 'ico-register' с использованием XPath
        assertTrue(getElementBy(By.xpath("//*[@class='ico-register']")).isDisplayed());

        // Проверка наличия элемента с классом, содержащим 'ico-register', с использованием XPath
        assertTrue(getElementBy(By.xpath("//*[contains(@class, 'ico-register')]")).isDisplayed());

        // Проверка наличия элемента с текстом 'Register' с использованием метода linkText
        assertTrue(getElementBy(By.linkText("Register")).isDisplayed());

        // Проверка наличия элемента с текстом 'Register' с использованием XPath
        assertTrue(getElementBy(By.xpath("//*[text()='Register']")).isDisplayed());

        // Проверка наличия элемента с текстом, содержащим 'Regist', с использованием метода partialLinkText
        assertTrue(getElementBy(By.partialLinkText("Regist")).isDisplayed());

        // Проверка наличия элемента с текстом, содержащим 'Regist', с использованием XPath
        assertTrue(getElementBy(By.xpath("//*[contains(text(), 'Regist')]")).isDisplayed());
    }

    @Test
    @DisplayName("Поиск сложного элемента через родителей")
    void test4() {
        // Переход на страницу демо веб-магазина
        driver.get(URL_DEMO_WEB_SHOP);

        // Проверка наличия ссылки на страницу компьютеров в списке с классом 'list' с использованием CSS-селектора
        assertTrue(getElementBy(By.cssSelector("ul[class='list'] a[href='/computers']")).isDisplayed());

        // Альтернативный способ: Проверка наличия ссылки на компьютеры с более простым CSS-селектором
        assertTrue(getElementBy(By.cssSelector(".list a[href='/computers']")).isDisplayed());

        // Проверка наличия ссылки на компьютеры с использованием XPath, который включает вложенность (менее предпочтительно)
        assertTrue(getElementBy(By.xpath("//ul[@class='list']//a[@href='/computers']")).isDisplayed()); // так делать можно, но не желательно

        // Проверка наличия ссылки на компьютеры с использованием XPath с помощью descendant оси (предпочтительно)
        assertTrue(getElementBy(By.xpath("//ul[@class='list']//descendant::a[@href='/computers']")).isDisplayed()); // так желательно

        // Поиск родительского элемента с классом 'list'
        WebElement parentElement = getElementBy(By.className("list"));

        // Поиск дочернего элемента с ссылкой на компьютеры внутри родительского элемента
        WebElement childElement = parentElement.findElement(By.cssSelector("[href='/computers']"));
        // Проверка отображения найденного дочернего элемента
        assertTrue(childElement.isDisplayed());

        // Получение текущего количества товаров в корзине
        int startQuantity = getCartQuantity();

        // Поиск второго элемента с классом 'product-item' и клик по кнопке 'Add to cart' внутри этого элемента
        driver.findElements(By.cssSelector("[class='product-item']")).get(1)
                .findElement(By.cssSelector("[value='Add to cart']")).click();

        // Явное ожидание для гарантии того, что элемент добавлен в корзину
        waitInSeconds(2); // Можно заменить на явное ожидание, например WebDriverWait

        // Получение обновленного количества товаров в корзине после добавления товара
        int resultQuantity = getCartQuantity();

        // Проверка, что количество товаров в корзине увеличилось на 1
        waitInSeconds(2);
        assertTrue(startQuantity + 1 == resultQuantity, "Товар не добавился в корзину");
    }

    // Метод для получения количества товаров в корзине
    private int getCartQuantity() {
        // Поиск элемента, отображающего количество товаров в корзине, по классу 'cart-qty'
        WebElement cartQtyElement = getElementBy(By.className("cart-qty")); // '(0)'

        // Извлечение текста, содержащего количество товаров, и удаление всех символов, кроме чисел
        String cartQtyString = cartQtyElement.getText().replaceAll("\\D", ""); // '0' - заменили все символы '(' и ')'

        // Преобразование строки с количеством товаров в корзине в целое число и возврат значения
        return Integer.parseInt(cartQtyString);
    }
}