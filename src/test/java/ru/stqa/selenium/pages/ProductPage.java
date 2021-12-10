package ru.stqa.selenium.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import ru.stqa.selenium.pages.Page;

import java.util.concurrent.TimeUnit;

public class ProductPage extends Page {
    public static int seconds = 10;


    public ProductPage(WebDriver driver) {
        super(driver);
    }

    public void addProduct(String size) {
        checkingTheSizeFieldOfTheProduct(driver, By.cssSelector("div#box-product select"), By.cssSelector("div#box-product [name=add_cart_product]"), size);
    }

    public void waitingForAnItem(int iteration) {
        wait.until(ExpectedConditions.textToBePresentInElementLocated(By.cssSelector("div#cart span.quantity"), String.valueOf(iteration + 1)));
    }

    public void back() {
        driver.navigate().back();
    }

    public void checkingTheSizeFieldOfTheProduct(WebDriver driver, By locatorSize, By locatorAdd, String value) {
        if (isNotElementsPresent(driver, locatorSize)) {
            driver.findElement(locatorAdd).click();
        } else {
            Select select = new Select(driver.findElement(locatorSize));
            select.selectByValue(value);
            driver.findElement(locatorAdd).click();
        }
    }

    boolean isNotElementsPresent(WebDriver driver, By locator) {
        try {
            driver.manage().timeouts().implicitlyWait(0, TimeUnit.SECONDS);
            return driver.findElements(locator).size() == 0;
        } finally {
            driver.manage().timeouts().implicitlyWait(seconds, TimeUnit.SECONDS);
        }
    }
}
