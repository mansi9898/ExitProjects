package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;

public class SearchPage {
	private WebDriver driver;

    // Constructor to initialize the WebDriver
    public SearchPage(WebDriver driver) {
        this.driver = driver;
        // Initialize elements using PageFactory
        PageFactory.initElements(driver, this);
    }
    
    // Method to search for a product
    public void searchProduct(String productName) {
    	// Locate the search input box using class name
    	WebElement searchInput = driver.findElement(By.className("Pke_EE"));
        // Enter the product name into the search input box
        searchInput.sendKeys(productName);
        // Press Enter to perform the search
        searchInput.sendKeys(Keys.ENTER);
    }
}

