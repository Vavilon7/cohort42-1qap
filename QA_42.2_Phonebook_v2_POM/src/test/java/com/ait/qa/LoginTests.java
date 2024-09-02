package com.ait.qa;

import core.DataProviders;
import model.User;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import static core.AppManager.TEST_USER;
import static core.AppManager.loginPage;
import static core.AppManager.navigationPanel;
import static pages.BasePage.getRandomEmail;

public class LoginTests {
    @BeforeMethod
    void precondition() {
        if (!navigationPanel.isLoginLinkPresent()) {
            navigationPanel.clickOnSigOutButton();
        }
    }

    @Test(description = "Проверка успешной авторизации", groups = {"Positive"})
    void loginPositiveTest() {
        navigationPanel
                .clickOnLoginLink()
                .fillLoginRegisterForm(TEST_USER)
                .clickOnLoginButton();
        Assert.assertTrue(navigationPanel.isSignOutButtonPresent());
    }

    @Test(dataProvider = "loginUsers", dataProviderClass = DataProviders.class)
    void loginPositiveParametersTest(User user) {
        navigationPanel
                .clickOnLoginLink()
                .fillLoginRegisterForm(user)
                .clickOnLoginButton();
        Assert.assertTrue(navigationPanel.isSignOutButtonPresent());
    }

    @Test(description = "Проверка авторизации c неверным паролем", groups = {"Negative"})
    void loginNegativeTest() {
        navigationPanel
                .clickOnLoginLink()
                .fillLoginRegisterForm(new User("qwert@qa.com", "234123"))
                .clickOnLoginButton();
        Assert.assertEquals("Wrong email or password", loginPage.getAlertText(), "Текст ошибки в всплывающем уведомлении не соответствует ожидаемому");
        Assert.assertEquals(("Login Failed with code 401"), loginPage.getMessageLoginFailed(), "Текст ошибки на странице не соответствует ожидаемому");
    }

    @Test(description = "Проверка успешной регистрации", groups = {"Positive"})
    void registrationPositiveTest() {
        navigationPanel
                .clickOnLoginLink()
                .fillLoginRegisterForm(new User(getRandomEmail(), TEST_USER.getPassword()))
                .clickOnRegistrationButton();
        Assert.assertTrue(navigationPanel.isSignOutButtonPresent());
    }
}
