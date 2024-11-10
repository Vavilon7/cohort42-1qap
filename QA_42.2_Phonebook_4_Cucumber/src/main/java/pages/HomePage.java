package pages;

import io.qameta.allure.Step;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class HomePage extends BasePage {

    @FindBy(xpath = "//*[text()='Home Component']")
    WebElement homeComponent;

    @FindBy(xpath = "//*[text()='React Contacts App']")
    WebElement reactAppInfo;

    @FindBy(xpath = "//*[text()='For QA Testing']")
    WebElement forQaInfo;

    @Step("Проверяем отображение 'Home Component'")
    public boolean checkHomeComponentIsPresent() {
        return homeComponent.isDisplayed();
    }

    @Step("Проверяем отображение 'React Contacts App'")
    public boolean checkReactAppInfoIsPresent() {
        return reactAppInfo.isDisplayed();
    }

    @Step("Проверяем отображение 'For QA Testing'")
    public boolean checkForQaInfoIsPresent() {
        return forQaInfo.isDisplayed();
    }
}
