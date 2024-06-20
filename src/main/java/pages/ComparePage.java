package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;

public class ComparePage {
	private WebDriver driver;

	// Constructor to initialize the WebDriver and PageFactory elements
    public ComparePage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);// Initialize web elements with PageFactory
    }
    
   // Method to click the compare checkbox
    public void clickCompareButton() {
    	WebElement checkBox = driver.findElement(By.className("XqNaEv")); // Locate the checkbox using its class name
    	checkBox.click();// Click the checkbox
    }
    
    // Method to navigate to the compare page
    public void goToComparePage() {
    	WebElement compareButton = driver.findElement(By.partialLinkText("COMPARE"));// Locate the compare button using partial link text
    	compareButton.click();// Click the compare button
    }
}
