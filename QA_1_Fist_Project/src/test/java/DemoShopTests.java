import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

public class DemoShopTests extends BaseTest{
    final static String DEMO_SHOP_URL = "https://demowebshop.tricentis.com/";

    @Test
    @DisplayName("Проверка элемента по id")
    void test1() {
        driver.get(DEMO_SHOP_URL);
        WebElement smallSearchTerms = getElementBy(By.id("small-searchterms"));

        Assertions.assertTrue(smallSearchTerms.getAttribute("name").equals("q"));
        Assertions.assertEquals("q", smallSearchTerms.getAttribute("name"));
    }

    @Test
    @DisplayName("Переход на страницу Books и проверяем элементы")
    void test2() {
        driver.get(DEMO_SHOP_URL);
//        WebElement hrefBooks = getElementBy(By.cssSelector("[href='/books']"));
        WebElement hrefBooks = getElementBy(By.partialLinkText("Books"));
        hrefBooks.click();

        Assertions.assertTrue(driver.getTitle().contains("Books"), "Не перешли на страницу Books");
    }

}
