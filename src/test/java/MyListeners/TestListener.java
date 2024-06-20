package MyListeners;

import java.io.IOException;
import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;
import resources.BaseClass;

public class TestListener implements ITestListener {
    private static WebDriver driver; // Static WebDriver instance for capturing screenshots

    // Method to set the WebDriver instance
    public static void setDriver(WebDriver driver) {
        TestListener.driver = driver;
    }

    @Override
    public void onStart(ITestContext context) {
    }

    @Override
    public void onFinish(ITestContext context) {
    }

    @Override
    public void onTestStart(ITestResult result) {
    }

    @Override
    public void onTestSuccess(ITestResult result) {
    }

    @Override
    public void onTestFailure(ITestResult result) {
    	// TestNG method: This method runs when a test case fails
        try {
            System.out.println("Failed Test");
            String screenshotPath = "screenshots/" + result.getName() + ".png"; // Define screenshot path using test case name
            // Capture screenshot using BaseClass static method
            BaseClass.getScreenshot(screenshotPath);
        } catch (IOException e) {
        	// Print stack trace in case of IO Exception
            e.printStackTrace();
        }
    }

    @Override
    public void onTestSkipped(ITestResult result) {
    }

    @Override
    public void onTestFailedButWithinSuccessPercentage(ITestResult result) {
    }
}
