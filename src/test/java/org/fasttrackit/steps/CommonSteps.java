package org.fasttrackit.steps;

import cucumber.api.java.en.And;
import org.fasttrackit.TestBase;
import org.openqa.selenium.By;

public class CommonSteps extends TestBase{

    @And("^I click on the \"([^\"]*)\" link$")
    public void iClickOnTheLink(String linkText) throws Throwable {
        driver.findElement(By.linkText(linkText));
    }
}
