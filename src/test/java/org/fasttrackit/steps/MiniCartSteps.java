package org.fasttrackit.steps;

import cucumber.api.java.en.Then;
import org.fasttrackit.TestBase;

public class MiniCartSteps extends TestBase {

    @Then("^the previously stored product name is displayed in mini-cart$")
    public void thePreviouslyStoredProductNameIsDisplayedInMiniCart() {
        System.out.println("Previously stored product: " +
                getStepVariables().get("addToCartProductName"));

        // todo: Implement assertion
    }
}
