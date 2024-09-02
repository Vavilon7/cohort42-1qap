package page_helpers;

import model.Contact;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

public class ContactHelper extends BaseHelper {
    public ContactHelper(WebDriver driver, WebDriverWait wait) {
        super(driver, wait);
    }

    public void clickOnAddLink() {
        clickOnElement(By.cssSelector("[href='/add']"));
    }

    public void clickOnSaveButton() {
        clickOnElement(By.xpath("//*[text()='Save']"));
    }

    public void fillAddContact(Contact contact) {
        fillInputField(By.cssSelector("[placeholder='Name']"), contact.getName());
        fillInputField(By.cssSelector("[placeholder='Last Name']"), contact.getLastName());
        fillInputField(By.cssSelector("[placeholder='Phone']"), contact.getPhone());
        fillInputField(By.cssSelector("[placeholder='email']"), contact.getEmail());
        fillInputField(By.cssSelector("[placeholder='Address']"), contact.getAddress());
        fillInputField(By.cssSelector("[placeholder='description']"), contact.getDescription());
    }

    public boolean contactIsPresent(Contact contact) {
        return isElementPresent(By.xpath(String.format("//h2[text()='%s']", contact.getName())));
    }

    public void removeContact(Contact contact) {
        clickOnElement(By.xpath(String.format("//h2[text()='%s']", contact.getName())));
        clickOnElement(By.xpath("//*[text()='Remove']"));
        waitInSeconds(2);
    }
}
