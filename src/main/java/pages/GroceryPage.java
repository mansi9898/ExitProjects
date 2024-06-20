package pages;

import java.time.Duration;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class GroceryPage {
	// WebDriver instance variable
	private WebDriver driver;
	
	// Constructor to initialize the WebDriver and set up the page elements
    public GroceryPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }
   
    // Method to click on the grocery link
    public void clickGroceryLink() {
    	// Create a WebDriverWait instance with a 10-second timeout
    	WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));// Set the timeout to 10 seconds
    	// Wait until the grocery link is clickable
        WebElement groceryLink = wait.until(ExpectedConditions.elementToBeClickable(By.partialLinkText("Grocery")));
        try {
        	// Attempt to click the grocery link
            groceryLink.click();
        } catch (org.openqa.selenium.ElementClickInterceptedException e) {
            // Use JavaScript to click the element if it's intercepted
            JavascriptExecutor js = (JavascriptExecutor) driver;
            js.executeScript("arguments[0].click();", groceryLink);
        }
    }

    // Method to enter the pincode
    public void enterPincode(String pin) {
    	// Find the pincode input element by its name attribute
    	WebElement pincode = driver.findElement(By.name("pincode"));
    	// Send the pincode to the input element
    	pincode.sendKeys(pin);
    	// Press the ENTER key to submit the pincode
    	pincode.sendKeys(Keys.ENTER);
    }
}
