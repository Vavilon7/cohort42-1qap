package phonebook;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import phonebook.BaseTest;

public class XPathLocatorTests extends BaseTest {

    /*
    1) * - позволяет выбрать любой элемент
    2) [] - индекс узла html/body/div/div[1]/a[1]
    узел - тэг элемента
    3) / - путь от корневого узла html/body/div/div[1]/a[1]
    4) // - ищет узлы по всему документу //div[1]/a[1]
    5) . - текущий узел //body/.  остается body
    6) .. - родительский узел //body/..
    7) @ - указание на конкретный атрибут

    Методы xPath
    text() - возвращает текст элемента //*[text()='LOGIN']
    contains() - проверяет содержит ли строка подстроку //a[contains(text(), 'Notebooks')]
    count() - возвращает нам количество элементов

        //a[contains(text(), 'Notebooks')]
        //*[@id="root"]/div[2]/div/form/input[1]

        //ul[@class='top-menu']/descendant::a[contains(text(), 'Notebooks')]

         * В XPath есть несколько осей, которые можно использовать при создании выражений:
        1) child::note — Выбирает все узлы note, которые являются прямыми потомками текущего узла
        2) attribute::date — Выбирает атрибут date текущего узла
        3) child::* — Выбирает всех прямых потомков текущего узла
        4) attribute::* — Выбирает все атрибуты текущего узла
        5) child::text() — Выбирает все текстовые узлы текущего узла
        6) child::node() — Выбирает всех прямых потомков текущего узла
        7) descendant::div — Выбирает всех потомков div текущего узла
        8) ancestor::div — Выбирает всех предков div текущего узла
        9) ancestor-or-self::div — Выбирает всех предков div текущего узла, а также сам текущий узел, если это узел div
        10) child::* /child::div — Выбирает всех прямых потомков прямых потомков (“внуков") div текущего узла
        11) last() — Выделяет последний элемент в дереве.
        12) following::div - выбирает всех соседей текущего и следущих узлов
        *
     */



    @Test
    @DisplayName("Поиск элементов по xPath")
    void findElementByXpath() {
        driver.get(URL_PHONE_BOOK);
        clickOnElement(By.xpath("//*[text()='LOGIN']"));

        fillInputField(By.xpath("//*[@name='email']"), "manuel@gm.com");
        fillInputField(By.xpath("//*[@name='password']"), "Manuel1234$");

        clickOnElement(By.xpath("//*[text()='Login']"));

        Assertions.assertTrue(getElementBy(By.xpath("//button[text()='Sign Out']")).isDisplayed(), "Отсутствует кнопка выхода ");
    }
}
