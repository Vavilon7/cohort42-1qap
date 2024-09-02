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
    // Драйвер браузера
    public WebDriver driver;

    // Адреса страниц для тестирования
    protected static final String URL_PHONE_BOOK = "https://telranedu.web.app/home";
    protected static final String URL_ILCARRO = "https://ilcarro.web.app/search";
    protected static final String URL_DEMO_WEB_SHOP = "https://demowebshop.tricentis.com";

    // Явное ожидание для элементов на странице
    WebDriverWait wait;

    @BeforeEach
    void startDriver() {
        // Создание экземпляра драйвера для браузера (Chrome)
        driver = new ChromeDriver();

        // Инициализация явного ожидания (до 5 секунд ожидания)
        wait = new WebDriverWait(driver, Duration.ofSeconds(5));

        // Настройка неявного ожидания для появления элементов на странице (до 5 секунд ожидания)
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));

        // Дополнительное ожидание для выполнения скриптов (если необходимо)
        // driver.manage().timeouts().setScriptTimeout(Duration.ofSeconds(5));

        // Увеличивает таймаут ожидания для завершения страниц до указанного времени (если необходимо)
        // driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(5));

        // Установка размера окна браузера в максимальный
        driver.manage().window().maximize();
    }

    @AfterEach
    void quitDriver() {
        // Закрытие браузера и завершение работы драйвера
        driver.quit();
    }

    // Метод для поиска элемента на странице с явным ожиданием его появления
    public WebElement getElementBy(By locator) {
        // Ожидание, пока элемент не станет видимым на странице, и возврат его
        return wait.until(ExpectedConditions.visibilityOf(driver.findElement(locator)));
    }

    // Ожидание кликабельности элемента
    private WebElement waitForClickableElement(By locator) {
        // Ждем, пока элемент не станет кликабельным, и возвращаем его
        return wait.until(ExpectedConditions.elementToBeClickable(locator));
    }

    // Ожидание появления уведомления от браузера (Alert)
    protected Alert getAlert() {
        // Ждем, пока не появится Alert, и возвращаем его
        return wait.until(ExpectedConditions.alertIsPresent());
    }

    // Метод для заполнения поля ввода
    protected void fillInputField(By locator, String value) {
        // Ждем, пока элемент не станет кликабельным
        WebElement element = waitForClickableElement(locator);
        // Очищаем поле ввода перед вводом текста
        element.click();  // Клик для фокусировки на элементе
        element.clear();  // Очистка текущего значения в поле ввода
        // Вводим значение в поле
        element.sendKeys(value);
        // Проверяем, что введенное значение совпадает с тем, что находится в поле
        Assertions.assertEquals(value, element.getAttribute("value"), "Введенный текст отличается от того что в элементе");
    }

    // Метод для клика по элементу
    protected void clickOnElement(By locator) {
        // Ждем, пока элемент не станет кликабельным
        WebElement element = waitForClickableElement(locator);
        // Выполняем клик по элементу
        element.click();
    }

    // Метод для явного ожидания (задержка)
    protected void waitInSeconds(int seconds) {
        try {
            // Ожидание в течение указанного количества секунд
            Thread.sleep(Duration.ofSeconds(seconds).toMillis());
        } catch (InterruptedException e) {
            // Если ожидание было прервано, выбрасываем исключение
            throw new RuntimeException(e);
        }
    }
}