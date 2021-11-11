package ru.stqa.selenium;

import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;

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
        wait.until(driver -> driver.findElements(By.id("body-wrapper")).size() > 0);
    }

}
