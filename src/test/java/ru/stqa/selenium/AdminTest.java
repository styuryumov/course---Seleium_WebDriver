package ru.stqa.selenium;

import org.checkerframework.checker.nullness.compatqual.NullableDecl;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;

import static org.openqa.selenium.support.ui.ExpectedConditions.presenceOfElementLocated;

import java.awt.*;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.StringSelection;
import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Collections;
import java.util.Set;

public class AdminTest extends TestBase {

    @Before
    public void enterTheLink() {
        driver.get(prop.getProperty("baseUrl") + "/admin");
        driver.findElement(By.name("username")).sendKeys(prop.getProperty("login"));
        driver.findElement(By.name("password")).sendKeys(prop.getProperty("password"));
        driver.findElement(By.name("login")).click();
        wait.until(driver -> driver.findElements(By.cssSelector("div#body-wrapper")).size() > 0);
    }


    @Test
    public void testAdminPanel() {
        List<WebElement> list = driver.findElements(By.cssSelector("li#app-"));
        int iterations = list.size();

        for (int i = 0; i < iterations; i++) {
            list = driver.findElements(By.cssSelector("li#app-"));
            list.get(i).click();
            wait.until(presenceOfElementLocated(By.cssSelector("h1")));

            if (isElementsPresent(driver, By.cssSelector("ul.docs [id*=doc-]"))) {
                List<WebElement> nestedElements = driver.findElements(By.cssSelector("ul.docs [id*=doc-]"));

                for (int j = 0; j < nestedElements.size(); j++) {
                    nestedElements = driver.findElements(By.cssSelector("ul.docs [id*=doc-]"));
                    nestedElements.get(j).click();
                    wait.until(presenceOfElementLocated(By.cssSelector("h1")));
                }
            }
        }
    }

    @Test
    public void testSortCountries() {
        List<WebElement> list = driver.findElements(By.cssSelector("li#app-"));
        list.get(2).click();

        List<String> sCountries = new ArrayList<>();
        WebElement tableCountries = driver.findElement(By.cssSelector("table.dataTable"));
        List<WebElement> allRowsCountries = tableCountries.findElements(By.cssSelector("tr"));
        for (int i = 1; i < allRowsCountries.size() - 1; i++) {
            List<WebElement> cellsCountries = allRowsCountries.get(i).findElements(By.cssSelector("td"));
            sCountries.add(cellsCountries.get(4).getAttribute("textContent"));
        }
        List<String> sortCountries = new ArrayList<>();
        sortCountries.addAll(sCountries);
        Collections.sort(sortCountries);
        Assert.assertEquals(sortCountries, sCountries);

        List<String> countryZones = new ArrayList<>();
        WebElement tableCountriesZones = driver.findElement(By.cssSelector("table.dataTable"));
        List<WebElement> allRowsCountriesZones = tableCountriesZones.findElements(By.cssSelector("tr"));
        int iterations = allRowsCountriesZones.size();
        for (int i = 1; i < iterations - 1; i++) {
            List<WebElement> cellsZones = allRowsCountriesZones.get(i).findElements(By.cssSelector("td"));
            int zone = Integer.parseInt(cellsZones.get(5).getAttribute("textContent"));
            if (zone > 0) {
                cellsZones.get(4).findElement(By.tagName("a")).click();
                WebElement tableZones = driver.findElement(By.cssSelector("table#table-zones"));
                List<WebElement> allRowsZones = tableZones.findElements(By.cssSelector("tr"));
                for (int j = 1; j < allRowsZones.size() - 1; j++) {
                    List<WebElement> cellsZone = allRowsZones.get(j).findElements(By.cssSelector("td"));
                    countryZones.add(cellsZone.get(2).getAttribute("textContent"));
                }
                List<String> sortCountriesZones = new ArrayList<>();
                sortCountriesZones.addAll(countryZones);
                Assert.assertEquals(sortCountriesZones, countryZones);
                driver.navigate().back();
                tableCountriesZones = driver.findElement(By.cssSelector("table.dataTable"));
                allRowsCountriesZones = tableCountriesZones.findElements(By.cssSelector("tr"));
            }
        }
    }

    @Test
    public void testSortGeoZones() {
        List<WebElement> list = driver.findElements(By.cssSelector("li#app-"));
        list.get(5).click();

        List<String> zones = new ArrayList<>();
        List<String> sortZones = new ArrayList<>();
        List<WebElement> allRowsZones = driver.findElements(By.cssSelector("table.dataTable tr"));
        int iterations = allRowsZones.size();
        for (int i = 1; i < iterations - 1; i++) {
            allRowsZones = driver.findElements(By.cssSelector("table.dataTable tr"));
            List<WebElement> cellsZones = allRowsZones.get(i).findElements(By.cssSelector("td"));
            cellsZones.get(2).findElement(By.tagName("a")).click();
            List<WebElement> rowsGeoZones = driver.findElements(By.cssSelector("table#table-zones tr"));
            int iterationsRow = rowsGeoZones.size();
            zones.clear();
            sortZones.clear();
            for (int j = 1; j < iterationsRow - 1; j++) {
                rowsGeoZones = driver.findElements(By.cssSelector("table#table-zones tr"));
                List<WebElement> cellsZonesEdit = rowsGeoZones.get(j).findElements(By.cssSelector("td"));
                String str = cellsZonesEdit.get(2).findElement(By.cssSelector("select")).getAttribute("value");
                zones.add(cellsZonesEdit.get(2).findElement(By.cssSelector("[value=" + str + "]")).getAttribute("textContent"));
            }
            sortZones.addAll(zones);
            Collections.sort(sortZones);
            Assert.assertEquals(sortZones, zones);
            driver.navigate().back();
        }
    }

    @Test
    public void creationNewProduct() throws InterruptedException {
        List<WebElement> list = driver.findElements(By.cssSelector("li#app-"));
        list.get(1).click();
        Assert.assertEquals("Catalog", driver.findElement(By.cssSelector("td#content h1")).getText());
        List<WebElement> tableBefore = driver.findElements(By.cssSelector("table.dataTable tr.row"));
        List<WebElement> addNew = driver.findElements(By.cssSelector("td#content a.button"));
        addNew.get(1).click();
        List<WebElement> tabs = driver.findElements(By.cssSelector("div.tabs li a"));
        // Filling out the sheet general
        List<WebElement> listGeneral = driver.findElements(By.cssSelector("div#tab-general [name]"));
        listGeneral.get(0).click();
        listGeneral.get(2).sendKeys("Bat-Duck");
        driver.findElement(By.cssSelector("div#tab-general [name=code]")).sendKeys("rd006");
        checkbox(driver, By.cssSelector("div.input-wrapper [name*=categories]"), 1);
        checkbox(driver, By.cssSelector("div.input-wrapper [name*=product_groups]"), 2);
        driver.findElement(By.cssSelector("div#tab-general [name=quantity]")).clear();
        driver.findElement(By.cssSelector("div#tab-general [name=quantity]")).sendKeys("30");
        Select selectSoldOutStatus = new Select(driver.findElement(By.cssSelector("div#tab-general [name=sold_out_status_id]")));
        selectSoldOutStatus.selectByValue("2");
        File file = new File("src\\test\\resources\\Duck.jpg");
        driver.findElement(By.cssSelector("div#tab-general [type=file]")).sendKeys(file.getAbsolutePath());
        driver.findElement(By.cssSelector("div#tab-general [name=date_valid_from]")).click();
        driver.findElement(By.cssSelector("div#tab-general [name=date_valid_from]")).sendKeys("26.11.2021");
        driver.findElement(By.cssSelector("div#tab-general [name=date_valid_to]")).click();
        driver.findElement(By.cssSelector("div#tab-general [name=date_valid_to]")).sendKeys("26.01.2022");
        // switch to tab Information
        tabs.get(1).click();
        Thread.sleep(1500);
        Select selectManufacturer = new Select(driver.findElement(By.cssSelector("div#tab-information [name=manufacturer_id]")));
        selectManufacturer.selectByValue("1");
        String shotDescription = "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Suspendisse sollicitudin ante massa, eget ornare libero porta congue.";
        StringSelection shotDescriptionSelection = new StringSelection(shotDescription);
        Clipboard clipboardShotDescription = Toolkit.getDefaultToolkit().getSystemClipboard();
        clipboardShotDescription.setContents(shotDescriptionSelection, null);
        driver.findElement(By.cssSelector("div#tab-information [name*=short_description]")).sendKeys(Keys.CONTROL + "v");
        String description = "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Suspendisse sollicitudin ante massa, eget ornare libero porta congue. Cras scelerisque dui non " +
                "consequat sollicitudin. Sed pretium tortor ac auctor molestie. Nulla facilisi. Maecenas pulvinar nibh vitae lectus vehicula semper. Donec et aliquet velit. Curabitur non ullamcorper mauris. In hac habitasse platea dictumst. Phasellus " +
                "ut pretium justo, sit amet bibendum urna. Maecenas sit amet arcu pulvinar, facilisis quam at, viverra nisi. Morbi sit amet adipiscing ante. Integer imperdiet volutpat ante, sed venenatis urna volutpat a. Proin justo massa, convallis " +
                "vitae consectetur sit amet, facilisis id libero.";
        StringSelection descriptionSelection = new StringSelection(description);
        Clipboard clipboardDescription = Toolkit.getDefaultToolkit().getSystemClipboard();
        clipboardDescription.setContents(descriptionSelection, null);
        driver.findElement(By.cssSelector("div.trumbowyg-editor")).sendKeys(Keys.CONTROL + "v");
        //// switch to tab Prices
        tabs.get(3).click();
        Thread.sleep(1500);
        driver.findElement(By.cssSelector("div#tab-prices [name=purchase_price]")).clear();
        driver.findElement(By.cssSelector("div#tab-prices [name=purchase_price]")).sendKeys("10");
        List<WebElement> listCurrencySheet = driver.findElements(By.cssSelector("div#tab-prices [name*=gross_prices]"));
        listCurrencySheet.get(0).clear();
        listCurrencySheet.get(0).sendKeys("25,00");
        driver.findElement(By.cssSelector("span.button-set [name=save]")).click();
        // verification of successful product addition
        Thread.sleep(1500);
        driver.findElement(By.cssSelector("td#content [name=query]")).sendKeys("Bat-Duck" + Keys.ENTER);
        list.clear();
        wait.until(ExpectedConditions.textToBePresentInElementLocated(By.cssSelector("table.dataTable a"), "Bat-Duck"));
        list = driver.findElements(By.cssSelector("table.dataTable a"));
        Assert.assertEquals("Bat-Duck", list.get(0).getText());
    }

    @Test
    public void testExternalLinkByCountries() {
        List<WebElement> list = driver.findElements(By.cssSelector("li#app-"));
        list.get(2).click();
        driver.findElement(By.cssSelector("td#content a.button")).click();
        String activeWindow = driver.getWindowHandle();
        Set<String> oldWindows = driver.getWindowHandles();
        List<WebElement> externalLinks = driver.findElements(By.cssSelector("td#content i.fa.fa-external-link"));
        int iterations = externalLinks.size();
        for (int i = 0; i < iterations; i++) {
            externalLinks.get(i).click();
            String newWindow = wait.until(new ExpectedCondition<String>() {
                @NullableDecl
                @Override
                public String apply(@NullableDecl WebDriver driver) {
                    Set<String> newWindows = driver.getWindowHandles();
                    newWindows.removeAll(oldWindows);
                    return newWindows.size() > 0 ? newWindows.iterator().next() : null;
                }
            });
            driver.switchTo().window(newWindow);
            driver.close();
            driver.switchTo().window(activeWindow);
            Assert.assertEquals("Add New Country", driver.findElement(By.cssSelector("td#content h1")).getText());
        }
        driver.findElement(By.cssSelector("span.button-set [name=cancel]")).click();
        Assert.assertEquals("Countries", driver.findElement(By.cssSelector("td#content h1")).getText());
    }
}

