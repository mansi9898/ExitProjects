package pages;

import java.time.Duration;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class LoginPage {
	private WebDriver driver;

    // Constructor to initialize the WebDriver
    public LoginPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    @FindBy(linkText = "Login")
    private WebElement loginButton;
    
    @FindBy(linkText = "New to Flipkart? Create an account")
    private WebElement signUpLink;

    // Method to click on the login button
    public void clickLoginButton() {
    	WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));// Set the timeout to 10 seconds
    	wait.until(ExpectedConditions.elementToBeClickable(loginButton));
        loginButton.click();
    }
       
 // Method to click on the SignUp link with JavaScript fallback
    public void clickSignUpLink() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15)); // Set the timeout to 10 seconds
        wait.until(ExpectedConditions.elementToBeClickable(signUpLink));
        
        try {
            signUpLink.click(); // Try clicking the element
        } catch (org.openqa.selenium.ElementClickInterceptedException e) {
            // If clicking the element directly fails, try clicking using JavaScript
            ((JavascriptExecutor) driver).executeScript("arguments[0].click();", signUpLink);
        }
    }
    
    
    // Method to enter phone number
    public void enterPhoneNumber(String phoneNumber) {
        WebElement phoneNumberField = driver.findElement(By.className("r4vIwl BV+Dqf"));
        phoneNumberField.sendKeys(phoneNumber);
    }
    
    // Method to click on the CONTINUE button
    public void clickContinueButton() {
        WebElement continueButton = driver.findElement(By.cssSelector("button.QqFHMw twnTnD _7Pd1Fp"));
        continueButton.click();
    }
}
