import org.junit.jupiter.api.*;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import java.time.Duration;

public class FirstSeleniumTest {
    //Адрес страницы
    private static final String URL = "https://telranedu.web.app/home";
    //Драйвер
    public WebDriver driver;

    @BeforeEach
    void startDriver() {
        //Создание драйвера
        driver = new ChromeDriver();
        //Настройка ожидания появления элемента
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
    }

    @AfterEach
    void quitDriver() {
        driver.quit();//Закрытие браузера
    }

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
