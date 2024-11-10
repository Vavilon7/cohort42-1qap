package pages;
import io.qameta.allure.Step;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;

public class NavigationPanel extends BasePage{

    @FindBy(xpath = "//*[text()='LOGIN']")
    List<WebElement> loginLink;

    @FindBy(css = "[href='/home']")
    List<WebElement> homeLink;

    @FindBy(css = "[href='/add']")
    List<WebElement> addLink;

    @FindBy(xpath = "//*[text()='Sign Out']")
    List<WebElement> signOut;

    @FindBy(xpath = "//*[text()='Sign Out']")
    WebElement signOutButton;

    @Step("Проверяем наличие кнопки 'Sign Out'")
    public boolean isSignOutButtonPresent() {
        return isElementPresent(signOut);
    }

    @Step("Нажимаем на кнопку 'Sign Out'")
    public LoginPage clickOnSigOutButton() {
        clickOnElement(signOutButton);
        return new LoginPage();
    }

    @Step("Нажимаем на кнопку 'ADD'")
    public AddContactPage clickOnAddLink() {
        clickOnElement(addLink.get(0));
        return new AddContactPage();
    }

    @Step("Нажимаем на кнопку 'HOME'")
    public HomePage clickOnHomeLink() {
        clickOnElement(homeLink.get(0));
        return new HomePage();
    }

    @Step("Проверяем наличие кнопки 'LOGIN'")
    public boolean isLoginLinkPresent() {
        return isElementPresent(loginLink);
    }

    @Step("Нажимаем на кнопку 'LOGIN' в заголовке")
    public LoginPage clickOnLoginLink() {
        clickOnElement(loginLink.get(0));
        return new LoginPage();
    }

}
