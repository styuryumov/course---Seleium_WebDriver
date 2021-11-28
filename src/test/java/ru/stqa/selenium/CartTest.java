package ru.stqa.selenium;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;

import java.util.List;

public class CartTest extends TestBase {

    public void checkingTheSizeFieldOfTheProduct(WebDriver driver, By locatorSize, By locatorAdd, String value) {
        if (isNotElementsPresent(driver, locatorSize)) {
            driver.findElement(locatorAdd).click();
        } else {
            Select select = new Select(driver.findElement(locatorSize));
            select.selectByValue(value);
            driver.findElement(locatorAdd).click();
        }
    }


    @Before
    public void enterTheLink() {
        driver.get((prop.getProperty("baseUrl")) + "/" + (prop.getProperty("langu") + "/"));
    }

    @Test
    public void addedAndRemovedProduct() {
        //Adding items to cart
        List<WebElement> product = driver.findElements(By.cssSelector("div#box-most-popular li.product"));
        product.get(0).click();
        for (int i = 0; i < 3; i++) {
            checkingTheSizeFieldOfTheProduct(driver, By.cssSelector("div#box-product select"), By.cssSelector("div#box-product [name=add_cart_product]"), "Small");
            wait.until(ExpectedConditions.textToBePresentInElementLocated(By.cssSelector("div#cart span.quantity"), String.valueOf(i + 1)));
            driver.navigate().back();
            product = driver.findElements(By.cssSelector("div#box-most-popular li.product"));
            product.get(0).click();
        }
        // removing items from the cart
        driver.findElement(By.cssSelector("div#cart a.link")).click();
        List<WebElement> button = driver.findElements(By.cssSelector("div#box-checkout-cart [name=remove_cart_item]"));
        for (int i = 0; i < button.size(); i++) {
            List<WebElement> listBefore = driver.findElements(By.cssSelector("div#checkout-summary-wrapper tr"));
            wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("div#box-checkout-cart [name=remove_cart_item]"))).click();
            wait.until(ExpectedConditions.stalenessOf(driver.findElement(By.cssSelector("div#checkout-summary-wrapper tr"))));
            List<WebElement> listAfter = driver.findElements(By.cssSelector("div#checkout-summary-wrapper tr"));
            Assert.assertTrue(listAfter.size() < listBefore.size());
        }
        Assert.assertTrue(isElementsPresent(driver, By.cssSelector("div#checkout-cart-wrapper a")));
    }
}
