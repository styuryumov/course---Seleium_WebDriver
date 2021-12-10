package ru.stqa.selenium.test;


import com.tngtech.java.junit.dataprovider.DataProviderRunner;
import com.tngtech.java.junit.dataprovider.UseDataProvider;
import org.junit.Test;
import org.junit.runner.RunWith;
import ru.stqa.selenium.model.Iterations;


import java.io.IOException;


@RunWith(DataProviderRunner.class)

public class CartTest extends TestBase {


    @Test
    @UseDataProvider(value = "validIterations", location = DataProviders.class)
    public void addedAndRemovedProduct(Iterations iterations) throws IOException {
        app.openHomePage();
        for (int i = 0; i < iterations.getIterations(); i++) {
            app.productSelection();
            app.addProductToCart(i, iterations.getSize());
        }
        app.removingProductFromTheCart();
    }
}



