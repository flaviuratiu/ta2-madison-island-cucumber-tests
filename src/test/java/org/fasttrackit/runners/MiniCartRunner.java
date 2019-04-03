package org.fasttrackit.runners;

import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(
        glue = "org.fasttrackit",
        features = "src/test/resources/features/mini-cart.feature",
        plugin = {"html:target/cucumber-html-reports", "json:target/json-reports/miniCart.json"}
)
public class MiniCartRunner {
}
