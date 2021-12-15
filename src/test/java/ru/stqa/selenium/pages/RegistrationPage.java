package ru.stqa.selenium.pages;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;
import ru.stqa.selenium.model.Customers;

import java.util.List;

public class RegistrationPage extends Page {

    public RegistrationPage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
    }

    @FindBy(css = "div#box-account-login a")
    public WebElement openRegistrationPage;

    public void pageValidation() {
        Assert.assertEquals("Create Account", driver.findElement(By.cssSelector("div#create-account h1")).getAttribute("textContent"));
    }

    @FindBy(css = "div.content [name=firstname]")
    public WebElement firstnameInput;

    @FindBy(css = "div.content [name=lastname]")
    public WebElement lastnameInput;

    @FindBy(css = "div.content [name=address1]")
    public WebElement addressInput;

    @FindBy(css = "div.content [name=postcode]")
    public WebElement postcodeInput;

    @FindBy(css = "div.content [name=city]")
    public WebElement cityInput;

    public void selectCountry(String country) {
        driver.findElement(By.cssSelector("[id ^= select2-country_code]")).click();
        driver.findElement(By.cssSelector(
                String.format(".select2-results__option[id $= %s", country))).click();
    }

    public void selectZone(String zone) {
        wait.until((WebDriver d) -> d.findElement(
                By.cssSelector(String.format("select[name=zone_code] option[value=%s]", zone))));
        new Select(driver.findElement(By.cssSelector("select[name=zone_code]"))).selectByValue(zone);
    }

    @FindBy(css = "div.content [name=email]")
    public WebElement emailInput;

    @FindBy(css = "div.content [name=phone]")
    public WebElement phoneInput;

    @FindBy(css = "div.content [name=password]")
    public WebElement passwordInput;

    @FindBy(css = "div.content [name=confirmed_password]")
    public WebElement confirmedPasswordInput;

    @FindBy(css = "div.content [name=create_account]")
    public WebElement createAccountButton;

    @FindBy(css = "div#box-account ul.list-vertical li")
    public List<WebElement> listOutRegistration;

    public WebElement outPage() {
        return listOutRegistration.get(3).findElement(By.cssSelector("a"));
    }

}
