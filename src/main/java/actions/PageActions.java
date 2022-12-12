/* Thanks to Pigeon but minimal and modified - https://github.com/PramodKumarYadav/pigeon */
package actions;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;

public class PageActions {
    private WebDriver driver;
    private static WebDriverWait longWait;
    private static WebDriverWait shortWait;
    public PageActions(WebDriver driver) {
        this.driver = driver;
        shortWait = new WebDriverWait(driver, Duration.ofSeconds(10));
        longWait = new WebDriverWait(driver, Duration.ofSeconds(30));
    }
    public void waitForPageToLoad() {
        longWait.until(webDriver1 -> ((JavascriptExecutor) driver).executeScript("return document.readyState").equals("complete"));
    }
    public String getCurrentPageURL() {
        return driver.getCurrentUrl();
    }

    public WebElement waitUntilElementIsVisibleAndClickable(By locator)
    {
        shortWait.until(ExpectedConditions.visibilityOfElementLocated(locator));
        shortWait.until(ExpectedConditions.elementToBeClickable(locator));

        return driver.findElement(locator);
    }
    public WebElement waitUntilElementIsVisibleAndClickable(WebElement locator)
    {
        shortWait.until(ExpectedConditions.visibilityOf(locator));
        shortWait.until(ExpectedConditions.elementToBeClickable(locator));

        return locator;
    }

}
