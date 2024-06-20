package tests;

import java.io.IOException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException; // Add this import
import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.MediaEntityBuilder;
import com.aventstack.extentreports.Status;
import pages.BannerPage;
import resources.BaseClass;
import utils.ExcelUtils;
import utils.ExtentReport;

public class BannerTest extends BaseClass {
    private static final Logger logger = LogManager.getLogger(BannerTest.class);// Logger instance for logging test information
    private ExtentTest test;// ExtentTest instance for logging test steps and results to the report

    @Test(dataProvider = "bannerTestData", groups = {"smoke"}, priority = 1, enabled = true)
    public void bannerTest(String url, String executionRequired) {
        // Check if test execution is required based on the Excel data
        if (!executionRequired.equalsIgnoreCase("Yes")) {
            logger.info("Test execution not required. Skipping the test.");
            return;// Skip the test if execution is not required
        }

        // Create a new test in Extent Report
        test = ExtentReport.getInstance().createTest("BannerTest");

        // Initialize BannerPage object
        BannerPage bannerPage = new BannerPage(driver);
        // Navigate to the provided URL and maximize the browser window
        driver.get(url);
        driver.manage().window().maximize();

        // Click on the banner image
        bannerPage.clickBannerImage();
        logger.info("Clicked on HomePage Banner");

        // Wait for the page to load and get the title of the page
        try {
            Thread.sleep(4000); 
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        String pageTitle = driver.getTitle();

        // Assert that the title matches the expected title
        String expectedTitle = "Moto Edge 40 Neo - Buy Moto Edge 40 Neo Online at Low Prices In India | Flipkart.com";
        if (pageTitle.equals(expectedTitle)) {
            test.pass("Test Passed");// Log pass status if the title matches
        }
    }


    // Data provider for banner test data
    @DataProvider(name = "bannerTestData")
    public Object[][] getBannerTestData() throws IOException, InvalidFormatException {
        // Provide the path to your Excel file
        String excelFilePath = "ExcelFile/FlipkartReadDataExcel.xlsx";

        // Read test data from Excel
        return ExcelUtils.getTestData(excelFilePath, "bannerSheet");
    }

    @AfterMethod
    public void afterMethod(ITestResult result) {
        // Log test status to Extent Report
        if (result.getStatus() == ITestResult.FAILURE) {
            test.log(Status.FAIL, "Test Failed: " + result.getThrowable());
        } else if (result.getStatus() == ITestResult.SKIP) {
            test.log(Status.SKIP, "Test Skipped: " + result.getThrowable());
        }
    }
}
