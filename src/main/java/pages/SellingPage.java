package pages;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class SellingPage {
    private WebDriver driver;
    private WebDriverWait wait;

    // Constructor to initialize the WebDriver
    public SellingPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(30)); // Set the timeout to 30 seconds
        PageFactory.initElements(driver, this);
    }
    
    // Method to click sellerlink
    public void clickSellerLink() {
        WebElement sellerLink = wait.until(ExpectedConditions.elementToBeClickable(By.partialLinkText("Become a Seller")));

        // Scroll the element into view if it's not clickable
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", sellerLink);

        // Additional wait to ensure the element is fully in view
        wait.until(ExpectedConditions.visibilityOf(sellerLink));

        try {
            sellerLink.click();
        } catch (Exception e) {
            // If click fails, try again after a brief wait
            wait.until(ExpectedConditions.elementToBeClickable(sellerLink));
            sellerLink.click();
        }
    }
    
    // Method to click startselling link
    public void clickStartSellingLink() {
        WebElement sellingButton = driver.findElement(By.xpath("//button[@data-testid='button']"));
        sellingButton.click();
    }
    
    // Method to enter the pincode
    public void enterPincode(String pin) {
        WebElement pincode = driver.findElement(By.name("pincode"));
        pincode.sendKeys(pin);
        pincode.sendKeys(Keys.ENTER);
    }
}
