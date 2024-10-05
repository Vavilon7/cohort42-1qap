package pages;
import io.qameta.allure.Step;
import model.Contact;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class AddContactPage extends BasePage {

    @FindBy(xpath = "//*[text()='Save']")
    WebElement saveButton;

    @FindBy(css = "[placeholder='Name']")
    WebElement nameField;

    @FindBy(css = "[placeholder='description']")
    WebElement descriptionField;

    @FindBy(css = "[placeholder='Last Name']")
    WebElement lastNameField;

    @FindBy(css = "[placeholder='Phone']")
    WebElement phoneField;

    @FindBy(css = "[placeholder='email']")
    WebElement emailField;

    @FindBy(css = "[placeholder='Address']")
    WebElement addressField;

    @Step("Нажимаем на кнопку 'Save'")
    public AddContactPage clickOnSaveButton() {
        clickOnElement(saveButton);
        return this;
    }

    @Step("Заполняем данные нового контакта")
    public AddContactPage fillAddContact(Contact contact) {
        fillInputField(nameField, contact.getName());
        fillInputField(lastNameField, contact.getLastName());
        fillInputField(phoneField, contact.getPhone());
        fillInputField(emailField, contact.getEmail());
        fillInputField(addressField, contact.getAddress());
        fillInputField(descriptionField, contact.getDescription());
        return this;
    }
}