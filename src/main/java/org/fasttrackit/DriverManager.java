package org.fasttrackit;

import org.openqa.selenium.PageLoadStrategy;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;

import java.util.concurrent.TimeUnit;

public class DriverManager {

    private static WebDriver driver;

    public static WebDriver initDriver(String browser) {
        switch (browser.toLowerCase()) {
            default:
                System.out.println("Creating default driver for Chrome...");
            case "chrome":
//                if (System.getProperty("os.name").toLowerCase().contains("windows"))
                System.setProperty("webdriver.chrome.driver",
                        AppConfig.getChromeDriverPath());

                ChromeOptions options = new ChromeOptions();
                options.addArguments("enable-automation");

                if (System.getProperty("headless", "false")
                        .equalsIgnoreCase("true")) {
                    System.out.println("Running headless chrome.");
                    options.addArguments("--headless");
                }

                options.addArguments("--window-size=1920,1080");
                options.addArguments("--no-sandbox");
                options.addArguments("--disable-extensions");
                options.addArguments("--dns-prefetch-disable");
                options.addArguments("--disable-gpu");
                options.setPageLoadStrategy(PageLoadStrategy.NORMAL);

                driver = new ChromeDriver(options);
                break;
            case "firefox":
                System.setProperty("webdriver.gecko.driver",
                        AppConfig.getGeckoDriverPath());
                driver = new FirefoxDriver();
                break;
            case "ie":
                System.setProperty("webdriver.ie.driver",
                        AppConfig.getIeDriverPath());
                driver = new InternetExplorerDriver();
                break;
        }

        // move the hardcoded timeout value to properties files
        driver.manage().timeouts().implicitlyWait(AppConfig.getTimeout(), TimeUnit.SECONDS);
        driver.manage().timeouts().pageLoadTimeout(AppConfig.getTimeout(), TimeUnit.SECONDS);
        driver.manage().timeouts().setScriptTimeout(AppConfig.getTimeout(), TimeUnit.SECONDS);

        driver.manage().window().maximize();

        return driver;
    }

    public static WebDriver getDriver() {
        return driver;
    }
}
