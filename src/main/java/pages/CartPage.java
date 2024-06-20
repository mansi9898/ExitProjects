package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class CartPage {
	private WebDriver driver;

	// Constructor to initialize the WebDriver and PageFactory elements
    public CartPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);// Initialize web elements with PageFactory
    }
    
    // Method to get the Cart button element
	 public WebElement getCartButtonElement() {
        return driver.findElement(By.xpath("//button[@class='QqFHMw vslbG+ In9uk2']"));
    }
	 
	// Method to get the Place Order button element
    public WebElement getPlaceOrderButtonElement() {
        return driver.findElement(By.xpath("//button[@class='QqFHMw zA2EfJ _7Pd1Fp']"));
    }
    
    // Method to get the Email field element
    public WebElement getEmailFieldElement() {
        return driver.findElement(By.xpath("//input[@class='r4vIwl Jr-g+f']"));  // Update this locator based on your actual page
    }

    // Method to click the Cart button
    public void clickCartButton() {
        getCartButtonElement().click();
    }
    
    // Method to click the Place Order button
    public void clickPlaceOrderButton() {
    	getPlaceOrderButtonElement().click();
    }
    
    // Method to enter email and submit it
    public void enterEmail(String email) {
    	WebElement emailBox = driver.findElement(By.xpath("//input[@class='r4vIwl Jr-g+f']"));
    	emailBox.sendKeys(email);// Enter the email
    	emailBox.sendKeys(Keys.ENTER);// Press ENTER to submit
    }
}
