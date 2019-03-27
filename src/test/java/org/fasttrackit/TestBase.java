package org.fasttrackit;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;

import java.util.HashMap;
import java.util.Map;

public class TestBase {

    protected WebDriver driver = DriverManager.getDriver();

    private static final Map<String, Object> STEP_VARIABLES = new HashMap<>();

    public void waitForPageToLoad(long timeoutMillis) {
        do {
            long waitTime = 500;
            try {
                Thread.sleep(waitTime);
            } catch (InterruptedException e) {
                System.out.println("Wait interrupted. " + e.getMessage());
            }

            timeoutMillis -= waitTime;
            System.out.println("Waiting for page to load. Remaining millis: " +
                    timeoutMillis);
        } while (timeoutMillis > 0 && !((JavascriptExecutor) driver)
                .executeScript("return document.readyState")
                .equals("complete"));

    }

    public static Map<String, Object> getStepVariables() {
        return STEP_VARIABLES;
    }
}
