package steps;
import io.cucumber.java.ru.И;
import io.cucumber.java.ru.Когда;
import io.cucumber.java.ru.Тогда;
import model.User;
import org.junit.jupiter.api.Assertions;
import pages.BasePage;
import java.util.Map;
import static core.AppManager.TEST_USER;
import static core.AppManager.loginPage;

public class LoginPageSteps {

    @Тогда("нажимаем на кнопку 'login'")
    public void clickOnLoginButton() {
        loginPage.clickOnLoginButton();
    }

    @Тогда("нажимаем на кнопку 'registration'")
    public void clickOnRegistrationButton() {
        loginPage.clickOnRegistrationButton();
    }

    @Тогда("заполняем данные пользователя")
    public void fillLoginRegisterForm(Map<String, String> userFields) {
        User user = new User(userFields.get("Name"), userFields.get("Password"));
        loginPage.fillLoginRegisterForm(user);
    }

    @Тогда("заполняем данные пользователя с случайным e-mail")
    public void fillLoginRegisterFormRandomEmail() {
        User user = new User(BasePage.getRandomEmail(), TEST_USER.getPassword());
        loginPage.fillLoginRegisterForm(user);
    }

    @И("проверяем текст ошибки - \"(.+)\"$")
    public void checkMessageLoginFailed(String msg) {
        Assertions.assertEquals(msg, loginPage.getMessageLoginFailed(), "Текст ошибки на странице не соответствует ожидаемому");
    }

    @И("проверяем текст ошибки {string}")
    public void checkMessageLoginFailedString(String msg) {
        Assertions.assertEquals(msg, loginPage.getMessageLoginFailed(), "Текст ошибки на странице не соответствует ожидаемому");
    }

    @И("проверяем текст всплывающего уведомления - \"(.+)\"$")
    public void checkAlertMsg(String msg) {
        Assertions.assertEquals(msg, loginPage.getAlertText(), "Текст ошибки в всплывающем уведомлении не соответствует ожидаемому");
    }

    @Когда("авторизуемся под тестовым пользователем")
    public void loginTestUser() {
        loginPage.loginTestUser();
    }

    @Когда("заполняем поля авторизации пользователем {user}")
    public void loginTestUser(User user) {
        loginPage.fillLoginRegisterForm(user);
    }
}
