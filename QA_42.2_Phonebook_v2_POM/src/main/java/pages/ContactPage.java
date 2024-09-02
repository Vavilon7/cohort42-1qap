package pages;

import io.qameta.allure.Step;
import model.Contact;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;

public class ContactPage extends BasePage {
    @FindBy(xpath = " //h2")
    List<WebElement> contactNameList;

    @FindBy(xpath = "//*[text()='Remove']")
    WebElement removeButton;

    @Step("Проверяем что контакт добавился")
    public boolean contactIsPresent(Contact contact) {
        return getContactElementByName(contact).isDisplayed();
    }

    @Step("Удаляем контакт")
    public ContactPage removeContact(Contact contact) {
        clickOnElement(getContactElementByName(contact));
        clickOnElement(removeButton);
        waitInSeconds(2);
        return this;
    }

    public WebElement getContactElementByName(Contact contact) {
        for (WebElement nameElement : contactNameList) {
            if (nameElement.getText().equals(contact.getName())) {
                return nameElement;
            }
        }
        return null;
    }

}
