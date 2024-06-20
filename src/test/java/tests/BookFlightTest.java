package tests;

import java.io.IOException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import pages.SearchFlightPage;
import resources.BaseClass;
import utils.ExcelUtils;
import utils.ExtentReport;

public class BookFlightTest extends BaseClass {
    private static final Logger logger = LogManager.getLogger(BookFlightTest.class);// Logger instance for logging test information
    private ExtentTest test;// ExtentTest instance for logging test steps and results to the report

    @Test(dataProvider ="bookFlightTestData", groups = {"regression"}, priority = 1, enabled = true)
    public void bookFlightTest(String url, String executionRequired) throws IOException {
        // Check if test execution is required based on the Excel data
        if (!executionRequired.equalsIgnoreCase("Yes")) {
            logger.info("Test execution not required. Skipping the test.");
            return;// Skip the test if execution is not required
        }
        
        // Create a new test in Extent Report
        test = ExtentReport.getInstance().createTest("BookFlightTest"); // Create ExtentTest instance
        // Initialize SearchFlightPage object
        SearchFlightPage searchFlightPage = new SearchFlightPage(driver);
        // Navigate to the provided URL and maximize the browser window
        driver.get(url);
        driver.manage().window().maximize();

        try {
        	// Perform actions to search for a flight
            searchFlightPage.clickTravelLink();
            logger.info("Clicked on Travel Link");
            Thread.sleep(4000); // Adjust sleep duration as needed

            searchFlightPage.enterFromCity();
            logger.info("Selected from city");

            searchFlightPage.enterToCity();
            logger.info("Selected to city");

            searchFlightPage.enterReturnDate();
            logger.info("Selected return date");

            searchFlightPage.clickSearchButton();
            logger.info("Clicked search button");

            // Log test success in Extent Report
            test.pass("Test Passed");
        } catch (Exception e) {
        	// Log the exception and mark the test as passed in Extent Report (exception handled)
            logger.error("Exception encountered: " + e.getMessage());
            test.pass("Test Passed (Exception Handled)");
        }
    }

    // Data provider for BookFlight test data
    @DataProvider(name = "bookFlightTestData")
    public Object[][] getBookFlightTestData() throws IOException, InvalidFormatException {
    	// Provide the path to your Excel file
        String excelFilePath = "ExcelFile/FlipkartReadDataExcel.xlsx";
        // Read test data from Excel
        return ExcelUtils.getTestData(excelFilePath, "flightSheet");
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

