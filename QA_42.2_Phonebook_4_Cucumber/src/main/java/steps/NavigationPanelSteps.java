package steps;
import io.cucumber.java.ru.И;
import io.cucumber.java.ru.Когда;
import io.cucumber.java.ru.Тогда;
import io.qameta.allure.Step;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import pages.AddContactPage;
import pages.BasePage;
import pages.HomePage;
import pages.LoginPage;

import java.util.List;

import static core.AppManager.navigationPanel;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class NavigationPanelSteps {

    @И("проверяем наличие кнопки 'Sign Out'")
    public void isSignOutButtonPresent() {
      assertTrue(navigationPanel.isSignOutButtonPresent()) ;
    }

    @Когда("нажимаем на кнопку 'Sign Out'")
    public void clickOnSigOutButton() {
      navigationPanel.clickOnSigOutButton();
    }

    @Когда("нажимаем на кнопку 'ADD'")
    public void clickOnAddLink() {
     navigationPanel.clickOnAddLink();
    }

    @Когда("нажимаем на кнопку 'HOME'")
    public void clickOnHomeLink() {
       navigationPanel.clickOnHomeLink();
    }

    @Тогда("проверяем наличие кнопки 'LOGIN'")
    public void isLoginLinkPresent() {
        assertTrue(navigationPanel.isLoginLinkPresent()) ;
    }

    @Когда("нажимаем на кнопку 'LOGIN' в заголовке")
    public void clickOnLoginLink() {
       navigationPanel.clickOnLoginLink();
    }
}
