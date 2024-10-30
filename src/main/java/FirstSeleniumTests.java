import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import java.time.Duration;

public class FirstSeleniumTests {
    // Создаем драйвер
    WebDriver driver;

    @BeforeEach// - эта аннотация указание, что метод будет выполняться перед каждым нашим тестовым методом
//    @BeforeAll // - эта аннотация указание, что метод будет выполняться перед всеми нашими тестовым методом
    void startDriver() {
        //Инициализируем драйвер Хром Драйвером
        driver = new ChromeDriver();
//        Задаем не явное ожидание загрузки элементов
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
//        Задаем параметр окна браузера "развернуть на весь экран"
        driver.manage().window().maximize();
    }

    @AfterEach // эта аннотация указание, что метод будет выполняться после каждого теста
    void closeDriver() {
        //закрываем драйвер
        driver.quit();
    }

    @Test
    void test1() {
        //открываем страницу
        driver.get("https://www.bing.com/");
        //нажимаем кнопку назад
        driver.navigate().back();
        //нажимаем кнопку вперед
        driver.navigate().forward();
        //обновляем страницу
        driver.navigate().refresh();

        //проверяем, что открылась страница с Title = Bing
        Assertions.assertEquals("Bing", driver.getTitle(), "Открыта страница с другим Title");
    }

    @Test
    void test2() {
        driver.navigate().to("https://www.google.com/");
        driver.navigate().back();
        driver.navigate().forward();
        driver.navigate().refresh();

        Assertions.assertEquals("Google", driver.getTitle(), "Открыта страница с другим Title");
    }
}

// Горячие клавиши
//    win
//    ctrl+alt+l = выравнивание
//    ctrl+alt+o = удаление лишних импортов
//    ctrl+d = дублирование строки
//    ctrl+/ = комментирование строки
//    ctrl+shift+ u = изменение регистра переменной (ПРИМЕР пример)

//    mac
//    cmd+opt+l
//    ctrl+opt+o
//    cmd + d
//    cmd + /
//    cmd + shift+u