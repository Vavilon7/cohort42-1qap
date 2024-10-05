package pages;
import io.qameta.allure.Step;
import model.Contact;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;

public class ContactPage extends BasePage {

    @FindBy(xpath = " //h2")
    List<WebElement> contactNameList;

    @FindBy(xpath = "//*[text()='Remove']")
    WebElement removeButton;

    @Step("Проверяем что контакт добавился")
    public boolean contactIsPresent(String name) {
        return getContactElementByName(name).isDisplayed();
    }

    @Step("Удаляем контакт")
    public ContactPage removeContact(String name) {
        clickOnElement(getContactElementByName(name));
        clickOnElement(removeButton);
        waitInSeconds(2);
        return this;
    }

    public WebElement getContactElementByName(String name){
        for (WebElement nameElement : contactNameList) {
            if (nameElement.getText().equals(name)) {
                return nameElement;
            }
        }
        return null;
    }
}