package org.fasttrackit;

import org.fasttrackit.pageobjects.Header;
import org.fasttrackit.pageobjects.ProductsGrid;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;

import java.util.Arrays;
import java.util.List;

import static org.hamcrest.CoreMatchers.containsString;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

@RunWith(Parameterized.class)
public class SearchTest extends TestBase {

    private String keyword;

    public SearchTest(String keyword) {
        this.keyword = keyword;
    }

    @Parameterized.Parameters
    public static List<String> inputData() {
        return Arrays.asList("vase", "camera");
    }

    @Test
    public void searchByOneKeywordTest() {
        Header header =
                PageFactory.initElements(driver, Header.class);

        header.search(keyword);

        waitForPageToLoad(4000);

        String searchFieldValue = header.getSearchField().getAttribute("value");
        assertThat("Searched keyword not preserved in search field",
                searchFieldValue, is(keyword));

        ProductsGrid productsGrid =
                PageFactory.initElements(driver, ProductsGrid.class);

        for (WebElement container : productsGrid.getProductNameContainers()) {
            String productName = container.getText();

            container.click();
            System.out.println(productName);
            assertThat("Some of the product names do not contain the searched keyword",
                    productName, containsString(keyword.toUpperCase()));
        }
    }
}
