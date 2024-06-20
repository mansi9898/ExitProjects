package pages;

import java.time.Duration;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class NavigationPage {
    private WebDriver driver;
    private WebDriverWait wait;

    // Constructor to initialize the WebDriver and WebDriverWait
    public NavigationPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(20)); // Wait up to 20 seconds
        PageFactory.initElements(driver, this);
    }
    
    
    public void selectAppliances() {
        // Locate and click on the furniture image link
        WebElement Appliance = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//span[text()='Appliances']")));
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView({block: 'center', inline: 'nearest'});", Appliance);
        Appliance.click();

        // Scroll to the bottom of the page
        ((JavascriptExecutor) driver).executeScript("window.scrollTo(0, document.body.scrollHeight);");
        
        // Scroll back to the top of the page
        ((JavascriptExecutor) driver).executeScript("window.scrollTo(0, 0);");
    }

    public void goToOfferZone() {
        // Locate and click on the offer zone link
        WebElement offerZone = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//a[@class='TSD49J' and @href='/offers-list/top-deals?screen=dynamic&pk=themeViews%3DDT-OMU-A2%3ADT-OMU~widgetType%3DdealCard~contentType%3Dneo&otracker=nmenu_offer-zone']")));
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView({block: 'center', inline: 'nearest'});", offerZone);
        offerZone.click();

        // Scroll to the bottom of the page
        ((JavascriptExecutor) driver).executeScript("window.scrollTo(0, document.body.scrollHeight);");
        
        // Scroll back to the top of the page
        ((JavascriptExecutor) driver).executeScript("window.scrollTo(0, 0);");
    }
}