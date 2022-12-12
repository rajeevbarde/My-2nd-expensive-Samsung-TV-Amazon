import actions.PageActions;
import amazon.factories.DriverFactory;
import amazon.pages.HomePage;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebDriver;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class Amazon2ndExpensiveSamsungTVTest {
    private HomePage homePage;
    private PageActions pageActions;
    private WebDriver driver = DriverFactory.getDriver();

    @BeforeEach
    void initialize() {
        homePage = new HomePage(driver);
        pageActions = new PageActions(driver);
    }
    @DisplayName("Validate that customer can land on Amazon.in home page")
    @Test
    void assertThatAmazonHomePageIsLoaded() {
        homePage.visitHomePage();
        assertAll("Home page",
                () -> assertEquals ("https://www.amazon.in/", pageActions.getCurrentPageURL()),
                () -> assertEquals("Online Shopping site in India: Shop Online for Mobiles, Books, Watches, Shoes and More - Amazon.in", driver.getTitle()),
                () -> assertTrue(homePage.getHamburgerMenu().isDisplayed()));
    }

}//class
