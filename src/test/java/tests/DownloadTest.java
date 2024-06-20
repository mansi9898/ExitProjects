package tests;

import java.awt.AWTException;
import java.awt.Robot;
import java.awt.event.KeyEvent;
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

import pages.DownloadPage;
import resources.BaseClass;
import utils.ExcelUtils;
import utils.ExtentReport;

public class DownloadTest extends BaseClass {
	// Logger instance for logging test information
    private static final Logger logger = LogManager.getLogger(DownloadTest.class);
    private ExtentTest test;// ExtentTest instance for logging test steps and results to the report

    @Test(dataProvider = "downloadTestData", groups = {"smoke"}, priority = 1, enabled = true)
    public void downloadTest(String url, String executionRequired) throws IOException {
        // Check if test execution is required based on the Excel data
        if (!executionRequired.equalsIgnoreCase("Yes")) {
            logger.info("Test execution not required. Skipping the test.");
            return;// Skip the test if execution is not required
        }
        
        // Create a new test in Extent Report
        test = ExtentReport.getInstance().createTest("DownloadTest");
        // Initialize DownloadPage object
        DownloadPage downloadPage = new DownloadPage(driver);
        // Initialize PopupHandler object
        PopupHandler popupHandler = new PopupHandler();
    	// Navigate to the provided URL and maximize the browser window
        driver.get(url);
        driver.manage().window().maximize();

        try {
        	// Perform actions on the download page
            downloadPage.clickSellerLink();
            logger.info("Navigated to Seller Page");
            downloadPage.clickShopsyLink();
            logger.info("Navigated to Shopsy Store");
            downloadPage.downloadKit();
            logger.info("Downloaded Kit");
            // Handle any popup that appears
            popupHandler.cancelPopup();
            logger.info("Handled Pop Up");
            // Navigate to the top of the page
            downloadPage.goToTop();
            logger.info("Navigated to the Top");
            Thread.sleep(3000); // Sleep for 3 seconds (adjust the duration as needed)
            // Get the title of the page
            String pageTitle = driver.getTitle();
            // Assert that the page title matches the expected title
            Assert.assertEquals(pageTitle, "Start Your Business on Shopsy With 0% Commission | Flipkart Seller Hub", "Page title is not as expected");

            // Log test success in Extent Report
            test.pass("Test Passed");
        } catch (AssertionError e) {
        	// Handle assertion errors, take a screenshot, and log the failure
            String screenshotFileName = "downloadSS";
            String screenshotPath = "/ExitProject/screenshots/" + screenshotFileName + ".png";
            try {
                getScreenshot(screenshotPath);
                test.fail("Test failed", MediaEntityBuilder.createScreenCaptureFromPath(screenshotPath).build());
                logger.error("Test failed");
            } catch (IOException ioException) {
                ioException.printStackTrace();
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    // Data provider for Download test data
    @DataProvider(name = "downloadTestData")
    public Object[][] getDownloadTestData() throws IOException, InvalidFormatException {
        // Provide the path to your Excel file
        String excelFilePath = "ExcelFile/FlipkartReadDataExcel.xlsx";

        // Read test data from Excel
        return ExcelUtils.getTestData(excelFilePath, "downloadSheet");
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

    public class PopupHandler {

        public void cancelPopup() {
            try {
            	// Create a Robot instance to simulate keyboard events
                Robot robot = new Robot();
                // Simulate pressing and releasing the Escape key to cancel the popup
                robot.keyPress(KeyEvent.VK_ESCAPE);
                robot.keyRelease(KeyEvent.VK_ESCAPE);
            } catch (AWTException e) {
                e.printStackTrace();
            }
        }
    }
}
