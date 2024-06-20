package pages;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.ElementClickInterceptedException;

public class GroupCompanyPage {
    private WebDriver driver;

    // Constructor to initialize the WebDriver
    public GroupCompanyPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public void goToGroupCompany() {
        // Scroll to the bottom of the page
        ((JavascriptExecutor) driver).executeScript("window.scrollTo(0, document.body.scrollHeight)");

        // Wait for the element to be clickable
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));
        WebElement myntraLink = wait.until(ExpectedConditions.elementToBeClickable(By.partialLinkText("Myntra")));

        // Scroll the element into view
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", myntraLink);

        try {
            // Try clicking the element
            myntraLink.click();
        } catch (ElementClickInterceptedException e) {
            // If click is intercepted, use JavaScript to click the element
            ((JavascriptExecutor) driver).executeScript("arguments[0].click();", myntraLink);
        }
    }
}
