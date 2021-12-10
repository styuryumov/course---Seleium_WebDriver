package ru.stqa.selenium.cucumber;

import io.cucumber.java8.En;
import ru.stqa.selenium.model.Iterations;

public class WorkingWithCart extends CucumberTestBase implements En {

    private Iterations.Builder builder = Iterations.newEntity();
    private Iterations iterations;

    public WorkingWithCart() {
        Before("@OpenPage", 4, () -> {
            app.openHomePage();
        });
        Given("choose {string} products from the list Most Popular, the selected products must be added to the cart", (String iteration) -> {
            builder.withIterations(Integer.parseInt(iteration));
        });
        And("for some products it is possible to set the size {string}", (String size) -> {
            iterations = builder.withSize(size).build();
        });
        When("^the user has filled the cart with products$", () -> {
            for (int i = 0; i < iterations.getIterations(); i++) {
                app.productSelection();
                app.addProductToCart(i, iterations.getSize());
            }
        });
        Then("^user can remove products from cart$", () -> {
            app.removingProductFromTheCart();
        });
    }
}
