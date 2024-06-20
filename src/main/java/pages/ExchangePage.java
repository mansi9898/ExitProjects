package pages;

import java.time.Duration;
import org.openqa.selenium.By;
import org.openqa.selenium.ElementClickInterceptedException;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class ExchangePage {
    private WebDriver driver;
    private WebDriverWait wait;

    // Constructor to initialize the WebDriver
    public ExchangePage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10)); // Adjust the timeout as needed
        PageFactory.initElements(driver, this);
    }
    
    @FindBy(id = "pincodeInputId")
    private WebElement pinCodeBar;
    
    public void enterPincode(String pinCode) {
        pinCodeBar.sendKeys(pinCode);
        // Press Enter to perform the search
        pinCodeBar.sendKeys(Keys.ENTER);
    }
    
    public void scrollAndClickExchangeButton() {
    	WebElement exchangeButton = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[text()='Buy with Exchange']")));

        // Scroll to the element
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", exchangeButton);
        
        // Click the element
        try {
            exchangeButton.click();
        } catch (ElementClickInterceptedException e) {
            // If element click is intercepted, handle the exception
            System.out.println("Element click intercepted, trying scrolling again or other actions...");
        }
   
    }
}


