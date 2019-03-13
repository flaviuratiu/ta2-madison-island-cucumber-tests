package org.fasttrackit.steps;

import cucumber.api.java.en.And;
import org.fasttrackit.TestBase;
import org.fasttrackit.pageobjects.ProductsGrid;
import org.openqa.selenium.support.PageFactory;

public class ProductsGridSteps extends TestBase {

    private ProductsGrid productsGrid =
            PageFactory.initElements(driver, ProductsGrid.class);

    @And("^I sort products by \"([^\"]*)\" in (.+) order$")
    public void iSortProductsByInAscendingOrder(String sortCriteria, String sortDirection) {
        productsGrid.getSortBySelectList().selectByVisibleText(sortCriteria);

        String sortDirectionButtonClass = productsGrid.getSortDirectionButton()
                .getAttribute("class");

        // not very clear... maybe not the best implementation
        if (
                (
                        sortDirectionButtonClass.contains("--asc") &&
                                sortDirection.equals("descending")
                ) ||
                        (sortDirectionButtonClass.contains("--desc") &&
                                sortDirection.equals("ascending"))
                ) {
            productsGrid.getSortDirectionButton().click();
        }


    }
}
