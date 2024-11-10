package httpClient;

import ait.phonebook.dto.AuthenticationBodyDto;
import ait.phonebook.dto.ErrorMessageDto;
import ait.phonebook.dto.TokenDto;
import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;

import org.apache.http.HttpResponse;
import org.apache.http.client.fluent.Response;
import org.apache.http.client.fluent.Request;
import org.apache.http.entity.ContentType;
import org.junit.jupiter.api.Test;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

//Описание класса: Этот класс скорее всего выполняет тестирование аутентификации через HTTP запросы.
//
//Методы:
//
//Тест логина — метод, который проверяет, проходит ли аутентификация с определёнными данными.
//Проверка ошибок — метод для проверки, как система обрабатывает ошибки при некорректных данных.
public class LoginTestHttp {
    //Этот тест отправляет POST-запрос для авторизации пользователя с логином и паролем.
    // Полученный JSON-ответ парсится для извлечения токена, который затем выводится на консоль.
    @Test
    void test1() throws IOException {
        // Тело запроса в виде JSON, содержащее логин и пароль пользователя.
        String body = "{\n" +
                "  \"username\": \"manuel@gm.com\",\n" +
                "  \"password\": \"Manuel1234$\"\n" +
                "}";
        // URL для API-запроса логина.
        String url = "https://contactapp-telran-backend.herokuapp.com/v1/user/login/usernamepassword";
        // Создание POST-запроса с указанным URL и телом запроса.
        Response response = Request
                .Post(url)
                .bodyString(body, ContentType.APPLICATION_JSON)
                .execute();
        // Получение ответа в виде строки.
        String responseJson = response.returnContent().asString();
        System.out.println(responseJson);
// Парсинг JSON-ответа и получение значения токена.
        JsonElement element = JsonParser.parseString(responseJson);
        JsonElement token = element.getAsJsonObject().get("token");

        System.out.println(token.getAsString());
    }
// Этот тест отправляет POST-запрос для авторизации, используя объект AuthenticationBodyDto для передачи логина и пароля.
// Полученный JSON-ответ преобразуется в объект TokenDto, и токен выводится на консоль.
    @Test
    void test2() throws IOException {
        // Создание объекта с данными для авторизации, включая логин и пароль.
        AuthenticationBodyDto authenticationBodyDto = AuthenticationBodyDto.builder()
                .username("manuel@gm.com")
                .password("Manuel1234$").
                build();
        // Создание экземпляра Gson для сериализации объекта в JSON.
        Gson gson = new Gson();
        // URL для API-запроса логина.
        String url = "https://contactapp-telran-backend.herokuapp.com/v1/user/login/usernamepassword";
        // Создание POST-запроса с телом запроса в формате JSON.
        Response response = Request
                .Post(url)
                .bodyString(gson.toJson(authenticationBodyDto), ContentType.APPLICATION_JSON)
                .execute();
        // Получение ответа в виде строки и преобразование его в объект TokenDto.
        String responseJson = response.returnContent().asString();
        TokenDto tokenDto = gson.fromJson(responseJson, TokenDto.class);
        // Вывод токена на консоль.
        System.out.println("===============================");
        System.out.println();
        System.out.println(tokenDto.getToken());
        System.out.println();
        System.out.println("===============================");
    }
//Этот тест проверяет сценарий неудачной авторизации, где предоставлен неправильный пароль.
// В ответе ожидается ошибка, которая читается из HTTP-ответа и преобразуется в объект ErrorMessageDto. Затем ошибка выводится на консоль.
    @Test
    void test3() throws IOException {
        AuthenticationBodyDto authenticationBodyDto = AuthenticationBodyDto.builder()
                .username("manuel@gm.com")
                .password("Manuel123").
                build();

        Gson gson = new Gson();

        String url = "https://contactapp-telran-backend.herokuapp.com/v1/user/login/usernamepassword";
        Response response = Request
                .Post(url)
                .bodyString(gson.toJson(authenticationBodyDto), ContentType.APPLICATION_JSON)
                .execute();

        HttpResponse httpResponse = response.returnResponse();
        System.out.println(httpResponse);

        BufferedReader reader = new BufferedReader(new InputStreamReader(httpResponse.getEntity().getContent()));
        String line;
        StringBuilder sb = new StringBuilder();
        while ((line = reader.readLine()) != null) {
            sb.append(line);
        }


        ErrorMessageDto errorMessageDto = gson.fromJson(sb.toString(), ErrorMessageDto.class);
        System.out.println("===============================");
        System.out.println();
        System.out.println(errorMessageDto.toString());
        System.out.println();
        System.out.println("===============================");
    }
}