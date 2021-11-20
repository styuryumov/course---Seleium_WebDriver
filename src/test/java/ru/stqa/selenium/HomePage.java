package ru.stqa.selenium;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.util.List;

public class HomePage extends TestBase {

    @Before
    public void enterTheLink() {
        driver.get((prop.getProperty("baseUrl")) + "/" + (prop.getProperty("langu") + "/"));
    }

    @Test
    public void checkForStickersTest() {
        List<WebElement> list = driver.findElements(By.cssSelector("div#main li.hover-light"));

        for (int i = 0; i < list.size(); i++) {
            WebElement element = list.get(i);
            Assert.assertTrue(element.findElements(By.cssSelector("div.sticker")).size() == 1);
        }
    }
}
