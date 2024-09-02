package com.ait.qa;

import core.AppManager;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;

import java.lang.reflect.Method;

import static pages.BasePage.takeScreenshot;

public class BaseTest {
    protected static AppManager app = new AppManager(System.getProperty("browser", "chrome"));
    private Logger logger = LoggerFactory.getLogger(BaseTest.class);

    @BeforeSuite
    static void startTests() {
        app.init();
    }

    @AfterSuite
    static void stopTests() {
        app.stop();
    }

    @BeforeMethod
    void startTest(Method method) {
        logger.info("Start test" + method.getName());
    }

    @AfterMethod
    void stopTest(ITestResult result) {
        if (result.isSuccess()) {
            logger.info("PASSED: " + result.getMethod().getMethodName());
        } else {
            logger.info("FAILED: " + result.getMethod().getMethodName());
            takeScreenshot();
        }
        logger.info("===================================================");
    }
}
