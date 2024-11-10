package HomeWork;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

public class DemoWebShopTests extends BaseTest {
    @Test //позитивный тест проверка на равенство Title страницы с ожидаемым результатом "Demo Web Shop"
    void test1(){
        //открываем страницу
        driver.get("https://demowebshop.tricentis.com/");
        //нажимаем кнопку назад
        driver.navigate().back();
        //нажимаем кнопку вперед
        driver.navigate().forward();
        //обновляем страницу
        driver.navigate().refresh();
        //проверяем, что открылась страница с Title = Demo Web Shop
        Assertions.assertEquals("Demo Web Shop",driver.getTitle(),"Открыта страница с другим Titel");
    }
    @Test //негативный тест проверка на неравенство Title страницы с ожидаемым результатом "Demo Web Shop"
    void test2(){
        driver.get("https://demowebshop.tricentis.com/");
        driver.navigate().back();
        driver.navigate().forward();
        driver.navigate().refresh();
        //проверяем, что открылась страница с Title = Demo Web Shop
        Assertions.assertNotEquals("Demo Weba Shop",driver.getTitle(),"Открыта страница с другим Titel");
    }
    @Test
    @DisplayName("Поиск элементов на странице Demo Web Shop")
    void test3(){

        driver.get("https://demowebshop.tricentis.com/");
        WebElement smallSearchTerms = driver.findElement(By.id("small-searchterms"));

        Assertions.assertTrue(smallSearchTerms.getAttribute("name").equals("q"));
        Assertions.assertEquals("q", smallSearchTerms.getAttribute("name"));
    }
    @Test
    @DisplayName("Поиск элементов на странице Demo Web Shop")
    void test4() {
        driver.get("https://demowebshop.tricentis.com/");

        // Поиск элемента с классом "title"
        WebElement classNameElement = driver.findElement(By.className("title"));

        // Проверка, что текст элемента равен "Categories"
        Assertions.assertEquals("CATEGORIES", classNameElement.getText(), "Заголовок элемента не соответствует 'Categories'");
    }
}
