package tests;
import ait.phonebook.dto.*;
import ait.phonebook.utils.HttpUtils;
import io.qameta.allure.internal.shadowed.jackson.annotation.JsonTypeInfo;
import org.junit.jupiter.api.*;

import java.util.Set;

import static ait.phonebook.utils.HttpUtils.*;
import static ait.phonebook.utils.Utils.isNullOrEmpty;
//Описание класса: Этот класс, судя по названию, тестирует функционал, связанный с контактами.
// Он проверяет, корректно ли добавляются, редактируются и удаляются контакты.
//
//Методы:
//
//Тесты CRUD операций — методы для тестирования создания, чтения, обновления и удаления контактов.
public class ContactsTests extends BaseTest {

    private String token;

    @BeforeEach
    void precondition(TestInfo testInfo) {
        Set<String> tags = testInfo.getTags();
        if (tags.contains("@ADD")) {
            token = HttpUtils.postResponse(getDeleteTestUserLoginBody(), LOGIN_ENDPOINT, 200, TokenDto.class).getToken();
            HttpUtils.deleteResponse(token, CONTACTS_ENDPOINT + "/clear", 200, ResponseMessageDto.class);
        } else if (tags.contains("@DELETE")) {
            token = HttpUtils.postResponse(getDeleteTestUserLoginBody(), LOGIN_ENDPOINT, 200, TokenDto.class).getToken();
            postResponseWithToken(getContactBody(), CONTACTS_ENDPOINT, 200, token, ResponseMessageDto.class);
        } else {
            token = HttpUtils.postResponse(getTestUserLoginBody(), LOGIN_ENDPOINT, 200, TokenDto.class).getToken();
        }
    }


    @Test
    @DisplayName("Проверка получения списка контактов у авторизованного пользователя")
    void test1() {
        ContactsDto contacts = getResponse(token, CONTACTS_ENDPOINT, 200, ContactsDto.class);
        Assertions.assertFalse(contacts.getContacts().isEmpty());
    }

    @Test
    @DisplayName("Проверка получения списка контактов без авторизации")
    void test2() {
        ErrorMessageDto errorMessageDto = getResponse(null, CONTACTS_ENDPOINT, 401, ErrorMessageDto.class);
        Assertions.assertEquals("Unauthorized", errorMessageDto.getError(), "Тип ошибки не соответствует ожидаемому");
    }

    @Test
    @Tag("@ADD")
    @DisplayName("Проверка добавления контакта у авторизованного пользователя")
    void test3() {
        ResponseMessageDto responseMessageDto = postResponseWithToken(getContactBody(), CONTACTS_ENDPOINT, 200, token, ResponseMessageDto.class);
        Assertions.assertFalse(isNullOrEmpty(responseMessageDto.getMessage()));
        String id = responseMessageDto.getMessage().replace("Contact was added! ID: ", "");

        ContactsDto contacts = getResponse(token, CONTACTS_ENDPOINT, 200, ContactsDto.class);
        Assertions.assertTrue(contacts.getContacts().size() == 1);
        Assertions.assertEquals(id, contacts.getContacts().get(0).getId(), "Добавлен контакт с другим id");
    }

    @Test
    @Tag("@DELETE")
    @DisplayName("Проверка удаления контакта у авторизованного пользователя по id")
    void test4() throws Exception {
        ContactsDto contacts = getResponse(token, CONTACTS_ENDPOINT, 200, ContactsDto.class);
        Assertions.assertTrue(contacts.getContacts().size() == 1);
        String id = contacts.getContacts().get(0).getId();

        ResponseMessageDto responseMessageDto = deleteResponse(token, CONTACTS_ENDPOINT + "/" + id, 200, ResponseMessageDto.class);
        Assertions.assertEquals("Contact was deleted!", responseMessageDto.getMessage(), "Сообщение об удалении не соответствует ожидаемому");

        contacts = getResponse(token, CONTACTS_ENDPOINT, 200, ContactsDto.class);
        //TODO написать вторую проверку по списку
        Assertions.assertTrue(contacts.getContacts().size() == 0);

        //Альтернативная проверка
//        for (ContactDto contact : contacts.getContacts()) {
//            if (contact.getId().equals(id)) {
////                throw new Exception("Контакт не удалён");
//                //AutotestExc
//                Assertions.assertFalse(contact.getId().equals(id), "Контакт не удалён");
//            }
//        }
    }

}
