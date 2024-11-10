package test;
import app.web.demoShopSelenium.data.core.BaseSteps;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import static org.junit.jupiter.api.Assertions.assertTrue;

@Tag("@MENU")
@Tag("@ALL")
public class MenuTests extends BaseSteps {
    @Test
    @Tag("@1")
    @DisplayName("Проверка корректного отображения страниц")
    void test1() {
        clickOnElement(By.cssSelector(".header-menu"));
        Assertions.assertAll(
                () -> assertTrue(elementIsDisplayed(By.xpath("//a[@href='/books']"))),
                () -> assertTrue(elementIsDisplayed(By.xpath("//a[@href='/computers']"))),
                () -> assertTrue(elementIsDisplayed(By.xpath("//a[@href='/electronics']"))),
                () -> assertTrue(elementIsDisplayed(By.xpath("//a[@href='/apparel-shoes']"))),
                () -> assertTrue(elementIsDisplayed(By.xpath("//a[@href='/digital-downloads']"))),
                () -> assertTrue(elementIsDisplayed(By.xpath("//a[@href='/jewelry']"))),
                () -> assertTrue(elementIsDisplayed(By.xpath("//a[@href='/gift-cards']")))
        );
    }
}

