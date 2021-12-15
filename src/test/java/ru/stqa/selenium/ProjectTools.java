package ru.stqa.selenium;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.Color;
import org.openqa.selenium.support.ui.Select;

import java.util.List;
import java.util.Objects;
import java.util.concurrent.TimeUnit;

public class ProjectTools {
    public static int seconds = 10;

    public boolean isElementsPresent(WebDriver driver, By locator) {
        try {
            driver.manage().timeouts().implicitlyWait(seconds, TimeUnit.SECONDS);
            return driver.findElements(locator).size() > 0;
        } finally {
            driver.manage().timeouts().implicitlyWait(0, TimeUnit.SECONDS);
        }
    }

    public boolean isNotElementsPresent(WebDriver driver, By locator) {
        try {
            driver.manage().timeouts().implicitlyWait(0, TimeUnit.SECONDS);
            return driver.findElements(locator).size() == 0;
        } finally {
            driver.manage().timeouts().implicitlyWait(seconds, TimeUnit.SECONDS);
        }
    }

    public void checkbox(WebDriver driver, By locator, int number) {
        List<WebElement> list = driver.findElements(locator);
        for (WebElement chec : list) {
            if (chec.isSelected()) {
                chec.click();
            }
        }
        list.get(number).click();
    }

    public int size(String string) {
        String[] strArr = string.split("");
        if (Objects.equals(strArr[1], ".")) {
            strArr[1] = strArr[2];
        }
        return Integer.parseInt(strArr[0] + strArr[1]);
    }

    public boolean sizeComparison(int size1, int size2) {
        return size1 < size2;
    }

    public boolean color(Color color) {
        int red = color.getColor().getRed();
        int green = color.getColor().getGreen();
        int blue = color.getColor().getBlue();

        if ((red == green) && (green == blue)) {
            return true;
        } else if ((red != green) && (green == blue)) {
            return true;
        } else {
            return false;
        }
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
}
