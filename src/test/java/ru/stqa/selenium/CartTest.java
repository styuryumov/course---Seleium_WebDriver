package ru.stqa.selenium;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.util.List;

public class CartTest extends TestBase {
    ProjectTools tools = new ProjectTools();

    @Before
    public void enterTheLink() {
        driver.get((prop.getProperty("baseUrl")) + "/" + (prop.getProperty("langu") + "/"));
    }

    @Test
    public void addedAndRemovedProduct() {
        //Adding items to cart
        List<WebElement> listProductMP = driver.findElements(By.cssSelector("div#box-most-popular li.product"));
        listProductMP.get(0).click();
        for (int i = 0; i < 3; i++) {
            tools.checkingTheSizeFieldOfTheProduct(driver, By.cssSelector("div#box-product select"), By.cssSelector("div#box-product [name=add_cart_product]"), "Small");
            wait.until(ExpectedConditions.textToBePresentInElementLocated(By.cssSelector("div#cart span.quantity"), String.valueOf(i + 1)));
            driver.navigate().back();
            listProductMP = driver.findElements(By.cssSelector("div#box-most-popular li.product"));
            listProductMP.get(0).click();
        }
        // removing items from the cart
        driver.findElement(By.cssSelector("div#cart a.link")).click();
        List<WebElement> button = driver.findElements(By.cssSelector("div#box-checkout-cart [name=remove_cart_item]"));
        for (int i = 0; i < button.size(); i++) {
            WebElement element = driver.findElement(By.cssSelector("div#checkout-summary-wrapper tr"));
            wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("div#box-checkout-cart [name=remove_cart_item]"))).click();
            wait.until(ExpectedConditions.stalenessOf(element));

        }
        Assert.assertTrue(tools.isElementsPresent(driver, By.cssSelector("div#checkout-cart-wrapper a")));
    }
}
