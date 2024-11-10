package app.web.demoShopSelenium.data.core;

import app.web.demoShopSelenium.data.Contact;
import app.web.demoShopSelenium.data.User;
import io.qameta.allure.Step;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.security.SecureRandom;
import java.time.Duration;
import java.util.List;
import java.util.Random;

public class BaseTest { // Создаем драйвер
    protected WebDriver driver;
    // Создаём явные ожидания
    protected WebDriverWait wait;
    protected static final Contact TEST_CONTACT = new Contact("Ivanov", "Ivan","bodak83@gmail.com","123456","123456" );
    protected static final User TEST_USER = new User("bodak83@gmail.com", "123456");

    protected static final User NEW_USER = new User(getRandomEmail(), "123456");

    @BeforeEach
// - эта аннотация указание, что метод будет выполняться перед каждым нашим тестовым методом
//    @BeforeAll // - эта аннотация указание, что метод будет выполняться перед всеми нашими тестовым методом
    void startDriver() {
        //Инициализируем драйвер Хром Драйвером
        driver = new ChromeDriver();
        // инициализируем явные ожидания
        wait = new WebDriverWait(driver, Duration.ofSeconds(7));
//        Задаем не явное ожидание загрузки элементов
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
//        Задаём не явное ожидание полной загрузки страницы
        driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(20));
//        Задаем параметр окна браузера "развернуть на весь экран"
        driver.manage().window().maximize();
        driver.get("https://demowebshop.tricentis.com/");
    }

    @AfterEach
        // эта аннотация указание, что метод будет выполняться после каждого теста
    void closeDriver() {
        //закрываем драйвер
        driver.quit();
    }

    public WebElement getElement(By locator) {
        //ждем элемент по локатору, если он не появится в течение 5 секунд
        return wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
    }

    public List<WebElement> getElements(By locator) {
        return driver.findElements(locator);
    }

    public WebElement waitForClickableElement(By locator) {
        //ждем пока элемент по локатору станет кликабельный
        return wait.until(ExpectedConditions.elementToBeClickable(locator));
    }

    //ждем пока появится диалоговое окно или уведомление
    public Alert getAlert() {
        return wait.until(ExpectedConditions.alertIsPresent());
    }

    @Step("Проверяем наличие элемента на странице")
    //проверяем наличие элемента по локатору на странице
    public boolean elementIsDisplayed(By locator) {
        return getElements(locator).size() > 0;
    }

    @Step("Генерируем рандомный email")
    public static String getRandomEmail() {
        char[] chars = "0123456789abcdefgh".toCharArray();
        Random random = new SecureRandom();
        char[] result = new char[8];
        for (int i = 0; i < result.length; i++) {
            int randomIndex = random.nextInt(chars.length);
            result[i] = chars[randomIndex];
        }
        String email = new String(result) + "@test.com";
        return email;
    }
    @Step("Ожидаем {0} секунды ")
    protected void waitInSeconds(int seconds){
        try {
            Thread.sleep(Duration.ofSeconds(seconds).toMillis());
        }catch (InterruptedException e){
            throw new RuntimeException(e);
        }
    }
}
