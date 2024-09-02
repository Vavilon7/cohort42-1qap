package com.ait.qa;

import model.User;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import static core.AppManager.TEST_USER;
import static page_helpers.BaseHelper.getRandomEmail;

public class LoginTests extends BaseTest {

    @BeforeMethod
    void precondition() {
        if (!app.getUserHelper().isLoginLinkPresent()) {
            app.getUserHelper().clickOnSigOutButton();
        }
    }

    @Test(description = "Проверка успешной авторизации", groups = {"Positive"})
    void loginPositiveTest() {
        app.getUserHelper().clickOnLoginLink();
        app.getUserHelper().fillLoginRegisterForm(TEST_USER);
        app.getUserHelper().clickOnLoginButton();
        Assert.assertTrue(app.getUserHelper().isSignOutButtonPresent());
    }

    @Test(description = "Проверка авторизации c неверным паролем", groups = {"Negative"})
    void loginNegativeTest() {
        app.getUserHelper().clickOnLoginLink();
        app.getUserHelper().fillLoginRegisterForm(new User("qwert@qa.com", "234123"));
        app.getUserHelper().clickOnLoginButton();
        Assert.assertEquals("Wrong email or password", app.getUserHelper().getAlertText(), "Текст ошибки в всплывающем уведомлении не соответствует ожидаемому");
        Assert.assertEquals(("Login Failed with code 401"), app.getUserHelper().getMessageLoginFailed(), "Текст ошибки на странице не соответствует ожидаемому");
    }

    @Test(description = "Проверка успешной регистрации", groups = {"Positive"})
    void registrationPositiveTest() {
        app.getUserHelper().clickOnLoginLink();
        app.getUserHelper().fillLoginRegisterForm(new User(getRandomEmail(), TEST_USER.getPassword()));
        app.getUserHelper().clickOnRegistrationButton();
        Assert.assertTrue(app.getUserHelper().isSignOutButtonPresent());
    }
}