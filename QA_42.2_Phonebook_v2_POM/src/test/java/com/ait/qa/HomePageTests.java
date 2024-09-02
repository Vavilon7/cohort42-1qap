package com.ait.qa;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import static core.AppManager.homePage;
import static core.AppManager.navigationPanel;

public class HomePageTests extends BaseTest{
    @BeforeMethod
    void precondition() {
        navigationPanel.clickOnHomeLink();
    }

    @Test(description = "Проверка домашней страницы", groups = {"Positive"})
    void checkHomePage() {
        Assert.assertTrue(homePage.checkHomeComponentIsPresent());
        Assert.assertTrue(homePage.checkReactAppInfoIsPresent());
        Assert.assertTrue(homePage.checkForQaInfoIsPresent());
    }


}
