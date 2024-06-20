package tests;

import java.io.IOException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.MediaEntityBuilder;
import com.aventstack.extentreports.Status;
import utils.ExcelUtils;
import utils.ExtentReport;
import pages.SellingPage;
import resources.BaseClass;

public class SellerTest extends BaseClass {
    private static final Logger logger = LogManager.getLogger(SellerTest.class);// Logger instance for logging test information
    private ExtentTest test;// ExtentTest instance for logging test steps and results to the report

    @Test(dataProvider = "sellerTestData", groups = {"sanity"}, priority = 1, enabled = true)
    public void sellerTest(String url, String executionRequired) throws IOException {
        // Check if test execution is required based on the Excel data
        if (!executionRequired.equalsIgnoreCase("Yes")) {
            logger.info("Test execution not required. Skipping the test.");
            return;// Skip the test if execution is not required
        }

        test = ExtentReport.getInstance().createTest("SellerTest"); // Create ExtentTest instance

        SellingPage sellingPage = new SellingPage(driver);
        // Navigate to the provided URL and maximize the browser window
        driver.get(url);
        driver.manage().window().maximize();

        try {
            sellingPage.clickSellerLink();
            logger.info("Clicked Seller Link");
            sellingPage.clickStartSellingLink();
            logger.info("Navigated to Selling Page");
            // Get the title of the page
            String pageTitle = driver.getTitle();
            System.out.println(pageTitle);
            // Assert that the page title matches the expected title
            Assert.assertEquals(pageTitle, "Sell Online - Start Selling Online on Flipkart Seller Hub", "Page title is not as expected");

            // Log test success in Extent Report
            test.log(Status.PASS, "Test Passed");
        } catch (AssertionError e) {
        	// Handle assertion error and capture screenshot
            String screenshotFileName = "sellerSS";
            String screenshotPath = "/ExitProject/screenshots" + screenshotFileName + ".png";
            try {
                getScreenshot(screenshotPath);
                test.fail("Test failed", MediaEntityBuilder.createScreenCaptureFromPath(screenshotPath).build());
                logger.error("Test failed");
            } catch (IOException ioException) {
                ioException.printStackTrace();
            }
        }
    }

    // Data provider for seller test data
    @DataProvider(name = "sellerTestData")
    public Object[][] getSellerTestData() throws IOException, InvalidFormatException {
        // Provide the path to your Excel file
        String excelFilePath = "ExcelFile/FlipkartReadDataExcel.xlsx";

        // Read test data from Excel
        return ExcelUtils.getTestData(excelFilePath, "sellerSheet");
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
