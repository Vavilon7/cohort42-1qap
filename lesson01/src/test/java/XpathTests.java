import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;

public class XpathTests extends BaseTest{
    /*
    1) * - выбрать любой элемент (имеется ввиду тэг)
    2) [] - индекс, либо атрибуты
    3) / - переход от корневого тэга html/body/div/div[1]/a[1]
    4) // - переход к тэгу по всему документу (поиск) //div[1]/a[1]
    5) . - указание на текущий тэг  //body/.  остается body
    6) .. - родительский тэг //body/..
    7) @ - указание конкретного атрибута

    Методы:
    text() -  возвращает текст элемента //*[text()='HOME']
    contains() - проверяет содержит ли элемент конкретный текст//*[contains(text(),'HOME')]
    count() - возвращает количество элементов

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
    @DisplayName("Проверка авторизации с валидными данными")
    void test1() {
        //Заполняем поле ввода email
        fillInputField(By.xpath("//*[@name='email']"), "manuel@gm.com");
        //Заполняем поле ввода password
        fillInputField(By.xpath("//*[@name='password']"), "Manuel1234$");
        //Нажимаем кнопку Login
        clickOnElement(By.xpath("//*[text()='Login']"));
        //Проверяем отображение элемента
        checkElementOnDisplay(By.xpath("//*[@href='/contacts']"));
    }

    @Test
//    @Disabled - убирает тест из прогона
    @DisplayName("Проверка регистрация с валидными данными")
    void test2() {
        //Заполняем поле ввода email
        fillInputField(By.xpath("//*[@name='email']"), "manuelll@gm.com");
        //Заполняем поле ввода password
        fillInputField(By.xpath("//*[@name='password']"), "Manuel1234$");
        //Нажимаем кнопку Login
        clickOnElement(By.xpath("//*[text()='Registration']"));
        //Проверяем отображение элемента
        checkElementOnDisplay(By.xpath("//*[@href='/contacts']"));
    }
}
