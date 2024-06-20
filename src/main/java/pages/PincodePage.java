package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class PincodePage {
	
	// WebDriver instance variable
	private WebDriver driver;

	// Constructor to initialize the WebDriver and set up the page elements
    public PincodePage(WebDriver driver) {
        this.driver = driver;
        // Initialize WebElements using PageFactory
        PageFactory.initElements(driver, this);
    }
    
    // Locate the pincode input element using its id
	@FindBy(id = "pincodeInputId")
    private WebElement pinCodeBar;
	
	// Method to enter the pincode and submit it
	public void enterPincode(String pinCode) 
	{
		// Send the pincode to the input element
        pinCodeBar.sendKeys(pinCode);
        // Press Enter to perform the search
        pinCodeBar.sendKeys(Keys.ENTER);
    }
}
