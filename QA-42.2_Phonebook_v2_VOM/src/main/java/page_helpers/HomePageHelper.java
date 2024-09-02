package page_helpers;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

public class HomePageHelper extends BaseHelper {

    public HomePageHelper(WebDriver driver, WebDriverWait wait) {
        super(driver, wait);
    }

    public void clickOnHomeLink() {
        clickOnElement(By.cssSelector("[href='/home']"));
    }

    public boolean checkHomeComponentIsPresent(){
        return isElementPresent(By.xpath("//*[text()='Home Component']"));
    }

    public boolean checkReactAppInfoIsPresent() {
        return isElementPresent(By.xpath("//*[text()='React Contacts App']"));
    }

    public boolean checkForQaInfoIsPresent() {
        return isElementPresent(By.xpath("//*[text()='For QA Testing']"));
    }
}