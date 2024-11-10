package test;
import app.web.demoShopSelenium.data.core.BaseSteps;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;

@Tag("@MENU")
@Tag("@ALL")
public class AddToCartTest extends BaseSteps {
    @Test
    @Tag("@6")
    @DisplayName("Добавления товаров в корзину")
    void test6() {

        // Установка времени ожидания
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));

        // Добавление первого товара в корзину
        clickOnElement(By.cssSelector(".product-grid"));
        clickOnElement(By.xpath("//*[@data-productid='74']"));
        clickOnElement(By.id("add-to-cart-button-74"));

        // Переход к странице второго товара и настройка атрибута
        clickOnElement(By.xpath("//*[@data-productid='72']"));
        // Добавление второго товара в корзину
        clickOnElement(By.id("add-to-cart-button-72"));

        // Ожидание появления уведомления о добавлении товара в корзину
        WebElement notification = wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".bar-notification.success")));
        Assertions.assertTrue(notification.getText().contains("The product has been added to your shopping cart"),
                "Уведомление о добавлении товара в корзину не появилось.");

        // Ожидание появления и обновления количества товаров в корзине
        WebElement cartQtyElement = wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".cart-qty")));

        // Извлечение и преобразование текста из элемента корзины
        String countStrElm = cartQtyElement.getText(); // Например, "(2)"
        String countStr = countStrElm.replace("(", "").replace(")", "");
        int countInCart = Integer.parseInt(countStr);

        // Ожидаемое значение количества товаров в корзине
        int expectedCount = 2;

        // Проверка, что количество товаров в корзине равно ожидаемому значению
        Assertions.assertEquals(expectedCount, countInCart,
                "Количество товаров в корзине не совпадает с ожидаемым. Фактическое: " + countInCart);

        // Вывод сообщения о результате
        System.out.println("Тест пройден успешно: в корзине " + countInCart + " товар(ов).");
    }
}