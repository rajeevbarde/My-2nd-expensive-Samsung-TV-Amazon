package amazon.pages;

import actions.PageActions;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.ArrayList;
import java.util.List;

public class TelevisionPage {

    private PageActions pageActions;
    private WebDriver driver;
    @FindBy(id = "nav-search-label-id")
    private WebElement searchCategory;

    public TelevisionPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
        pageActions = new PageActions(driver);
    }

    public WebElement getSearchCategory() {
        searchCategory = pageActions.waitUntilElementIsVisibleAndClickable(searchCategory);
        return searchCategory;
    }

    public TelevisionPage clickBrand(String brand) {
        var brandLocator = By.xpath("//span[@class='a-list-item']//span[text()='"+brand+"']");
        pageActions.scrollToElement(brandLocator);
        var brandWebElem = pageActions.waitUntilElementIsVisibleAndClickable(brandLocator);
        brandWebElem.click();
        pageActions.waitForPageToLoad();

        return this;
    }

    public TelevisionPage sortBy(String sortValue) {
        var sortLocator = By.xpath("//span[@class='a-dropdown-label' and text()='Sort by:']");
        var sortByElem = pageActions.waitUntilElementIsVisibleAndClickable(sortLocator);
        sortByElem.click();

        driver.findElement(By.xpath("//a[text()='"+sortValue+"']")).click();
        pageActions.waitForPageToLoad();

        return this;
    }

    public boolean ResultsContainsSameBrand(String brand) {
        String resultsXpath = "//div[@data-component-type='s-search-result']//h2//span";
        List<WebElement> brands = driver.findElements(By.xpath(resultsXpath));

        return brands.stream().allMatch(val -> val.getText().toLowerCase().contains(brand));

        /* ArrayList<String> list = new ArrayList<String>();
        for (WebElement elem:
                samsungs) {
            System.out.println(elem.getText());
            list.add(elem.getText());
        }
        */
    }

    public boolean ResultContainsPriceDesc() {
        String resultsXpath = "//span[@class='a-price-whole']";
        List<WebElement> tvPrices = driver.findElements(By.xpath(resultsXpath));
        boolean isDesc = false;

        int ctr = 0, prevPrice = Integer.MAX_VALUE;
        for (WebElement tvPrice:
                tvPrices) {
            if (ctr > 5) break;  // Amazon sorting has bug - 1 result is not in descending order.Checking only top 5

            int currPrice = Integer.parseInt((tvPrice.getText()).replace(",", ""));
            if(currPrice <= prevPrice)
            {
                isDesc = true;
                prevPrice = currPrice;
            }
            else
            {
                isDesc = false;
                break;
            }

            ctr++;
        }

        return isDesc;
    }
}
