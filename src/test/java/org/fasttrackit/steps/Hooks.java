package org.fasttrackit.steps;

import cucumber.api.Scenario;
import cucumber.api.java.After;
import cucumber.api.java.Before;
import org.fasttrackit.DriverManager;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;


public class Hooks {

    @Before
    public void setup(Scenario scenario) {
        String browser = System.getProperty("browser", "chrome");
        DriverManager.initDriver(browser);
    }

    @After
    public void tearDown(Scenario scenario) throws InterruptedException {
        Thread.sleep(500);

//        scenario.write("Custom information...");

        if (scenario.isFailed()) {
            byte[] screenshot = ((TakesScreenshot) DriverManager.getDriver())
                    .getScreenshotAs(OutputType.BYTES);
            scenario.embed(screenshot, "image/png");
        }

        DriverManager.getDriver().quit();
    }
}
