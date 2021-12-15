package ru.stqa.selenium.test;

import com.tngtech.java.junit.dataprovider.DataProvider;
import ru.stqa.selenium.model.Customers;
import ru.stqa.selenium.model.Iterations;

import java.util.Random;

public class DataProviders {

    @DataProvider
    public static Object[][] validIterations() {
        return new Object[][]{
                {
                        Iterations.newEntity()
                                .withIterations(3).withSize("Small").build()},
        };
    }

    @DataProvider
    public static Object [][] validCustomers() {
        return new Object[][]{
                {
                        Customers.newEntity()
                                .withFirstname("Adam").withLastname("Maki").withPhone("+19287475617")
                                .withEmail("adam-" + System.currentTimeMillis() + "@test.com")
                                .withCity("Miami").withPostcode("33101").withAddress("Elm Street")
                                .withCountry("US").withZone("FL")
                                .withPassword("adam_1238_A").build() },
        };
    }

}
