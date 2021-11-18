package ru.stqa.selenium;


import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import static org.openqa.selenium.support.ui.ExpectedConditions.presenceOfElementLocated;

import java.util.List;

public class AdminTest extends TestBase {

    @Before
    public void enterTheLink() {
        driver.get(prop.getProperty("baseUrl") + "/admin" );
    }

    @Test
    public void testLogin() {
        driver.findElement(By.name("username")).sendKeys(prop.getProperty("login"));
        driver.findElement(By.name("password")).sendKeys(prop.getProperty("password"));
        driver.findElement(By.name("login")).click();
        wait.until(driver -> driver.findElements(By.cssSelector("div#body-wrapper")).size() > 0);
        }

    @Test
    public void testAdminPanel() {
        List<WebElement> list = driver.findElements(By.cssSelector("li#app-"));
        int iterations = list.size();

        for (int i = 0; i < iterations; i++) {
             list = driver.findElements(By.cssSelector("li#app-"));
             list.get(i).click();
             wait.until(presenceOfElementLocated(By.cssSelector("h1")));
             if (areElementsPresent(driver, By.cssSelector("ul.docs [id*=doc-]"))) {
                 List<WebElement> nestedElements = driver.findElements(By.cssSelector("ul.docs [id*=doc-]"));
                 for (int j = 0; j < nestedElements.size(); j++) {
                     nestedElements = driver.findElements(By.cssSelector("ul.docs [id*=doc-]"));
                     nestedElements.get(j).click();
                     wait.until(presenceOfElementLocated(By.cssSelector("h1")));
                 }
             }
             else {
                 continue;
             }

        }

    }

}
