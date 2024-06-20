package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;

public class RateProductPage {
	// WebDriver instance variable
	private WebDriver driver;

	// Constructor to initialize the WebDriver and set up the page elements
    public RateProductPage(WebDriver driver) {
        this.driver = driver;
        // Initialize WebElements using PageFactory
        PageFactory.initElements(driver, this);
    }
    
   // Method to click on the product rating element
    public void clickRating() {
        WebElement rating = driver.findElement(By.xpath("//div[@class='XQDdHH']"));
        rating.click();
    }
    
    // Method to click the rating button to submit the rating
    public void clickRatingButton() {
    	 WebElement ratingButton = driver.findElement(By.xpath("//button[@class='QqFHMw YEbu1k']"));
         ratingButton.click();// Click the rating button
    }
}
