package pages;

import java.util.List;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

public class SearchFlightPage {
	private WebDriver driver;

    // Constructor to initialize the WebDriver
    public SearchFlightPage(WebDriver driver) {
        this.driver = driver;
        // Initialize elements using PageFactory
        PageFactory.initElements(driver, this);
    }
    
    // Method to click on the "Travel" link
    public void clickTravelLink() {
    	WebElement travelLink = driver.findElement(By.partialLinkText("Travel"));
    	travelLink.click();
    }
    
    // Method to enter the departure city
    public void enterFromCity() {
    	WebElement cityBox = driver.findElement(By.xpath("//input[@class='v2VFa- rLGgLC g9KxuM smJZop ZjUTQC z2D4XG']"));
    	cityBox.click();
    	 // Locate and select the desired city from the dropdown options
    	List<WebElement> options = driver.findElements(By.xpath("//input[@class='v2VFa- rLGgLC g9KxuM smJZop ZjUTQC z2D4XG']/following-sibling::ul/li"));
        for (WebElement option : options) {
            if (option.getText().trim().equals("Mumbai, BOM - Chatrapati Shivaji Airport, India")) {
                option.click();
                break;
            }
        }
    }
    
    // Method to enter the destination city
    public void enterToCity() {
    	WebElement cityBox = driver.findElement(By.xpath("//input[@class='v2VFa- rLGgLC g9KxuM smJZop G+rzy6 z2D4XG']"));
    	cityBox.click();
    	// Locate and select the desired city from the dropdown options
    	List<WebElement> options = driver.findElements(By.xpath("//input[@class='v2VFa- rLGgLC g9KxuM smJZop G+rzy6 z2D4XG']/following-sibling::ul/li"));
        for (WebElement option : options) {
            if (option.getText().trim().equals("Gorakhpur, GOP - Gorakhpur, India")) {
                option.click();
                break;
            }
        }
    }
    
    // Method to enter the return date
    public void enterReturnDate() {
    	WebElement dateBox = driver.findElement(By.xpath("//input[@class='v2VFa- k2khBy rOnAcM z2D4XG']"));
    	dateBox.click();
    	 // Locate and click on a specific date using the date picker
    	WebElement date = driver.findElement(By.xpath("//button[@class='pl8ttv']"));
    	Actions actions = new Actions(driver);
   	 	actions.moveToElement(date).click().perform();
    }
    
    // Method to click on the search button
    public void clickSearchButton() {
    	// Locate the search button using XPath
    	WebElement searchButton = driver.findElement(By.xpath("//button[@class='QqFHMw sgUmTV M5XAsp']"));
    	 // Click the search button to perform the search action
    	searchButton.click();
    }
    
}

