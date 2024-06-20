package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;

public class FilterPage {
    private WebDriver driver;
    private WebDriverWait wait;

    // Constructor to initialize the WebDriver and WebDriverWait
    public FilterPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);// Initialize web elements with PageFactory
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));// Set WebDriverWait with 10 seconds timeout
    }
    
    // Method to click on the banner image
    public void clickBannerImage() {
    	// Wait until the banner image is clickable and then locate it
        WebElement bannerImage = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//div[@class='zlQd20 _1eDlvI']")));
        // Scroll the banner image into view
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", bannerImage);
        // Click the banner image
        bannerImage.click();
    }
    
    // Method to filter by minimum price
    public void minPriceFilter() {
        try {
        	// Wait until the minimum price dropdown is clickable and then locate it
            WebElement minPrice = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//select[@class='Gn+jFg']")));
            minPrice.click();// Click to open the dropdown
            Select select = new Select(minPrice);// Create a Select object for the dropdown
            select.selectByValue("10000");// Select the value "10000" from the dropdown
        } catch (StaleElementReferenceException e) {
        	// Retry the operation if a StaleElementReferenceException is caught
            minPriceFilter();
        }
    }
    
    // Method to filter by brand
    public void brandFilter() {
        try {
        	// Wait until the brand filter checkbox is clickable and then locate it
            WebElement brand = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//div[@class='XqNaEv']")));
            brand.click();// Click the brand filter checkbox
        } catch (StaleElementReferenceException e) {
        	//Retry the operation if a StaleElementReferenceException is caught
            brandFilter();
        }
    }
}
