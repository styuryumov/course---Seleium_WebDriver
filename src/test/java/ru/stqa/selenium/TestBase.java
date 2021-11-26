package ru.stqa.selenium;

import org.junit.After;
import org.junit.Before;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.ie.InternetExplorerOptions;
import org.openqa.selenium.support.ui.WebDriverWait;

import org.openqa.selenium.support.Color;
import java.io.IOException;
import java.util.*;

public class TestBase {
    public static WebDriver driver;
    public Properties prop;
    public static WebDriverWait wait;
    public Random rdm = new Random();

    boolean areElementsPresent(WebDriver driver, By locator) {
        return driver.findElements(locator).size() > 0;
    }

    public void checkbox(WebDriver driver, By locator, int number) {
        List<WebElement> list = driver.findElements(locator);
        for (WebElement chec: list) {
            if (chec.isSelected() == true) {
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

    boolean sizeComparison(int size1, int size2) {
        return size1 < size2;
    }

    boolean color(Color color) {
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

    @Before
    public void start() throws IOException {
        prop = new Properties();
        prop.load(TestBase.class.getResourceAsStream("/testData.properties"));

        if (driver != null) {
            return;
        }
        if ("chrome".equals(prop.getProperty("browser"))) {
            ChromeOptions options = new ChromeOptions();
            options.addArguments("start-fullscreen");
            driver = new ChromeDriver(options);
            wait = new WebDriverWait(driver, 10);
        } else if ("firefox".equals(prop.getProperty("browser"))) {
            FirefoxOptions options = new FirefoxOptions();
            options.addArguments("start-fullscreen");
            driver = new FirefoxDriver(options);
            wait = new WebDriverWait(driver, 10);
        } else if ("ie".equals(prop.getProperty("browser"))) {
            InternetExplorerOptions options = new InternetExplorerOptions();
            options.addCommandSwitches("start-fullscreen");
            driver = new InternetExplorerDriver(options);
            wait = new WebDriverWait(driver, 10);
        }
        Runtime.getRuntime().addShutdownHook(new Thread(() -> {
            driver.quit();
            driver = null;
        }));
    }

    @After
    public void stop() {
        //driver.quit();
        //driver = null;
    }
}
