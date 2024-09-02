import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.security.SecureRandom;
import java.util.Random;

@Tag("@LoginTests")
@Tag("@REGRESS")
public class LoginPageTests extends BaseTest {

    @Test
    @Tag("@Positive")
    @Tag("@SMOKE")
    @DisplayName("Проверка успешной авторизации")
    void loginTest1() {
        login("manuel@gm.com", "Manuel1234$", LOGIN);
        Assertions.assertTrue(getElementBy(By.xpath("//*[text()='Sign Out']")).isDisplayed(), "Отсутствует кнопка выхода ");
    }

    @Test
    @Tag("@NEGATIVE")
    @Tag("@SMOKE")
    @DisplayName("Проверка ввода неверного логина")
    void loginTest2() {
        login("manuelgm.com", "Manuel1234$", LOGIN);
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
        login("manuel@gm.com", "Manue$", LOGIN);
        Alert alert = getAlert();
        Assertions.assertEquals("Wrong email or password", alert.getText(), "Отсутствует кнопка выхода ");
        alert.accept();

        WebElement errorTextElement = getElementBy(By.xpath("//*[contains(text(), '401')]"));
        Assertions.assertEquals("Login Failed with code 401", errorTextElement.getText(), "Текст ошибки не соответствует ожидаемому");
    }


    @Test
    @Tag("@Positive")
    @Tag("@SMOKE")
    @DisplayName("Проверка успешной регистрации")
    void loginTest4() {
        login(randomEmail(), "Manuel1234$", REGISTRATION);
        Assertions.assertTrue(getElementBy(By.xpath("//*[text()='Sign Out']")).isDisplayed(), "Отсутствует кнопка выхода ");
    }

    private static String randomEmail() {
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
}