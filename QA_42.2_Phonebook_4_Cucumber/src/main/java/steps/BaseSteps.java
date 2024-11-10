package steps;

import core.AppManager;
import io.cucumber.java.AfterAll;
import io.cucumber.java.Before;
import io.cucumber.java.BeforeAll;
import io.cucumber.java.ParameterType;
import io.cucumber.java.en.When;
import model.User;
import org.openqa.selenium.remote.Browser;
import pages.BasePage;

import static core.AppManager.TEST_USER;
import static core.AppManager.navigationPanel;

public class BaseSteps {

    protected static AppManager app = new AppManager(System.getProperty("browser", Browser.CHROME.browserName()));

    @BeforeAll
    public static void startTests() {
        app.init();
    }

    @AfterAll
    public static void stopTests() {
        app.stop();
    }

    @Before("@BeforeLogin")
    public static void precondition() {
        if (!navigationPanel.isLoginLinkPresent()) {
            navigationPanel.clickOnSigOutButton();
        }
    }

    @When("нет кнопки 'LOGIN' то нажимаем 'Sign Out'")
    public void loginPrecondition() {
        if (!navigationPanel.isLoginLinkPresent()) {
            navigationPanel.clickOnSigOutButton();
        }
    }

    @When("ожидаем {int} секунд(ы)")
    public void waitInSeconds(int seconds) {
        navigationPanel.waitInSeconds(seconds);
    }

    //Создание параметра в лице Юзера
    @ParameterType("TestUser|RandomUser")
    public User user(String userName) {
        if (userName.equals("TestUser")) {
            return TEST_USER;
        }
        return new User(BasePage.getRandomEmail(), TEST_USER.getPassword());
    }

}
