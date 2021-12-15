package ru.stqa.selenium;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.Color;
import org.openqa.selenium.Keys;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;


import java.util.List;


public class HomePage extends TestBase {
    ProjectTools tools = new ProjectTools();

    @Before
    public void enterTheLink() {
        driver.get((prop.getProperty("baseUrl")) + "/" + (prop.getProperty("langu") + "/"));
    }

    @Test
    public void checkingTheComplianceOfTheStickersWithTheEstablishedRequirements() {
        List<WebElement> listProduct = driver.findElements(By.cssSelector("div#main li.hover-light"));

        for (int i = 0; i < listProduct.size(); i++) {
            WebElement element = listProduct.get(i);
            Assert.assertTrue(element.findElements(By.cssSelector("div.sticker")).size() == 1);
        }
    }

    @Test
    public void verifyingThatTheCorrectProductPageIsOpening() {
        //product data on the home page
        String nameHomePage = driver.findElement(By.cssSelector("div#box-campaigns div.name")).getText();
        String regularPriceHomePage = driver.findElement(By.cssSelector("div#box-campaigns s.regular-price")).getText();
        String campaignPriceHomePage = driver.findElement(By.cssSelector("div#box-campaigns strong.campaign-price")).getText();
        String strikeRegularPriceHomePage = driver.findElement(By.cssSelector("div#box-campaigns s.regular-price")).getTagName();
        String strongCampaignPriceHomePage = driver.findElement(By.cssSelector("div#box-campaigns strong.campaign-price")).getTagName();
        String sSizeRegularPriceHomePage = driver.findElement(By.cssSelector("div#box-campaigns s.regular-price")).getCssValue("font-size");
        String sSizeCampaignPriceHomePage = driver.findElement(By.cssSelector("div#box-campaigns strong.campaign-price")).getCssValue("font-size");
        int sizeRegularPriceHomePage = tools.size(sSizeRegularPriceHomePage);
        int sizeCampaignPriceHomePage = tools.size(sSizeCampaignPriceHomePage);
        boolean bSizeHomePage = tools.sizeComparison(sizeRegularPriceHomePage, sizeCampaignPriceHomePage);
        Assert.assertTrue(bSizeHomePage);
        String colorRegularPriceHomePage = driver.findElement(By.cssSelector("div#box-campaigns s.regular-price")).getCssValue("color");
        String colorCampaignPriceHomePage = driver.findElement(By.cssSelector("div#box-campaigns strong.campaign-price")).getCssValue("color");
        Color greyColorHomePage = Color.fromString(colorRegularPriceHomePage);
        Color redColorHomePage = Color.fromString(colorCampaignPriceHomePage);
        Assert.assertTrue(tools.color(greyColorHomePage));
        Assert.assertTrue(tools.color(redColorHomePage));
        //product data on the product page
        driver.findElement(By.cssSelector("div#box-campaigns li.product")).click();
        wait.until(driver -> driver.findElements(By.cssSelector("div#box-product h1")).size() > 0);
        String nameProductPage = driver.findElement(By.cssSelector("div#box-product h1")).getText();
        String regularPriceProductPage = driver.findElement(By.cssSelector("div#box-product s.regular-price")).getText();
        String campaignPriceProductPage = driver.findElement(By.cssSelector("div#box-product strong.campaign-price")).getText();
        String strikeRegularPriceProductPage = driver.findElement(By.cssSelector("div#box-product s.regular-price")).getTagName();
        String strongCampaignPriceProductPage = driver.findElement(By.cssSelector("div#box-product strong.campaign-price")).getTagName();
        String sSizeRegularPriceProductPage = driver.findElement(By.cssSelector("div#box-product s.regular-price")).getCssValue("font-size");
        String sSizeCampaignPriceProductPage = driver.findElement(By.cssSelector("div#box-product strong.campaign-price")).getCssValue("font-size");
        int sizeRegularPriceProductPage = tools.size(sSizeRegularPriceProductPage);
        int sizeCampaignPriceProductPage = tools.size(sSizeCampaignPriceProductPage);
        boolean bSizeProductPage = tools.sizeComparison(sizeRegularPriceProductPage, sizeCampaignPriceProductPage);
        Assert.assertTrue(bSizeProductPage);
        String colorRegularPriceProductPage = driver.findElement(By.cssSelector("div#box-product s.regular-price")).getCssValue("color");
        String colorCampaignPriceProductPage = driver.findElement(By.cssSelector("div#box-product strong.campaign-price")).getCssValue("color");
        Color greyColorProductPage = Color.fromString(colorRegularPriceHomePage);
        Color redColorProductPage = Color.fromString(colorCampaignPriceHomePage);
        Assert.assertTrue(tools.color(greyColorProductPage));
        Assert.assertTrue(tools.color(redColorProductPage));
        driver.navigate().back();
        //checks
        wait.until(driver -> driver.findElements(By.cssSelector("div#box-most-popular")).size() > 0);
        Assert.assertEquals(nameHomePage, nameProductPage);
        Assert.assertEquals(regularPriceHomePage, regularPriceProductPage);
        Assert.assertEquals(campaignPriceHomePage, campaignPriceProductPage);
        Assert.assertEquals(strikeRegularPriceHomePage, strikeRegularPriceProductPage);
        Assert.assertEquals(strongCampaignPriceHomePage, strongCampaignPriceProductPage);
    }

    @Test
    public void registrationAndLoginNewUser() throws InterruptedException {
        driver.findElement(By.cssSelector("div#box-account-login a")).click();
        wait.until(ExpectedConditions.textToBePresentInElementLocated(By.cssSelector("div#create-account h1"), "Create Account"));
        // Registration new user
        driver.findElement(By.cssSelector("div.content [name=firstname]")).sendKeys("Sergey");
        driver.findElement(By.cssSelector("div.content [name=lastname]")).sendKeys("Pshenichnikov-Ivanov");
        driver.findElement(By.cssSelector("div.content [name=address1]")).sendKeys("Mountain View Driver 0514");
        driver.findElement(By.cssSelector("div.content [name=postcode]")).sendKeys("85505");
        driver.findElement(By.cssSelector("div.content [name=city]")).sendKeys("Mesa");
        Select selectCountries = new Select(driver.findElement(By.cssSelector("div#create-account [name=country_code]")));
        selectCountries.selectByValue("US");
        wait.until((WebDriver d) -> d.findElement(By.cssSelector("select[name=zone_code] option[value=AZ]")));
        Select selectZones = new Select(driver.findElement(By.cssSelector("div#create-account select[name=zone_code]")));
        selectZones.selectByValue("AZ");
        driver.findElement(By.cssSelector("div.content [name=email]")).sendKeys("scalex-" + System.currentTimeMillis() + "@test.com");
        driver.findElement(By.cssSelector("div.content [name=email]")).sendKeys(Keys.CONTROL + "a");
        driver.findElement(By.cssSelector("div.content [name=email]")).sendKeys(Keys.CONTROL + "c");
        driver.findElement(By.cssSelector("div.content [name=phone]")).sendKeys("+19287475617");
        driver.findElement(By.cssSelector("div.content [name=password]")).sendKeys("Eqlbcljhjub09");
        driver.findElement(By.cssSelector("div.content [name=confirmed_password]")).sendKeys("Eqlbcljhjub09");
        driver.findElement(By.cssSelector("div.content [name=create_account]")).click();
        List<WebElement> listOutRegistration = driver.findElements(By.cssSelector("div#box-account ul.list-vertical li"));
        listOutRegistration.get(3).findElement(By.cssSelector("a")).click();
        // Login new user
        wait.until((WebDriver d) -> d.findElement(By.cssSelector("div#box-account-login [name=email]")));
        driver.findElement(By.cssSelector("div#box-account-login [name=email]")).click();
        driver.findElement(By.cssSelector("div#box-account-login [name=email]")).sendKeys(Keys.CONTROL + "v");
        driver.findElement(By.cssSelector("div#box-account-login [name=password]")).click();
        driver.findElement(By.cssSelector("div#box-account-login [name=password]")).sendKeys("Eqlbcljhjub09" + Keys.ENTER);
        List<WebElement> listOutLogin = driver.findElements(By.cssSelector("div#box-account ul.list-vertical li"));
        listOutLogin.get(3).findElement(By.cssSelector("a")).click();
    }
}

