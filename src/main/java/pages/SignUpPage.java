package pages;

import java.time.Duration;


import org.openqa.selenium.By;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.JavascriptExecutor;

public class SignUpPage {
	private WebDriver driver;

    // Constructor to initialize the WebDriver
    public SignUpPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    @FindBy(linkText = "Login")
    private WebElement loginButton;
    
    @FindBy(linkText = "New to Flipkart? Create an account")
    private WebElement signUpLink;

    
    public void clickLoginButton() {
    	WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));// Set the timeout to 10 seconds
        WebElement loginButton = wait.until(ExpectedConditions.elementToBeClickable(By.linkText("Login")));
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", loginButton);
        ((JavascriptExecutor) driver).executeScript("arguments[0].click();", loginButton);
    }

    public void clickSignUpLink() {
        signUpLink.click();
    }
    
    // Method to enter phone number
    public void enterPhoneNumber(String phoneNumber) {
        WebElement phoneNumberField = driver.findElement(By.className("r4vIwl"));
        phoneNumberField.sendKeys(phoneNumber);
    }
    
    // Method to click on the CONTINUE button
    public void clickContinueButton() {
        WebElement continueButton = driver.findElement(By.cssSelector("button.QqFHMw"));
        continueButton.click();
    }
}
