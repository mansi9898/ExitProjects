package pages;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;

public class GiftcardPage {
    private WebDriver driver;
    private WebDriverWait wait;

    // Constructor to initialize the WebDriver and WebDriverWait
    public GiftcardPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(20));  // Set a timeout of 20 seconds
        PageFactory.initElements(driver, this);// Initialize web elements with PageFactory
    }
    

    // Method to get the gift card link element
    public WebElement getGiftcardLinkElement() {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//img[@alt='dm']")));
    }
    
    // Method to click the gift card link
    public void clickGiftcardLink() {
        getGiftcardLinkElement().click();
    }
    
    // Method to enter the recipient's email
    public void enterEmail(String emailID) {
        WebElement email = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//input[@name='recipient-email[]']")));
        email.sendKeys(emailID);
    }
    
    // Method to enter the recipient's name
    public void enterName(String name) {
        WebElement nameBox = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//input[@name='recipient-name[]']")));
        nameBox.sendKeys(name);
    }
    
    // Method to select a voucher value from the dropdown
    public void selectVoucher() {
    	// Wait until the voucher dropdown is clickable and then locate it
        WebElement dropDown = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//select[@name='voucher-value[]']")));
        dropDown.click();// Click to open the dropdown
        Select select = new Select(dropDown);// Create a Select object for the dropdown
        List<WebElement> options = select.getOptions();// Get all options in the dropdown
        
       // Iterate through the options to find and select the option with text "100"
        for (WebElement option : options) {
            if (option.getText().trim().equals("100")) {
                option.click(); // Click the option with text "100"
                break;
            }
        }
    }
    
    // Method to get the buy button element
    public WebElement getBuyButtonLinkElement() {
    	// Wait until the buy button element is visible and then locate it
        return wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//button[contains(@class, 'QqFHMw') and contains(@class, 'AsTLnx') and contains(@class, '_7Pd1Fp') and contains(text(), 'BUY GIFT CARD FOR')]")));
    }

    // Method to click the buy button
    public void clickBuyButton() {
    	// Wait until the buy button is clickable and then locate it
        WebElement buyButton = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[contains(@class, 'QqFHMw') and contains(@class, 'AsTLnx') and contains(@class, '_7Pd1Fp') and contains(text(), 'BUY GIFT CARD FOR')]")));
        buyButton.click();// Click the buy button
    }
}

