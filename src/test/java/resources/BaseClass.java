package resources;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.time.Duration;
import java.util.Properties;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.testng.ITestResult;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;

import io.github.bonigarcia.wdm.WebDriverManager;
import utils.ExtentReport;

public class BaseClass {
    protected static WebDriver driver;// Static WebDriver instance to be used across tests
    
    @BeforeClass
    public void setUp() {
    	// Initialize Extent Report
        ExtentReport.getInstance();
        // Read configuration from config.properties
        Properties prop = new Properties();
        try (FileInputStream input = new FileInputStream("Utilities/config.properties")) {
            prop.load(input);
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        
     // Retrieve browser and headless mode configuration from properties
        String browser = prop.getProperty("browser");
        boolean headless = Boolean.parseBoolean(prop.getProperty("headless"));

     // Initialize WebDriver based on specified browser
        if (driver == null) {
            if (browser.equalsIgnoreCase("chrome")) {
            	// Setup ChromeDriver using WebDriverManager
                WebDriverManager.chromedriver().setup();
                ChromeOptions options = new ChromeOptions();
                if (headless) {
                    options.addArguments("--headless");// Set Chrome to run in headless mode if configured
                }
                driver = new ChromeDriver(options);
            } else if (browser.equalsIgnoreCase("edge")) {
            	// Setup EdgeDriver using WebDriverManager
                WebDriverManager.edgedriver().setup();
                EdgeOptions options = new EdgeOptions();
                if (headless) {
                	// Edge does not support headless mode, throw an exception if configured
                    throw new IllegalArgumentException("Headless mode is not supported for Edge browser.");
                }
                driver = new EdgeDriver(options);
            } else if (browser.equalsIgnoreCase("firefox")) {
            	// Setup FirefoxDriver using WebDriverManager
            	FirefoxOptions options = new FirefoxOptions();
                if (headless) {
                    options.addArguments("--headless");// Set Firefox to run in headless mode if configured
                }
                // No need to specify the binary path, the system will use the default installed Firefox
                driver = new FirefoxDriver(options);
            } else {
                throw new IllegalArgumentException("Invalid browser specified");// Throw exception for invalid browser
            }
        }

        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(3));//Apply the Implicit Wait for 3 seconds
    }
    
    @AfterMethod
    public void tearDown(ITestResult result) {
    	// Method runs after each test method execution
        if (result.getStatus() == ITestResult.FAILURE) {
            System.out.println("Test failed: " + result.getThrowable());// Print failure message
        } else if (result.getStatus() == ITestResult.SKIP) {
            System.out.println("Test skipped: " + result.getThrowable());// Print skip message
        } else {
            System.out.println("Test passed");// Print pass message
        }
    }

    @AfterClass
    public void tearDown() {
    	  // Method runs after all test methods in the class have been run
        if (driver != null) {
            driver.quit();// Quit WebDriver instance
            driver = null;
        }
     // Flush Extent Report at the end of the class
        ExtentReport.flush(); // Flush Extent Report to write all information
    }
    
    // Method to capture screenshot
    public static void getScreenshot(String screenshotPath) throws IOException {
        // Method for taking screenshots if needed
    	TakesScreenshot ts = (TakesScreenshot) driver;// Convert WebDriver instance to TakesScreenshot
        File source = ts.getScreenshotAs(OutputType.FILE);// Capture screenshot as File
        FileUtils.copyFile(source, new File(screenshotPath)); // Copy screenshot file to specified path
    }
}

