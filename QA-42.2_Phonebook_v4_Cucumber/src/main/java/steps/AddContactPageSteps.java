package steps;
import io.cucumber.java.ru.И;
import io.cucumber.java.ru.Тогда;
import model.Contact;
import pages.BasePage;

import java.util.Map;

import static core.AppManager.TEST_CONTACT;
import static core.AppManager.addContactPage;

public class AddContactPageSteps extends BasePage {

    @И("нажимаем на кнопку 'Save'")
    public void clickOnSaveButton() {
        addContactPage.clickOnSaveButton();
    }

    @Тогда("заполняем данные тестового контакта для добавления в записную книжку")
    public void fillAddContact() {
        addContactPage.fillAddContact(TEST_CONTACT);
    }
}