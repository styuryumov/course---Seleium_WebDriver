package ru.stqa.selenium.app;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.ie.InternetExplorerOptions;
import org.openqa.selenium.support.ui.WebDriverWait;
import ru.stqa.selenium.pages.CartPage;
import ru.stqa.selenium.pages.HomePage;
import ru.stqa.selenium.pages.ProductPage;
import ru.stqa.selenium.test.TestBase;

import java.io.IOException;
import java.util.Properties;



public class Application {

    public WebDriver driver;
    public Properties prop;
    public WebDriverWait wait;

    private final HomePage home;
    private final ProductPage product;
    private final CartPage cart;

    public Application() throws IOException {
        prop = new Properties();
        prop.load(TestBase.class.getResourceAsStream("/testData.properties"));

        if ("chrome".equals(prop.getProperty("browser"))) {
            ChromeOptions options = new ChromeOptions();
           //options.addArguments("start-fullscreen");
            driver = new ChromeDriver(options);
            wait = new WebDriverWait(driver, 10);
        } else if ("firefox".equals(prop.getProperty("browser"))) {
            FirefoxOptions options = new FirefoxOptions();
            //options.addArguments("start-fullscreen");
            driver = new FirefoxDriver(options);
            wait = new WebDriverWait(driver, 10);
        } else if ("ie".equals(prop.getProperty("browser"))) {
            InternetExplorerOptions options = new InternetExplorerOptions();
            options.addCommandSwitches("start-fullscreen");
            driver = new InternetExplorerDriver(options);
            wait = new WebDriverWait(driver, 10);
        }

        home = new HomePage(driver);
        product = new ProductPage(driver);
        cart = new CartPage(driver);
    }
    public void quit() {
        driver.quit();
    }
    public void openHomePage() throws IOException {
        home.openHome();
    }

    public void productSelection() {
        home.addProductSelection.get(0).click();
    }

    public void addProductToCart(int iteration, String size) {
        product.addProduct(size);
        product.waitingForAnItem(iteration);
        product.back();
    }

    public void removingProductFromTheCart() {
        cart.openCart.click();
        int iteration = cart.addButtons.size();
        for (int i = 0; i < iteration; i++) {
            cart.getItem();
            cart.remove().click();
            cart.checkingForMissingElement();
        }
        cart.checkCart();
    }
}