package ru.stqa.selenium;

import org.junit.After;
import org.junit.Before;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.WebDriverWait;


import java.io.IOException;
import java.util.Properties;

public class TestBase {

    public WebDriver driver;
    public Properties prop;
    public WebDriverWait wait;

    @Before
    public void start() throws IOException {
        prop = new Properties();
        prop.load(TestBase.class.getResourceAsStream("/testData.properties"));
        driver = new FirefoxDriver();
        wait = new WebDriverWait(driver, 10);
    }

    @After
    public void stop() {
        driver.quit();
        driver = null;
    }
}
