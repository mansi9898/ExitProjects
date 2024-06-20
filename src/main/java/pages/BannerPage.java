package pages;

import java.time.Duration;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class BannerPage {
	private WebDriver driver;
	private WebDriverWait wait;

    // Constructor to initialize the WebDriver
    public BannerPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(30));
    }
    // Method to click the banner image
     public void clickBannerImage() {
    	// Wait until the banner image is clickable and then locate it
        WebElement bannerImage = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//div[@class='zlQd20 _1eDlvI']")));
        // Scroll the banner image into view
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", bannerImage);
        // Click the banner image using JavaScript executor
        ((JavascriptExecutor) driver).executeScript("arguments[0].click();", bannerImage);
    }
   
}   