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

public class DownloadPage {
    private WebDriver driver;
    private WebDriverWait wait;

    // Constructor to initialize the WebDriver and WebDriverWait
    public DownloadPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(20)); // 20 seconds wait time
        PageFactory.initElements(driver, this);
    }

    public void clickSellerLink() {
        WebElement sellerLink = wait.until(ExpectedConditions.elementToBeClickable(By.partialLinkText("Become a Seller")));
        sellerLink.click();
    }

    public void clickShopsyLink() {
        WebElement shopsyButton = wait.until(ExpectedConditions.elementToBeClickable(By.partialLinkText("Shopsy")));
        shopsyButton.click();
    }

    public void downloadKit() {
    	// Wait for the "Download Launch Kit" button to be clickable
        WebElement downloadButton = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[contains(@class,'styles__ButtonStyle') and text()='Download Launch Kit']")));
        
        // Scroll to the "Download Launch Kit" button
        Actions actions = new Actions(driver);
        actions.moveToElement(downloadButton).perform();
        
        // Click on the "Download Launch Kit" button
        WebElement downloadButton1 = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[contains(@class,'styles__ButtonStyle') and text()='Download Launch Kit']")));
        downloadButton1.click();
    }

    public void goToTop() {
    	JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("window.scrollTo(0, 0)");
    }
}
