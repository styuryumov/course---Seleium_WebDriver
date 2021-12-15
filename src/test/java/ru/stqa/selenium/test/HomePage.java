package ru.stqa.selenium.test;

import com.tngtech.java.junit.dataprovider.DataProviderRunner;
import com.tngtech.java.junit.dataprovider.UseDataProvider;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import ru.stqa.selenium.model.Customers;

import java.io.IOException;
import java.util.Set;

@RunWith(DataProviderRunner.class)

public class HomePage extends TestBase {

    @Test
    @UseDataProvider(value = "validCustomers", location = DataProviders.class)
    public void creatingNewCustomer(Customers customer) throws IOException {
        Set<String> oldIds = app.listCustomerIds();
        app.registrationNewCustomer(customer);
        app.loginNewCustomer(customer);
        Set<String> newIds = app.listCustomerIds();
        Assert.assertTrue(newIds.containsAll(oldIds));
        Assert.assertTrue(newIds.size() == oldIds.size() + 1);
    }
}
