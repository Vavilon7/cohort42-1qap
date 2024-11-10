package ait.phonebook.utils;
import java.io.IOException;
import java.util.Properties;
//Описание класса: TestProperties используется для загрузки и работы с тестовыми настройками.
// Обычно такие классы содержат конфигурационные параметры, такие как URL сервера, таймауты и другие свойства,
// которые необходимы для тестирования.
//
//Методы:
//
//Загрузка свойств — метод для чтения конфигурации из файла.
//Получение значения свойства — метод для извлечения конкретного параметра из настроек.
public class TestProperties {

    private final Properties properties = new Properties();
    private static TestProperties INSTANCE = null;

    private TestProperties() {
        try {
            properties.load(TestProperties.class.getClassLoader().getResourceAsStream("app.properties"));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static TestProperties getINSTANCE() {
        if (INSTANCE == null) {
            INSTANCE = new TestProperties();
        }
        return INSTANCE;
    }

    public Properties getProperties() {
        return properties;
    }
}