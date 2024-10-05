package steps;
import io.cucumber.java.ru.И;
import io.cucumber.java.ru.Тогда;
import model.Contact;
import org.junit.jupiter.api.Assertions;
import pages.BasePage;

import static core.AppManager.contactPage;

public class ContactPageSteps extends BasePage {

    @Тогда("проверяем, что контакт - \"(.+)\" добавился$")
    public void contactIsPresent(String name) {
        Assertions.assertTrue(contactPage.contactIsPresent(name));
    }

    @И("удаляем контакт - {word}")
    public void removeContact(String name) {
        contactPage.removeContact(name);
    }
}