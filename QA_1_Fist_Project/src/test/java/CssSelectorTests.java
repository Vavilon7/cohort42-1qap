import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

public class CssSelectorTests extends BaseTest{
    @Test
    @DisplayName("Css тесты")
    void test1() {
        driver.get(URL_ILCARRO);
        //tagName -> tag name
//        WebElement tagName = driver.findElement(By.tagName("h1"));
        WebElement tagName = driver.findElement(By.cssSelector("h1"));

        //By.id -> #id
        WebElement elementById = driver.findElement(By.cssSelector("#city"));
//        WebElement elementById = driver.findElement(By.id("city"));
//        WebElement elementById = driver.findElement(By.cssSelector("[id='city']"));

        //class -> .class
//        WebElement elementByClass = driver.findElement(By.className("input-container"))
        WebElement elementByClass = driver.findElement(By.cssSelector(".input-container"));
//        WebElement elementByClass = driver.findElement(By.cssSelector("class='input-container'"))

        //name, type, href и по остальным любым атрибутам ->
        WebElement elementByAtr = driver.findElement(By.cssSelector("[href='/search']"));

        //Вниз по дереву body img
        WebElement elementByCss = driver.findElement(By.cssSelector("body img"));

        /*
            class='ng-untouched ng-pristine ng-invalid'"
            contains -> *=      By.cssSelector("[class*='ng-pristine']")
            start With -> ^=    By.cssSelector("[class^='ng-untouched']")
            end on -> $=        By.cssSelector("[class$='ng-invalid']")
        */
    }

    @Test
    @DisplayName("Проверка работы всплывающего уведомления")
    void test2() {
        driver.get(URL_PHONE_BOOK);
        WebElement loginLink = getElementBy(By.cssSelector("[href='/login']"));
        loginLink.click();

        WebElement loginButton = getElementBy(By.cssSelector("button[name='login']"));
        loginButton.click();

        Alert alert = getAlert();
        Assertions.assertEquals("Wrong email or password", alert.getText(), "Текст всплывающего уведомления не соответствует ожидаемому");
        alert.accept();

        WebElement errorTextElement = getElementBy(By.cssSelector("[class='login_login__3EHKB'] div"));
        Assertions.assertEquals("Login Failed with code 401", errorTextElement.getText(), "Текст ошибки не соответствует ожидаемому");
    }

}
