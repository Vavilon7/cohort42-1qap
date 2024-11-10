package phonebook;

import org.junit.jupiter.api.*;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

@Tag("@LoginTests")
@Tag("@REGRESS")
public class LoginTests extends BaseTest{

    @BeforeEach
    void preconditions() {
        driver.get(URL_PHONE_BOOK);
    }


    @Test
    @Tag("@Positive")
    @Tag("@SMOKE")
    @DisplayName("Проверка успешной авторизации")
    void loginTest1() {
        clickOnElement(By.xpath("//*[text()='LOGIN']"));
        fillInputField(By.name("email"), "manuel@gm.com");
        fillInputField(By.name("password"), "Manuel1234$");
        clickOnElement(By.name("login"));
        Assertions.assertTrue(getElementBy(By.xpath("//*[text()='Sign Out']")).isDisplayed(), "Отсутствует кнопка выхода ");
    }

    @Test
    @Tag("@NEGATIVE")
    @Tag("@SMOKE")
    @DisplayName("Проверка ввода неверного логина")
    void loginTest2() {
        clickOnElement(By.xpath("//*[text()='LOGIN']"));
        fillInputField(By.name("email"), "manuelgm.com");
        fillInputField(By.name("password"), "Manuel1234$");
        clickOnElement(By.name("login"));
        Alert alert = getAlert();
        Assertions.assertEquals("Wrong email or password", alert.getText(), "Отсутствует кнопка выхода ");
        alert.accept();

        WebElement errorTextElement = getElementBy(By.xpath("//*[contains(text(), '401')]"));
        Assertions.assertEquals("Login Failed with code 401", errorTextElement.getText(), "Текст ошибки не соответствует ожидаемому");
    }

    @Test
    @Tag("@NEGATIVE")
    @DisplayName("Проверка ввода неверного пароля")
    void loginTest3() {
        clickOnElement(By.xpath("//*[text()='LOGIN']"));
        fillInputField(By.name("email"), "manuel@gm.com");
        fillInputField(By.name("password"), "Manuel");
        clickOnElement(By.name("login"));
        Alert alert = getAlert();
        Assertions.assertEquals("Wrong email or password", alert.getText(), "Отсутствует кнопка выхода ");
        alert.accept();

        WebElement errorTextElement = getElementBy(By.xpath("//*[contains(text(), '401')]"));
        Assertions.assertEquals("Login Failed with code 401", errorTextElement.getText(), "Текст ошибки не соответствует ожидаемому");
    }

}
