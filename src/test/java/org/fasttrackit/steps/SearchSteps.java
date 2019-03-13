package org.fasttrackit.steps;

import cucumber.api.java.en.And;
import org.fasttrackit.TestBase;
import org.fasttrackit.pageobjects.Header;
import org.openqa.selenium.support.PageFactory;

public class SearchSteps extends TestBase {

    private Header header = PageFactory.initElements(driver, Header.class);

    @And("^I search products by \"([^\"]*)\"$")
    public void iSearchProductsBy(String keyword) {
        header.search(keyword);
    }
}
