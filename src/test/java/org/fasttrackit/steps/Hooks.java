package org.fasttrackit.steps;

import cucumber.api.Scenario;
import cucumber.api.java.After;
import cucumber.api.java.Before;
import org.fasttrackit.DriverManager;


public class Hooks {

    @Before
    public void setup(Scenario scenario) {
        String browser = System.getProperty("browser", "chrome");
        DriverManager.initDriver(browser);
    }

    @After
    public void tearDown(Scenario scenario) throws InterruptedException {
        Thread.sleep(500);
        DriverManager.getDriver().quit();
    }
}
