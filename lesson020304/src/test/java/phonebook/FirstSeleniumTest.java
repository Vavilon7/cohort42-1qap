package phonebook;

import org.junit.jupiter.api.*;
import phonebook.BaseTest;

public class FirstSeleniumTest extends BaseTest {

    @Test
    @DisplayName("Проверка заголовка страницы")
    void testOpenBrowser() {
        driver.get("https://www.google.com/");//Открытие страницы

        driver.navigate().back();//Возврат на предыдущую страницу
        driver.navigate().forward();//Переход на следующую страницу
        driver.navigate().refresh();//Обновление страницы
        Assertions.assertEquals("Google", driver.getTitle(), "Открыта страница с другим Title");//Проверка заголовка страницы
    }
}
