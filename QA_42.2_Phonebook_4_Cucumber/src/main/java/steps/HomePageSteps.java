package steps;

import io.cucumber.java.en.Then;
import io.cucumber.java.ru.Тогда;

import static core.AppManager.homePage;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class HomePageSteps {

    @Тогда("проверяем отображение 'Home Component'")
    public void checkHomeComponentIsPresent() {
        assertTrue(homePage.checkHomeComponentIsPresent());
    }

    @Then("check 'React Contacts App' is displayed")
    public void checkReactAppInfoIsPresent() {
        assertTrue(homePage.checkReactAppInfoIsPresent());
    }

    @Тогда("проверяем отображение 'For QA Testing'")
    public void checkForQaInfoIsPresent() {
        assertTrue(homePage.checkForQaInfoIsPresent());
    }
}
