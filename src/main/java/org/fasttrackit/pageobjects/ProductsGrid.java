package org.fasttrackit.pageobjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.Select;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

public class ProductsGrid {

    @FindBy(css = ".product-name > a")
    private List<WebElement> productNameContainers;

    @FindBy(xpath = "//span[@class='price' and " +
            "./parent::*[not(contains(@class, 'old-price'))]]")
    private List<WebElement> actualProductPriceContainers;

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

    public List<String> getProductNames() {
        List<String> names = new ArrayList<>();

        for (WebElement nameContainer : productNameContainers) {
            String name = nameContainer.getText();
            names.add(name);
        }

        return names;

        // same as above, but easier with Java 8 stream api
//        return productNameContainers.stream()
//                .map(WebElement::getText)
//                .collect(Collectors.toList());

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

    public List<WebElement> getActualProductPriceContainers() {
        return actualProductPriceContainers;
    }

    // Double - wrapper class
    public List<Double> getActualProductPricesAsDoubles() {
        List<Double> convertedPrices = new ArrayList<>();

        for (WebElement priceContainer : actualProductPriceContainers) {
            String priceAsText = priceContainer.getText();

            // Matching: any character except (^) dash,
            // at least 1 character (+),
            // followed by any character (.), at least 1 occurrence (+)
            // Extracting first part, before dash
            Pattern pattern = Pattern.compile("([^ ]+).+");
            Matcher matcher = pattern.matcher(priceAsText);

            if (matcher.find()) {
                String priceTextWithoutCurrency = matcher.group(1);

                priceTextWithoutCurrency =
                        priceTextWithoutCurrency.
                                replace(",", ".");

                double convertedPrice = Double.parseDouble(priceTextWithoutCurrency);

                convertedPrices.add(convertedPrice);
            }
        }

        return convertedPrices;
    }
}
