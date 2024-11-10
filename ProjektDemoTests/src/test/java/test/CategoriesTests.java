package test;

import app.web.demoShopSelenium.data.core.BaseSteps;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;

import static org.junit.jupiter.api.Assertions.assertTrue;
@Tag("@CATEGORIES")
@Tag("@ALL")
public class CategoriesTests extends BaseSteps {
    @Test
    @Tag("@2")
    @DisplayName("Проверка корректного отображения страниц в классе Computers")
    void test2() {
        clickOnElement(By.cssSelector(".active"));
        Assertions.assertAll(
                () -> assertTrue(elementIsDisplayed(By.xpath("//a[@href='/desktops']"))),
                () -> assertTrue(elementIsDisplayed(By.xpath("//a[@href='/notebooks']"))),
                () -> assertTrue(elementIsDisplayed(By.xpath("//a[@href='/accessories']")))
        );
    }

}
