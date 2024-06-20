package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.JavascriptExecutor;
import java.time.Duration;

public class CustomerCarePage {
    private WebDriver driver;
    private WebDriverWait wait;

    // Constructor to initialize the WebDriver and WebDriverWait
    public CustomerCarePage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(30));
    }

    public void goToCustomerCareInfo() {
        WebElement dropdownButton = driver.findElement(By.xpath("//img[@alt='Dropdown with more help links']"));
        
        // Wait for the dropdown button to be clickable
        wait.until(ExpectedConditions.elementToBeClickable(dropdownButton));
        
        // Scroll to the dropdown button
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", dropdownButton);

        // Click on the dropdown button
        dropdownButton.click();
        
        // After the dropdown is expanded, wait for the customer care link and click on it
        WebElement customerCareLink = wait.until(ExpectedConditions.elementToBeClickable(By.partialLinkText("24x7 Customer Care")));
        customerCareLink.click();
    }
}











