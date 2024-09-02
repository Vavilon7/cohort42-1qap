import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;

public class HomePageTests extends BaseTest{
    @Test
    void test1() {
        clickOnElement(By.cssSelector("[href='/home']"));

        Assertions.assertTrue(getElementBy(By.xpath("//*[text()='Home Component']")).isDisplayed(), "На странице home отсутствует элемент с текстом 'Home Component'");
        Assertions.assertTrue(getElementBy(By.xpath("//*[text()='React Contacts App']")).isDisplayed(), "На странице home отсутствует элемент с текстом 'React Contacts App'");
        Assertions.assertTrue(getElementBy(By.xpath("//*[text()='For QA Testing']")).isDisplayed(), "На странице home отсутствует элемент с текстом 'For QA Testing'");
    }
}
