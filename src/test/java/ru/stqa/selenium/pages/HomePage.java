package ru.stqa.selenium.pages;


import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import ru.stqa.selenium.app.Application;


import java.io.IOException;
import java.util.List;
import java.util.Properties;


public class HomePage extends Page {
    Properties prop;
    public HomePage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
    }

    public void openHome() throws IOException {
        prop = new Properties();
        prop.load(Application.class.getResourceAsStream("/testData.properties"));
        driver.get((prop.getProperty("baseUrl")) + "/" + (prop.getProperty("langu") + "/"));
    }

    @FindBy(css = "div#box-most-popular li.product")
    public List<WebElement> addProductSelection;

}
