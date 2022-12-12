package amazon.pages;

import actions.PageActions;
import amazon.config.EnvFactory;
import com.typesafe.config.Config;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class HomePage {
    private PageActions pageActions;
    private WebDriver driver;
    private static Config config = EnvFactory.getInstance().getConfig();
    private static final String HOME_PAGE_URL = config.getString("HOME_PAGE_URL");
    @FindBy(id = "nav-hamburger-menu")
    private WebElement hamburgerMenu;
    private WebElement menuCategory;
    private WebElement menuSubCategory;


    public HomePage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
        pageActions = new PageActions(driver);
    }
    public WebElement getHamburgerMenu() {
        hamburgerMenu = pageActions.waitUntilElementIsVisibleAndClickable(hamburgerMenu);
        return hamburgerMenu;}

    public HomePage ClickMenuCategory(String category) {
        menuCategory = pageActions.waitUntilElementIsVisibleAndClickable(By.xpath("//div[text()='"+category+"']"));
        menuCategory.click();
        return this;
    }

    public HomePage ClickMenuSubCategory(String subcat) {
        menuSubCategory = pageActions.waitUntilElementIsVisibleAndClickable(By.xpath("//li/a[text()='"+subcat+"']"));
        menuSubCategory.click();

        return this;
    }

    public HomePage ClickHamburgerMenu() {
        hamburgerMenu = pageActions.waitUntilElementIsVisibleAndClickable(hamburgerMenu);
        hamburgerMenu.click();

        return this;
    }

    public HomePage visitHomePage() {
        driver.get(HOME_PAGE_URL);
        pageActions.waitForPageToLoad();
        return this;
    }



}
