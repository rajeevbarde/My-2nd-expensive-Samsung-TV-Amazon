package amazon.pages;

import actions.PageActions;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

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



}
