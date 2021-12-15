package ru.stqa.selenium.pages;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import java.util.Properties;
import java.util.Set;

import static java.util.stream.Collectors.toSet;

public class AdminPage extends Page {
    Properties prop;

    public AdminPage(WebDriver driver) {
        super(driver);
    }

    public AdminPage open() {
        driver.get("http://localhost/litecart/admin");
        return this;
    }

    public boolean isOnThisPage() {
        return driver.findElements(By.id("box-login")).size() > 0;
    }

    public AdminPage enterUsername(String username) {
        driver.findElement(By.name("username")).sendKeys(username);
        return this;
    }

    public AdminPage enterPassword(String password) {
        driver.findElement(By.name("password")).sendKeys(password);
        return this;
    }

    public void submitLogin() {
        driver.findElement(By.name("login")).click();
        wait.until((WebDriver d) -> d.findElement(By.id("box-apps-menu")));
    }

}
