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
import pages.BannerPage;
import pages.WishlistPage;
import resources.BaseClass;

public class WishlistTest extends BaseClass {
    private static final Logger logger = LogManager.getLogger(WishlistTest.class);// Logger instance for logging test information
    private ExtentTest test;// ExtentTest instance for logging test steps and results to the report

    @Test(dataProvider = "bannerTestData", priority = 1, groups = {"banner"}, enabled = true)
    public void bannerTest(String url, String executionRequired) throws IOException {
        // Check if test execution is required based on the Excel data
        if (!executionRequired.equalsIgnoreCase("Yes")) {
            logger.info("Test execution not required. Skipping the test.");
            return;// Skip the test if execution is not required
        }

        test = ExtentReport.getInstance().createTest("BannerTest");// Create a new test in Extent Report
        BannerPage bannerPage = new BannerPage(driver);
         // Navigate to the provided URL and maximize the browser window
        driver.get(url);
        driver.manage().window().maximize();

        try {
            bannerPage.clickBannerImage();
            logger.info("Clicked on HomePage Banner");
            Thread.sleep(4000); 
            String pageTitle = driver.getTitle();
            String expectedTitle = "Moto Edge 40 Neo - Buy Moto Edge 40 Neo Online at Low Prices In India | Flipkart.com";
            //Assert.assertEquals(pageTitle, expectedTitle, "Page title is not as expected");

        } catch (AssertionError e) {
        	// Handle assertion error and capture screenshot
            String screenshotFileName = "bannerSS";
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

    // Priority is 2 and it  depends on BannerTest Data
    @Test(dataProvider = "wishlistTestData", priority = 2, groups = {"wishlist"}, enabled = true , dependsOnMethods = {"bannerTest"})
    public void wishlistTest(String url, String executionRequired) throws IOException {
        // Check if test execution is required based on the Excel data
        if (!executionRequired.equalsIgnoreCase("Yes")) {
            logger.info("Test execution not required. Skipping the test.");
            return;
        }

        test = ExtentReport.getInstance().createTest("WishlistTest");// Create a new test in Extent Report
        WishlistPage wishlistPage = new WishlistPage(driver);//Initialize the wishlist pageobject
        // Navigate to the provided URL and maximize the browser window
        driver.get(url);
        driver.manage().window().maximize();

        try {
            wishlistPage.clickHeartButton();
            logger.info("Clicked heart button");

            Thread.sleep(2000);
             // Get the title of the page
            String pageTitle = driver.getTitle();
            // Assert that the page title matches the expected title
            Assert.assertEquals(pageTitle, "POCO M6 Pro 5G - Buy POCO M6 Pro 5G Online at Low Prices In India | Flipkart.com", "Page title is not as expected");

        } catch (AssertionError e) {
        	// Handle assertion error and capture screenshot
            String screenshotFileName = "wishlistSS";
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

    // Data provider for banner test data
    @DataProvider(name = "bannerTestData")
    public Object[][] getBannerTestData() throws IOException, InvalidFormatException {
        String excelFilePath = "ExcelFile/FlipkartReadDataExcel.xlsx";
        return ExcelUtils.getTestData(excelFilePath, "bannerSheet");
    }

    // Data provider for wishlist test data
    @DataProvider(name = "wishlistTestData")
    public Object[][] getWishlistTestData() throws IOException, InvalidFormatException {
        String excelFilePath = "ExcelFile/FlipkartReadDataExcel.xlsx";
        return ExcelUtils.getTestData(excelFilePath, "wishlistSheet");
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
        //ExtentReport.getInstance().flush();
    }
}
