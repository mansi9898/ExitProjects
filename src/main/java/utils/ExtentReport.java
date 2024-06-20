package utils;


import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

public class ExtentReport {
    private static ExtentReports extent;
    
    // Method to get the singleton instance of ExtentReports
    public static ExtentReports getInstance() {
    	// Check if the extent instance is null
        if (extent == null) {
        	// Create a new ExtentReports instance
            extent = new ExtentReports();
            // Create ExtentSparkReporter instance with report file path
            ExtentSparkReporter sparkReporter = new ExtentSparkReporter("Reports/FlipkartExtentReport.html");
            // Configure the report theme to standard
            sparkReporter.config().setTheme(Theme.STANDARD);
            // Configure the document title for the report
            sparkReporter.config().setDocumentTitle("FlipkartExitProject Automation Test Report");
            // Configure the report name
            sparkReporter.config().setReportName("FlipkartAutomation Report");

            // Attach ExtentSparkReporter to ExtentReports instance
            extent.attachReporter(sparkReporter);
        }
       // Return the ExtentReports instance
        return extent;
    }

   // Method to flush and write all test information to the report
    public static void flush() {
    	// Check if extent instance is not null
        if (extent != null) {
        	// Flush the report - write all test information to the report
            extent.flush();
        }
    }
}
