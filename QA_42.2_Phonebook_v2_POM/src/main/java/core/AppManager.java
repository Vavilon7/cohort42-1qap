package core;

import model.Contact;
import model.User;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.safari.SafariDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import pages.AddContactPage;
import pages.ContactPage;
import pages.HomePage;
import pages.LoginPage;
import pages.NavigationPanel;

import java.time.Duration;

public class AppManager {public static WebDriver driver;
    public static WebDriverWait wait;
    public static final User TEST_USER = new User("manuel@gm.com", "Manuel1234$");
    public static final Contact TEST_CONTACT = new Contact("Test", "Testoviy", "1234567890", "test@test.com", "testAddress", "TestDesc");
    static final String URL_PHONE_BOOK = "https://telranedu.web.app";
    private String browser;

    public static HomePage homePage;
    public static LoginPage loginPage;
    public static ContactPage contactPage;
    public static AddContactPage addContactPage;
    public static NavigationPanel navigationPanel;

    public AppManager(String browser) {
        this.browser = browser;
    }

    public void init() {
        switch (browser.toLowerCase()) {
            case "firefox":
                driver = new FirefoxDriver();
                break;
            case "edge":
                driver = new EdgeDriver();
                break;
            case "safari":
                driver = new SafariDriver();
                break;
            case "chrome":
            default:
                driver = new ChromeDriver();
        }
        wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
        driver.manage().window().maximize();
        driver.get(URL_PHONE_BOOK);

        homePage = new HomePage();
        loginPage = new LoginPage();
        contactPage = new ContactPage();
        addContactPage = new AddContactPage();
        navigationPanel = new NavigationPanel();
    }

    public void stop() {
        driver.quit();
    }

}
