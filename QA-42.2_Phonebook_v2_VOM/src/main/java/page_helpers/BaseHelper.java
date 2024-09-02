package page_helpers;

import io.qameta.allure.Step;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.security.SecureRandom;
import java.time.Duration;
import java.util.Random;
import static org.testng.Assert.*;

public class BaseHelper {

    protected WebDriver driver;
    protected WebDriverWait wait;

    public BaseHelper(WebDriver driver, WebDriverWait wait) {
        this.driver = driver;
        this.wait = wait;
    }

    @Step("Получаем элемент с локатором - {0}")
    protected WebElement getElementBy(By locator) {
        return wait.until(ExpectedConditions.visibilityOf(driver.findElement(locator)));
    }
    @Step("Получаем элемент с локатором - {0} с ожиданием его кликабельности")
    private WebElement waitForClickableElement(By locator) {
        return wait.until(ExpectedConditions.elementToBeClickable(locator));
    }

    @Step("Ожидаем всплывающее уведомление")
    protected Alert getAlert() {
        return wait.until(ExpectedConditions.alertIsPresent());
    }

    @Step("Получаем текст всплывающего уведомления и закрываем его")
    public String getAlertText() {
        String allerText = getAlert().getText();
        getAlert().accept();
        return allerText;
    }

    @Step("Заполняем элемент с локатором {0}, значением {1}")
    protected void fillInputField(By locator, String value) {
        WebElement element = waitForClickableElement(locator);
        element.click();
        element.clear();
        element.sendKeys(value);
        assertEquals(value, element.getAttribute("value"), "Введенный текст отличается от того что в элементе");
    }
    @Step("Делаем клик по элементу с локатором {0}")
    protected void clickOnElement(By locator) {
        WebElement element = waitForClickableElement(locator);
        element.click();
    }
    @Step("Проверяем наличие на экране элемента с локатором {0}")
    protected void checkElementIsPresent(By locator) {
        assertTrue(getElementBy(locator).isDisplayed(), String.format("Ожидаемый элемент с локатором %s не найден на странице", locator));
    }
    @Step("Проверяем наличие на экране элемента с локатором {0}")
    protected boolean isElementPresent(By locator) {
        return driver.findElements(locator).size() > 0;
    }
    @Step("Генерирует случайные e-mail")
    public static String getRandomEmail() {
        char[] chars = "0123456789abcdef".toCharArray();
        Random random = new SecureRandom();
        char[] result = new char[8];
        for (int i = 0; i < result.length; i++) {
            int randomIndex = random.nextInt(chars.length);
            result[i] = chars[randomIndex];
        }
        String email = new String(result) + "@test.com";
        return email;
    }

    @Step("Ожидаем {0} секунд")
    public void waitInSeconds(int seconds) {
        try {
            Thread.sleep(Duration.ofSeconds(seconds).toMillis());
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

}