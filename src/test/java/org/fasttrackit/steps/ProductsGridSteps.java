package org.fasttrackit.steps;

import cucumber.api.PendingException;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Then;
import org.fasttrackit.TestBase;
import org.fasttrackit.pageobjects.ProductsGrid;
import org.openqa.selenium.support.PageFactory;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class ProductsGridSteps extends TestBase {

    private ProductsGrid productsGrid =
            PageFactory.initElements(driver, ProductsGrid.class);

    @And("^I sort products by \"([^\"]*)\" in (.+) order$")
    public void iSortProductsBy(String sortCriteria, String sortDirection) {
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

    @Then("^all products are sorted by \"([^\"]*)\" in (.+) order$")
    public void allProductsAreSortedByInAscendingOrder(String sortCriteria, String sortDirection) {
        Comparator comparator;
        if (sortDirection.equalsIgnoreCase("ascending")) {
            comparator = Comparator.naturalOrder();
        } else {
            comparator = Comparator.reverseOrder();
        }

        if (sortCriteria.equalsIgnoreCase("Price")) {
            assertCorrectSortByPrice(comparator);
        } else if (sortCriteria.equalsIgnoreCase("Name")) {
            assertCorrectSortByName(comparator);
        } else {
            throw new PendingException("Assertion for sort by "
                    + sortCriteria + " not implemented.");
        }
    }

    private void assertCorrectSortByName(Comparator comparator) {
        List<String> names = productsGrid.getProductNames();
        List<String> sortedNames = new ArrayList<>(names);
        sortedNames.sort(comparator);
        assertThat("Products are not sorted correctly.",
                names, equalTo(sortedNames));
    }

    private void assertCorrectSortByPrice(Comparator comparator) {
        List<Double> prices = productsGrid.getActualProductPricesAsDoubles();
        List<Double> sortedPrices = new ArrayList<>(prices);
        sortedPrices.sort(comparator);
        assertThat("Products are not sorted correctly.",
                prices, equalTo(sortedPrices));
    }
}
