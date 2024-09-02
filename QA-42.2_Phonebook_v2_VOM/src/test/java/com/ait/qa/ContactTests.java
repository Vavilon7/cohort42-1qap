package com.ait.qa;

import model.Contact;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import static core.AppManager.TEST_CONTACT;

public class ContactTests extends BaseTest{
    @BeforeMethod
    void precondition() {
        if (!app.getUserHelper().isLoginLinkPresent()) {
            app.getUserHelper().clickOnSigOutButton();
        }
        app.getUserHelper().loginTestUser();
    }

    @Test(description = "Проверка успешного добавления контакт", groups = {"Positive"})
    void successAddContactTest() {
        app.getContactHelper().clickOnAddLink();
        app.getContactHelper().fillAddContact(TEST_CONTACT);
        app.getContactHelper().clickOnSaveButton();
        app.getContactHelper().waitInSeconds(3);
        Assert.assertTrue(app.getContactHelper().contactIsPresent(TEST_CONTACT), "Контакт не добавлен");
        app.getContactHelper().removeContact(TEST_CONTACT);
        app.getContactHelper().waitInSeconds(3);
    }

    @Test(description = "Проверка добавления контакта с не корректным номером телефона", groups = {"Negative"})
    void failedAddContactTest() {
        app.getContactHelper().clickOnAddLink();
        Contact contact = new Contact("Test", "Testoviy", "123456", "test@test.com", "testAddress", "TestDesc");
        app.getContactHelper().fillAddContact(contact);
        app.getContactHelper().clickOnSaveButton();
        Assert.assertTrue(app.getContactHelper().getAlertText().contains("Phone not valid"), "Сообщение в уведомлении не содержит 'Phone not valid' ");
    }
}
