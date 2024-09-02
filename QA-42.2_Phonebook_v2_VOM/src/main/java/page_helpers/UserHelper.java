package page_helpers;

import model.User;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import static core.AppManager.TEST_USER;
public class UserHelper extends BaseHelper {
    public UserHelper(WebDriver driver, WebDriverWait wait) {
        super(driver, wait);
    }

    public boolean isSignOutButtonPresent() {
        return isElementPresent(By.xpath("//*[text()='Sign Out']"));
    }

    public boolean isLoginLinkPresent() {
        return isElementPresent(By.xpath("//*[text()='LOGIN']"));
    }

    public void clickOnLoginLink() {
        clickOnElement(By.xpath("//*[text()='LOGIN']"));
    }

    public void clickOnLoginButton() {
        clickOnElement(By.name("login"));
    }

    public void clickOnRegistrationButton() {
        clickOnElement(By.name("registration"));
    }

    public void clickOnSigOutButton() {
        clickOnElement(By.xpath("//*[text()='Sign Out']"));
    }

    public void fillLoginRegisterForm(User user) {
        fillInputField(By.name("email"), user.getEmail());
        fillInputField(By.name("password"), user.getPassword());
    }

    public String getMessageLoginFailed() {
        return getElementBy(By.xpath("//*[contains(text(), 'Login Failed')]")).getText();
    }

    public void loginTestUser() {
        clickOnLoginLink();
        fillLoginRegisterForm(TEST_USER);
        clickOnLoginButton();
    }
}