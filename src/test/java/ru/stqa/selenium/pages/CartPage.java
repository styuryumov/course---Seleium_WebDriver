package ru.stqa.selenium.pages;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.util.List;
import java.util.concurrent.TimeUnit;

public class CartPage extends Page {
    WebElement element;
    public static int seconds = 10;

    public CartPage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
    }

    @FindBy(css = "div#cart a.link")
    public WebElement openCart;

    @FindBy (css = "div#box-checkout-cart [name=remove_cart_item]")
    public List<WebElement> addButtons;

    public void getItem(){
        element = driver.findElement(By.cssSelector("div#checkout-summary-wrapper tr"));
    }

    public WebElement remove() {
       return wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("div#box-checkout-cart [name=remove_cart_item]")));
    }

    public void checkingForMissingElement() {
        wait.until(ExpectedConditions.stalenessOf(element));
    }

    public void checkCart() {
        Assert.assertTrue(isElementsPresent(driver, By.cssSelector("div#checkout-cart-wrapper a")));
    }

    boolean isElementsPresent(WebDriver driver, By locator) {
        try {
            driver.manage().timeouts().implicitlyWait(seconds, TimeUnit.SECONDS);
            return driver.findElements(locator).size() > 0;
        } finally {
            driver.manage().timeouts().implicitlyWait(0, TimeUnit.SECONDS);
        }
    }
}
