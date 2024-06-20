package tests;

import java.io.IOException;
import java.time.Duration;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.MediaEntityBuilder;
import com.aventstack.extentreports.Status;
import utils.ExcelUtils;
import pages.CartPage;
import resources.BaseClass;
import utils.ExtentReport;

public class CartTest extends BaseClass {
    private static final Logger logger = LogManager.getLogger(CartTest.class);// Logger instance for logging test information
    private ExtentTest test;// ExtentTest instance for logging test steps and results to the report

    @Test(dataProvider = "cartTestData", groups = {"functional"}, priority = 1, enabled = true)
    public void cartTest(String url, String executionRequired, String email) throws IOException {
    	// Check if test execution is required based on the Excel data
        if (!executionRequired.equalsIgnoreCase("Yes")) {
            logger.info("Test execution not required. Skipping the test.");
            return;// Skip the test if execution is not required
        }

        // Create a new test in Extent Report
        test = ExtentReport.getInstance().createTest("AddtoCartTest");
        
        // Initialize CartPage object
        CartPage cartPage = new CartPage(driver);
        // Navigate to the provided URL and maximize the browser window
        driver.get(url);
        driver.manage().window().maximize();

        // Set up an explicit wait with a timeout of 10 seconds
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        try {
        	// Wait until the cart button is clickable, then scroll to it and click
            WebElement cartButton = wait.until(ExpectedConditions.elementToBeClickable(cartPage.getCartButtonElement()));
            ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", cartButton);
            cartPage.clickCartButton();
            logger.info("Clicked on Cart Button");

            // Wait until the place order button is clickable, then click it
            WebElement placeOrderButton = wait.until(ExpectedConditions.elementToBeClickable(cartPage.getPlaceOrderButtonElement()));
            cartPage.clickPlaceOrderButton();
            logger.info("Clicked on Place Order Button");

            // Wait until the email field is visible, then enter the email
            WebElement emailField = wait.until(ExpectedConditions.visibilityOf(cartPage.getEmailFieldElement()));
            cartPage.enterEmail(email);
            logger.info("Entered email");
             
            // Get the title of the current page
            String pageTitle = driver.getTitle();
            System.out.println(pageTitle);

             // Assertion to check if the page title matches the expected title
            String expectedPageTitle = "Flipkart.com: Secure Payment: Login > Select Shipping Address > Review Order > Place Order";
            Assert.assertEquals(pageTitle, expectedPageTitle, "Page title is not as expected");

            // Log test success in Extent Report
            test.log(Status.PASS, "Test Passed");
        } catch (Exception e) {
        	// Handle exceptions and take a screenshot if the test fails
            String screenshotFileName = "cartTestFailure_" + System.currentTimeMillis();
            String screenshotPath = "ExitProject/screenshots/" + screenshotFileName + ".png";
            try {
                getScreenshot(screenshotPath);
                test.fail("Test failed", MediaEntityBuilder.createScreenCaptureFromPath(screenshotPath).build());
                logger.error("Test failed: " + e.getMessage());
            } catch (IOException ioException) {
                ioException.printStackTrace();
            }
        }
    }
        

    // Data provider for Cart test data
    @DataProvider(name = "cartTestData")
    public Object[][] getCartTestData() throws IOException, InvalidFormatException {
    	 // Provide the path to your Excel file
        String excelFilePath = "ExcelFile/FlipkartReadDataExcel.xlsx";
        // Read test data from Excel
        return ExcelUtils.getTestData(excelFilePath, "cartSheet");
    }

    @AfterMethod
    public void afterMethod(ITestResult result) {
    	 // Log test status to Extent Report based on the result status
        if (result.getStatus() == ITestResult.FAILURE) {
            test.log(Status.FAIL, "Test Failed: " + result.getThrowable());
        } else if (result.getStatus() == ITestResult.SKIP) {
            test.log(Status.SKIP, "Test Skipped: " + result.getThrowable());
        } else {
            test.log(Status.PASS, "Test Passed");
        }
    }
}
