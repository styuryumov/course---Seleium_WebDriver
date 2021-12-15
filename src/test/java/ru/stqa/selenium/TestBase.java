package ru.stqa.selenium;

import org.junit.Before;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.ie.InternetExplorerOptions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.IOException;
import java.util.*;


public class TestBase {
    public ThreadLocal<WebDriver> tlDriver = new ThreadLocal<>();
    public WebDriver driver;
    public Properties prop;
    public WebDriverWait wait;

    @Before
    public void start() throws IOException {
        prop = new Properties();
        prop.load(TestBase.class.getResourceAsStream("/testData.properties"));

        if (tlDriver.get() != null) {
            driver = tlDriver.get();
            wait = new WebDriverWait(driver, 10);
            return;
        }
        if ("chrome".equals(prop.getProperty("browser"))) {
            ChromeOptions options = new ChromeOptions();
            options.addArguments("start-fullscreen");

            /*try {
                driver = new RemoteWebDriver(new URL("http://192.168.56.1:4444/wd/hub"), options);
            } catch (MalformedURLException e) {
                e.printStackTrace();
            }*/
            driver = new ChromeDriver(options);
            wait = new WebDriverWait(driver, 10);
        } else if ("firefox".equals(prop.getProperty("browser"))) {
            FirefoxOptions options = new FirefoxOptions();
            //options.addArguments("start-fullscreen");

            /*try {
                driver = new RemoteWebDriver(new URL("http://192.168.56.1:4444/wd/hub"), options);
            } catch (MalformedURLException e) {
                e.printStackTrace();
            }*/
            driver = new FirefoxDriver(options);
            wait = new WebDriverWait(driver, 10);
        } else if ("ie".equals(prop.getProperty("browser"))) {
            InternetExplorerOptions options = new InternetExplorerOptions();
            options.addCommandSwitches("start-fullscreen");
            /*try {
                driver = new RemoteWebDriver(new URL("http://192.168.56.1:4444/wd/hub"), options);
            } catch (MalformedURLException e) {
                e.printStackTrace();
            }*/
            driver = new InternetExplorerDriver(options);
            wait = new WebDriverWait(driver, 10);
        }
        Runtime.getRuntime().addShutdownHook(new Thread(() -> {
            driver.quit();
            driver = null;
        }));
    }
}
