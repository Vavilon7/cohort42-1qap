package com.phonebook.tests;

import data.Contact;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class ContactsPageTests extends BaseTest{
    private final Contact TEST_CONTACT = new Contact("Test", "Testoviy", "1234567890", "test@test.com", "testAddress", "TestDesc");

    @BeforeEach
    void preconditions() {
        loginTestUser();
    }

    @Test
    @DisplayName("Успешное создание контакта")
    void addTest1() {
        clickOnElement(By.cssSelector("[href='/add']"));
        fillAddContact(TEST_CONTACT);
        clickOnElement(By.xpath("//*[text()='Save']"));
        assertTrue(getElementBy(By.xpath(String.format("//h2[text()='%s']", TEST_CONTACT.getName()))).isDisplayed(), String.format("Не добавлен контакт с именем - %s, фамилией - %s, и номером телефона - %s", TEST_CONTACT.getName(), TEST_CONTACT.getLastName(), TEST_CONTACT.getPhone()));
        System.out.println(String.format("Добавлен контакт с именем - %s, фамилией - %s, и номером телефона - %s", TEST_CONTACT.getName(), TEST_CONTACT.getLastName(), TEST_CONTACT.getPhone()));
        removeContact(TEST_CONTACT);
    }

    private void fillAddContact(Contact contact) {
        fillInputField(By.cssSelector("[placeholder='Name']"), contact.getName());
        fillInputField(By.cssSelector("[placeholder='Last Name']"), contact.getLastName());
        fillInputField(By.cssSelector("[placeholder='Phone']"), contact.getPhone());
        fillInputField(By.cssSelector("[placeholder='email']"), contact.getEmail());
        fillInputField(By.cssSelector("[placeholder='Address']"), contact.getAddress());
        fillInputField(By.cssSelector("[placeholder='description']"), contact.getDescription());
    }

    private void removeContact(Contact contact) {
        clickOnElement(By.xpath(String.format("//h2[text()='%s']", contact.getName())));
        clickOnElement(By.xpath("//*[text()='Remove']"));
        waitInSeconds(2);
    }

}
