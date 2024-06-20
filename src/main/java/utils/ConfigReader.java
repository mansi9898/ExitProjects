package utils;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class ConfigReader {
	// Properties object to hold configuration properties
	private static Properties properties;

	// Static block to initialize properties from config file
    static {
        try {
            properties = new Properties();
            // Load the properties file from the specified path
            FileInputStream fis = new FileInputStream("Utilities/config.properties");
            properties.load(fis);
            fis.close();
        } catch (IOException e) {
        	// Print stack trace if file loading fails
            e.printStackTrace();
        }
    }
    
    // Method to retrieve the browser name from properties
    public static String getBrowser() {
        return properties.getProperty("browser");
    }
    
    // Method to check if headless mode is enabled from properties
    public static boolean isHeadless() {
        return Boolean.parseBoolean(properties.getProperty("headless"));
    }
}