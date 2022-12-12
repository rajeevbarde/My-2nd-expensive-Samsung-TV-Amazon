package amazon.pages;

import actions.PageActions;
import amazon.config.EnvFactory;
import com.typesafe.config.Config;
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
    private WebElement HamburgerMenu;

    public HomePage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
        pageActions = new PageActions(driver);
    }

    public HomePage visitHomePage()
    {
        driver.get(HOME_PAGE_URL);
        pageActions.waitForPageToLoad();
        return this;
    }
    public WebElement getHamburgerMenu()
    {
        return HamburgerMenu;
    }
}
