package org.fasttrackit.pageobjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.Select;

import java.util.List;

public class ProductsGrid {

    @FindBy(css = ".product-name > a")
    private List<WebElement> productNameContainers;

    @FindBy(className = "link-compare")
    private List<WebElement> addToCompareLinks;

    @FindBy(css = ".sort-by select")
    private WebElement sortBySelectList;

    @FindBy(className = "sort-by-switcher")
    private WebElement sortDirectionButton;

    public WebElement getSortDirectionButton() {
        return sortDirectionButton;
    }

    public Select getSortBySelectList() {
        return new Select(sortBySelectList);
    }

    public List<WebElement> getProductNameContainers() {
        return productNameContainers;
    }

    public List<WebElement> getAddToCompareLinks() {
        return addToCompareLinks;
    }

    public WebElement getAddToCartButton(String productName, WebDriver driver) {
        return driver.findElement(By.xpath(
                "//div[@class='product-info' and .//a[text()='"
                        + productName + "']]//button[@title='Add to Cart']"));
    }

    public void addProductToCart(String productName, WebDriver driver) {
        getAddToCartButton(productName, driver).click();
    }
}
