package HomeWork;

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

import java.time.Duration;

public class BaseTest {
    // Создаем драйвер
    protected WebDriver driver;
    // Создаём явные ожидания
    protected WebDriverWait wait;


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
        driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(10));
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

    public WebElement waitForClickableElement(By locator) {
        //ждем пока элемент по локатору станет кликабельный
        return wait.until(ExpectedConditions.elementToBeClickable(locator));
    }

    //ждем пока появится диалоговое окно или уведомление
    public Alert getAlert() {
        return wait.until(ExpectedConditions.alertIsPresent());
    }

    //заполняем поле ввода по локатору, конкретным значением.
    //проверяем что введенные значения совпадают со значением элемента
    public void fillInputField(By locator, String value) {
        //получаем элемент и кликаем на него
        WebElement element = waitForClickableElement(locator);
        element.click();
        //очищаем поле и вводим новые данные
        element.clear();
        element.sendKeys(value);
        //получаем значение элемента и сравниваем с введенным
        Assertions.assertEquals(value, element.getAttribute("value"), "Введенные данные отличаются от данных у элемента");
    }

    //кликаем на элемент по локатору
    public void clickOnElement(By locator) {
        waitForClickableElement(locator).click();
    }

    //проверяем наличие элемента по локатору на странице
    public void checkElementOnDisplay(By locator) {
        Assertions.assertTrue(driver.findElements(locator).size() > 0,  String.format("Элемент с локатором - %s не найден", locator ));
    }
}