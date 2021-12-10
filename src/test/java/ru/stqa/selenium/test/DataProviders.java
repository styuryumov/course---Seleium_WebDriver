package ru.stqa.selenium.test;

import com.tngtech.java.junit.dataprovider.DataProvider;
import ru.stqa.selenium.model.Iterations;

public class DataProviders {

    @DataProvider
    public static Object[][] validIterations() {
        return new Object[][]{
                {
                        Iterations.newEntity()
                                .withIterations(3).withSize("Small").build()},
        };
    }

}
