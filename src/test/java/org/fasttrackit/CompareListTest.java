package org.fasttrackit;

import org.fasttrackit.pageobjects.Header;
import org.fasttrackit.pageobjects.ProductsGrid;
import org.junit.Test;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;

import java.util.ArrayList;
import java.util.List;

public class CompareListTest extends TestBase {

    @Test
    public void addProductsToCompareListTest() {
        Header header =
                PageFactory.initElements(driver, Header.class);

        header.search("vase");

        ProductsGrid productsGrid =
                PageFactory.initElements(driver, ProductsGrid.class);

        for (WebElement addToCompareLink : productsGrid.getAddToCompareLinks()) {
            addToCompareLink.click();
        }

        // store the names of all products displayed on the page
        // in a list of Strings
        List<String> productNames = new ArrayList<>();

        for (WebElement productNameContainer : productsGrid.getProductNameContainers()) {
            String name = productNameContainer.getText();
            productNames.add(name);
        }

        // press the Compare button

        // switch to the new window (with the Compare list)

        // assert that the Compare list contains all product names previously stored



    }
}
