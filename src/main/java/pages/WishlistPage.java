package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class WishlistPage {
	private WebDriver driver;

    // Constructor to initialize the WebDriver
    public WishlistPage(WebDriver driver) {
        this.driver = driver;
     // Initialize elements using PageFactory
        PageFactory.initElements(driver, this);
    }
    
    // Method to click heartbutton to wishlist the product
	public void clickHeartButton() {
		WebElement heart = driver.findElement(By.xpath("//div[@class='oUss6M ssUU08']"));
		heart.click();
	}
}
