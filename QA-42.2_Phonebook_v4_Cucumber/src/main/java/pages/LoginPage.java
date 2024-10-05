package pages;
import io.qameta.allure.Step;
import model.User;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import static core.AppManager.TEST_USER;

public class LoginPage extends BasePage {

    @FindBy(name = "login")
    WebElement loginButton;

    @FindBy(name = "registration")
    WebElement registrationButton;

    @FindBy(name = "email")
    WebElement emailField;

    @FindBy(name = "password")
    WebElement passwordField;

    @FindBy(xpath = "//*[contains(text(), 'Login Failed')]")
    WebElement errorLoginMessage;


    @Step("Нажимаем на кнопку 'login'")
    public LoginPage clickOnLoginButton() {
        clickOnElement(loginButton);
        return this;
    }

    @Step("Нажимаем на кнопку 'registration'")
    public LoginPage clickOnRegistrationButton() {
        clickOnElement(registrationButton);
        return this;
    }

    @Step("Заполняем данные пользователя")
    public LoginPage fillLoginRegisterForm(User user) {
        fillInputField(emailField, user.getEmail());
        fillInputField(passwordField, user.getPassword());
        return this;
    }

    @Step("Получаем сообщение об ошибке на странице")
    public String getMessageLoginFailed() {
        return getElement(errorLoginMessage).getText();
    }

    @Step("Авторизуемся под тестовым пользователем")
    public LoginPage loginTestUser() {
        fillLoginRegisterForm(TEST_USER);
        clickOnLoginButton();
        return this;
    }
}