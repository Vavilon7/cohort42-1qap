package tests;

import ait.phonebook.dto.AuthenticationBodyDto;
import ait.phonebook.dto.ContactDto;
import ait.phonebook.utils.TestProperties;
import io.restassured.RestAssured;
import org.junit.jupiter.api.BeforeAll;

import java.util.Properties;
//Описание класса: BaseTest — это базовый класс для тестов, который может содержать общие настройки
// и подготовку окружения для выполнения тестов.
//
//Методы:
//
//Подготовка окружения — метод для настройки тестовой среды перед запуском тестов.
//Завершение тестов — метод, который выполняется после завершения всех тестов.

public class BaseTest {

    public static Properties properties = TestProperties.getINSTANCE().getProperties();

    @BeforeAll
    public static void load() {
        //Указываем в настройках RestAssured наш адрес для запросов
        //https://contactapp-telran-backend.herokuapp.com
        RestAssured.baseURI = properties.getProperty("base.url");
        //Указываем в настройках RestAssured наш путь для запросов
        //v1
        RestAssured.basePath = properties.getProperty("base.version");
        //https://contactapp-telran-backend.herokuapp.com/v1  - дальше это эндпоинты для работы /user/login/usernamepassword";
    }

    static AuthenticationBodyDto getTestUserLoginBody() {
        return AuthenticationBodyDto.builder()
                .username(properties.getProperty("testuser.name"))
                .password(properties.getProperty("testuser.pass"))
                .build();
    }

    static AuthenticationBodyDto getDeleteTestUserLoginBody() {
        return AuthenticationBodyDto.builder()
                .username(properties.getProperty("delete_user.name"))
                .password(properties.getProperty("testuser.pass"))
                .build();
    }

    static ContactDto getContactBody() {
        return ContactDto.builder()
                .name(properties.getProperty("contact.name"))
                .lastName(properties.getProperty("contact.lastname"))
                .phone(properties.getProperty("contact.phone"))
                .email(properties.getProperty("contact.email"))
                .address(properties.getProperty("contact.address"))
                .description(properties.getProperty("contact.description"))
                .build();
    }
}