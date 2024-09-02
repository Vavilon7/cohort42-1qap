package com.ait.qa;

import core.DataProviders;
import model.Contact;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pages.NavigationPanel;

import static core.AppManager.TEST_CONTACT;
import static core.AppManager.addContactPage;
import static core.AppManager.contactPage;
import static core.AppManager.navigationPanel;

public class ContactTests extends BaseTest {
    @BeforeMethod
    void precondition() {
        if (!navigationPanel.isLoginLinkPresent()) {
            navigationPanel.clickOnSigOutButton();
        }
        new NavigationPanel()
                .clickOnLoginLink()
                .loginTestUser();

    }

    @Test(description = "Проверка успешного добавления контакт", groups = {"Positive"})
    void successAddContactTest() {
        navigationPanel
                .clickOnAddLink()
                .fillAddContact(TEST_CONTACT)
                .clickOnSaveButton()
                .waitInSeconds(3);

        Assert.assertTrue(contactPage.contactIsPresent(TEST_CONTACT), "Контакт не добавлен");
        contactPage.removeContact(TEST_CONTACT);
    }

    @Test(description = "Проверка добавления контакта с не корректным номером телефона", groups = {"Negative"})
    void failedAddContactTest() {
        navigationPanel.clickOnAddLink();
        Contact contact = new Contact("Test", "Testoviy", "123456", "test@test.com", "testAddress", "TestDesc");
        addContactPage
                .fillAddContact(contact)
                .clickOnSaveButton();
        Assert.assertTrue(addContactPage.getAlertText().contains("Phone not valid"), "Сообщение в уведомлении не содержит 'Phone not valid' ");
    }

    @Test(dataProvider = "addNewContact", dataProviderClass = DataProviders.class)
    void successAddContactTestDataProvider(Contact contact) {
        navigationPanel
                .clickOnAddLink()
                .fillAddContact(contact)
                .clickOnSaveButton()
                .waitInSeconds(3);
        Assert.assertTrue(contactPage.contactIsPresent(contact), "Контакт не добавлен");
        contactPage.removeContact(contact);
    }

    @Test(dataProvider = "addNewContactFromCsvFile", dataProviderClass = DataProviders.class)
    void successAddContactTestDataProviderFromCsv(Contact contact, String result) {
        navigationPanel
                .clickOnAddLink()
                .fillAddContact(contact)
                .clickOnSaveButton();
        if (result.equals("passed")) {
            contactPage.waitInSeconds(3);
            Assert.assertTrue(contactPage.contactIsPresent(contact), "Контакт не добавлен");
            contactPage.removeContact(contact);
        } else {
            Assert.assertTrue(addContactPage.getAlertText().contains("not valid:"), "Сообщение в уведомлении не содержит 'not valid'");
        }

    }
}
