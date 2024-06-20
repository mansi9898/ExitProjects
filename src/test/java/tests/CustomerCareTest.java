package tests;

import java.io.IOException;
import java.time.Duration;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
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
import pages.CustomerCarePage;
import resources.BaseClass;
import utils.ExtentReport;

public class CustomerCareTest extends BaseClass {
    private static final Logger logger = LogManager.getLogger(CustomerCareTest.class);// Logger instance for logging test information
    private ExtentTest test;// ExtentTest instance for logging test steps and results to the report

    @Test(dataProvider = "customerTestData", groups = {"sanity"}, priority = 1, enabled = true)
    public void customerCareTest(String url, String executionRequired) throws IOException {
        // Check if test execution is required based on the Excel data
        if (!executionRequired.equalsIgnoreCase("Yes")) {
            logger.info("Test execution not required. Skipping the test.");
            return;// Skip the test if execution is not required
        }

        // Create a new test in Extent Report
        test = ExtentReport.getInstance().createTest("CustomerCareTest"); 

        // Initialize CustomerCarePage object
        CustomerCarePage ccpage = new CustomerCarePage(driver);
        
        // Navigate to the provided URL and maximize the browser window
        driver.get(url);
        driver.manage().window().maximize();

        try {
            // Refresh the page
            driver.navigate().refresh();
            // Navigate to Customer Care information page
            ccpage.goToCustomerCareInfo();
            logger.info("Navigated to Customer Care Page");
            // Get the title of the Customer Care page
            String pageTitle = driver.getTitle();
            // Assert that the page title matches the expected title
            Assert.assertEquals(pageTitle, "Online Shopping India | Buy Mobiles, Electronics, Appliances, Clothing and More Online at Flipkart.com", "Page title is not as expected");

            // Log test success in Extent Report
            test.log(Status.PASS, "Test Passed");
        } catch (AssertionError e) {
        	 // Handle assertion errors, take a screenshot, and log the failure
            String screenshotFileName = "customerSS";
            String screenshotPath = "/ExitProject/screenshots/" + screenshotFileName + ".png";
            try {
                getScreenshot(screenshotPath);
                test.fail("Test failed", MediaEntityBuilder.createScreenCaptureFromPath(screenshotPath).build());
                logger.error("Test failed");
            } catch (IOException ioException) {
                ioException.printStackTrace();
            }
        }
    }

    // Data provider for Customer test data
    @DataProvider(name = "customerTestData")
    public Object[][] getLoginTestData() throws IOException, InvalidFormatException {
        // Provide the path to your Excel file
        String excelFilePath = "ExcelFile/FlipkartReadDataExcel.xlsx";

        // Read test data from Excel
        return ExcelUtils.getTestData(excelFilePath, "customerSheet");
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
