package ru.stqa.selenium;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import static org.openqa.selenium.support.ui.ExpectedConditions.presenceOfElementLocated;

import java.io.File;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Collections;

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

            if (areElementsPresent(driver, By.cssSelector("ul.docs [id*=doc-]"))) {
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
}

