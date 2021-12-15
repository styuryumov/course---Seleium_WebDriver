package ru.stqa.selenium.app;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.ie.InternetExplorerOptions;

import org.openqa.selenium.support.ui.WebDriverWait;
import ru.stqa.selenium.model.Customers;
import ru.stqa.selenium.pages.*;
import ru.stqa.selenium.test.TestBase;

import java.io.IOException;
import java.util.Properties;
import java.util.Set;

import static java.util.stream.Collectors.toSet;


public class Application {

    public WebDriver driver;
    public Properties prop;
    public WebDriverWait wait;

    private HomePage home;
    private ProductPage product;
    private final CartPage cart;
    private RegistrationPage registration;
    private AdminPage admin;
    private CustomerListPage customerListPage;

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
        registration = new RegistrationPage(driver);
        admin = new AdminPage(driver);
        customerListPage = new CustomerListPage(driver);
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

    public void registrationNewCustomer(Customers customer) throws IOException {
        home.openHome();
        registration.openRegistrationPage.click();
        registration.pageValidation();
        // Registration new customer
        registration.firstnameInput.sendKeys(customer.getFirstname());
        registration.lastnameInput.sendKeys(customer.getLastname());
        registration.addressInput.sendKeys(customer.getAddress());
        registration.postcodeInput.sendKeys(customer.getPostcode());
        registration.cityInput.sendKeys(customer.getCity());
        registration.selectCountry(customer.getCountry());
        registration.selectZone(customer.getZone());
        registration.emailInput.sendKeys(customer.getEmail());
        registration.emailInput.sendKeys(Keys.CONTROL + "a");
        registration.emailInput.sendKeys(Keys.CONTROL + "c");
        registration.phoneInput.sendKeys(customer.getPhone());
        registration.passwordInput.sendKeys(customer.getPassword());
        registration.confirmedPasswordInput.sendKeys(customer.getPassword());
        registration.createAccountButton.click();
        registration.outPage().click();
    }

    public void loginNewCustomer(Customers customer) {
        registration.emailInput.click();
        registration.emailInput.sendKeys(Keys.CONTROL + "v");
        registration.passwordInput.click();
        registration.passwordInput.sendKeys(customer.getPassword() + Keys.ENTER);
        registration.outPage().click();
    }

    public Set<String> listCustomerIds() {
        if (admin.open().isOnThisPage()) {
            admin.enterUsername("admin").enterPassword("admin").submitLogin();
        }
        return customerListPage.open().getCustomerIds();
    }

}