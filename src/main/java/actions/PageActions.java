/* Thanks to Pigeon but minimal - https://github.com/PramodKumarYadav/pigeon */
package actions;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;

public class PageActions {
    private WebDriver driver;
    private static WebDriverWait longWait;
    public PageActions(WebDriver driver) {
        this.driver = driver;
        longWait = new WebDriverWait(driver, Duration.ofSeconds(30));
    }
    public void waitForPageToLoad() {
        longWait.until(webDriver1 -> ((JavascriptExecutor) driver).executeScript("return document.readyState").equals("complete"));
    }
    public String getCurrentPageURL() {
        return driver.getCurrentUrl();
    }
}
