package restAssuredTest;
import ait.phonebook.dto.AuthenticationBodyDto;
import ait.phonebook.dto.TokenDto;
import io.restassured.http.ContentType;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
//Описание класса: Класс используется для автоматических тестов API с использованием библиотеки RestAssured.
// Он помогает тестировать REST API и проверять их корректность.
//
//Методы:
//
//Тестирование GET, POST запросов — методы, которые проверяют, корректно ли API отвечает на эти запросы.
//Проверка ответов — методы для анализа ответов API (например, статус код, тело ответа).
public class RestAssuredTests {

    AuthenticationBodyDto authenticationBodyDto = AuthenticationBodyDto.builder()
            .username("manuel@gm.com")
            .password("Manuel1234$").
            build();


    @Test
    void test1() {
        String url = "https://contactapp-telran-backend.herokuapp.com/v1/user/login/usernamepassword";
/**
 * Четыре основных REST-assured метода
 *
 * Given — позволяет узнать, что было передано в запросе.
 * When — с каким методом и на какой эндпойнт отправляем запрос.
 * Then — как проверяется пришедший ответ.
 *
 */

        TokenDto tokenDto = given()//Начало запроса
                .contentType(ContentType.JSON)//Указываем тип контента
                .body(authenticationBodyDto)// Тело запроса (POST & PUT)
                .when()// с каким методом и на какой эндпойнт отправляем запрос.
                .log().all()// логирование в консоль
                .post(url)// на какой адрес (или endpoint) отправляем запрос
                .then()// Что делаем когда получаем ответ
                .log().all()//Логируем ответ
                .assertThat().statusCode(200)//Проверяем статус код пришедшего ответа
                .extract().response().as(TokenDto.class);//извлекаем ответ.

        System.out.println();
        System.out.println(tokenDto.getToken());
    }
}