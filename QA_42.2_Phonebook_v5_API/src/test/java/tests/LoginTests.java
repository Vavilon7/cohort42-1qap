package tests;

import ait.phonebook.dto.AuthenticationBodyDto;
import ait.phonebook.dto.ErrorMessageDto;
import ait.phonebook.dto.TokenDto;
import ait.phonebook.utils.HttpUtils;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static ait.phonebook.utils.HttpUtils.LOGIN_ENDPOINT;
import static ait.phonebook.utils.HttpUtils.REGISTRATION_ENDPOINT;
import static ait.phonebook.utils.Utils.getRandomEmail;
import static ait.phonebook.utils.Utils.isNullOrEmpty;
//Описание класса: Класс для тестирования логина. Он проверяет, может ли пользователь успешно войти в систему,
// и как обрабатываются ошибки.
//
//Методы:
//
//Тест успешного логина — метод, который проверяет, может ли пользователь успешно пройти аутентификацию.
//Тест ошибки логина — метод, проверяющий обработку ошибок при некорректных данных для входа.
public class LoginTests extends BaseTest {

    @Test
    @DisplayName("Проверка успешной авторизации")
    void test1() {
        TokenDto tokenDto = HttpUtils.postResponse(getTestUserLoginBody(), LOGIN_ENDPOINT, 200, TokenDto.class);

        Assertions.assertFalse(isNullOrEmpty(tokenDto.getToken()), "Пришел пустой токен");
    }

    @Test
    @DisplayName("Проверка авторизации с не корректным email")
    void test2() {
        AuthenticationBodyDto loginRqBody = getTestUserLoginBody();
        loginRqBody.setUsername("manuelgm.com");

        ErrorMessageDto errorMessageDto = HttpUtils.postResponse(loginRqBody, LOGIN_ENDPOINT, 401, ErrorMessageDto.class);

        Assertions.assertEquals("Login or Password incorrect", errorMessageDto.getMessage(), "Текст ошибки не соответствует ожидаемому");
        Assertions.assertEquals("Unauthorized", errorMessageDto.getError(), "Тип ошибки не соответствует ожидаемому");
    }

    @Test
    @DisplayName("Проверка авторизации с не корректным password")
    void test6() {
        AuthenticationBodyDto loginRqBody = getTestUserLoginBody();
        loginRqBody.setPassword("qwerty");

        ErrorMessageDto errorMessageDto = HttpUtils.postResponse(loginRqBody, LOGIN_ENDPOINT, 401, ErrorMessageDto.class);

        Assertions.assertEquals("Login or Password incorrect", errorMessageDto.getMessage(), "Текст ошибки не соответствует ожидаемому");
        Assertions.assertEquals("Unauthorized", errorMessageDto.getError(), "Тип ошибки не соответствует ожидаемому");
    }

    @Test
    @DisplayName("Проверка успешной регистрации")
    void test3() {
        AuthenticationBodyDto registrationRqBody = AuthenticationBodyDto.builder()
                .username(getRandomEmail())
                .password("QwertY123!")
                .build();

        TokenDto tokenDto = HttpUtils.postResponse(registrationRqBody, REGISTRATION_ENDPOINT, 200, TokenDto.class);
        Assertions.assertFalse(isNullOrEmpty(tokenDto.getToken()), "Пришел пустой токен");
    }

    @Test
    @DisplayName("Проверка регистрации с уже существующим email")
    void test4() {
        AuthenticationBodyDto registrationRqBody = getTestUserLoginBody();

        ErrorMessageDto errorMessageDto = HttpUtils.postResponse(registrationRqBody, REGISTRATION_ENDPOINT, 409, ErrorMessageDto.class);

        Assertions.assertEquals("User already exists", errorMessageDto.getMessage(), "Текст ошибки не соответствует ожидаемому");
        Assertions.assertEquals("Conflict", errorMessageDto.getError(), "Тип ошибки не соответствует ожидаемому");
    }

    @Test
    @DisplayName("Проверка регистрации с не корректным email")
    void test5() {
        AuthenticationBodyDto loginRqBody = AuthenticationBodyDto.builder()
                .username("login.com")
                .password("QwertY123!")
                .build();

        ErrorMessageDto errorMessageDto = HttpUtils.postResponse(loginRqBody, REGISTRATION_ENDPOINT, 400, ErrorMessageDto.class);
        String msg = errorMessageDto.getMessage().toString();
        Assertions.assertTrue(msg.contains("must be a well-formed email address"), "Текст ошибки не соответствует ожидаемому");
        Assertions.assertEquals("Bad Request", errorMessageDto.getError(), "Тип ошибки не соответствует ожидаемому");
    }
}