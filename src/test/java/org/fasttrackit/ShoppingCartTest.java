package org.fasttrackit;

import org.fasttrackit.pageobjects.Header;
import org.fasttrackit.pageobjects.ProductsGrid;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

public class ShoppingCartTest extends TestBase {

    @Test
    public void addToCartFromSearchResultsTest() {
        String keyword = "vase";

        Header header = PageFactory.initElements(driver, Header.class);
        header.search(keyword);

        ProductsGrid productsGrid =
                PageFactory.initElements(driver, ProductsGrid.class);

        String productName = "Herald Glass Vase";

        productsGrid.addProductToCart(productName, driver);

        String successMessage = driver.findElement(By.className("success-msg"))
                .getText();

        assertThat("Unexpected success message", successMessage,
                is(productName + " was added to your shopping cart."));

        WebElement productNameInCart = driver.findElement(By.xpath(
                "//table[@id='shopping-cart-table']//a[text()='" +
                        productName + "']"));

        assertThat("Product not displayed in cart.",
                productNameInCart.isDisplayed());
    }
}
