package com.ait.qa;

import app.web.demoShopSelenium.data.core.BaseTest;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

public class DemoWebShopTests extends BaseTest {
    @Test
    @DisplayName("Позитивный тест проверка на равенство Title страницы с ожидаемым результатом Demo Web Shop")
    void test1() {
        //нажимаем кнопку назад
        driver.navigate().back();
        //нажимаем кнопку вперед
        driver.navigate().forward();
        //обновляем страницу
        driver.navigate().refresh();
        //проверяем, что открылась страница с Title = Demo Web Shop
        Assertions.assertEquals("Demo Web Shop", driver.getTitle(), "Открыта страница с другим Titel");
    }

    @Test
    @DisplayName("негативный тест проверка на неравенство Title страницы с ожидаемым результатом Demo Web Shop")
    void test2() {
        driver.navigate().back();
        driver.navigate().forward();
        driver.navigate().refresh();
        //проверяем, что открылась страница с Title = Demo Web Shop
        Assertions.assertNotEquals("Demo Weba Shop", driver.getTitle(), "Открыта страница с другим Titel");
    }

    @Test
    @DisplayName("Поиск элемента с классом register")
    void test3() {
        WebElement classElement = driver.findElement(By.cssSelector(".ico-register"));
        Assertions.assertTrue(classElement.isDisplayed(), "Не отображается элемент с классом 'ico-register'");
    }

    @Test
    @DisplayName("Поиск элемента с классом login")
    void test4() {
        WebElement classElement = driver.findElement(By.cssSelector(".ico-login"));
        Assertions.assertTrue(classElement.isDisplayed(), "Не отображается элемент с классом 'ico-login'");
    }

    @Test
    @DisplayName("Поиск элемента с классом topcartlink")
    void test5() {
        WebElement idElement = driver.findElement(By.cssSelector("#topcartlink"));
        Assertions.assertTrue(idElement.isDisplayed(), "Не найден элемент с id 'topcartlink'");
    }

    @Test
    @DisplayName("Поиск элементов на странице Demo Web Shop")
    void test6() {
        // Поиск элемента с классом "topcartlink"
        WebElement artElement = driver.findElement(By.cssSelector("#topcartlink"));
        Assertions.assertTrue(artElement.isDisplayed(), "Не найден элемент с id 'topcartlink'");
    }

    @Test
    @DisplayName("Поиск элементов на странице Demo Web Shop")
    void test7() {
        // Поиск элемента с классом "title"
        WebElement classNameElement = driver.findElement(By.className("title"));
        Assertions.assertEquals("CATEGORIES", classNameElement.getText(), "Заголовок элемента не соответствует 'Categories'");
    }
}

