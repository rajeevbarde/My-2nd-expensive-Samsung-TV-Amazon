import actions.PageActions;
import amazon.factories.DriverFactory;
import amazon.pages.HomePage;
import amazon.pages.TelevisionPage;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class Amazon2ndExpensiveSamsungTVTest {
    private HomePage homePage;
    private TelevisionPage televisionPage;
    private PageActions pageActions;
    private WebDriver driver = DriverFactory.getDriver();

    @BeforeEach
    void initialize() {
        homePage = new HomePage(driver);
        televisionPage = new TelevisionPage(driver);
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

    @DisplayName("Validate that customer can reach Television page")
    @Test
    void assertThatCustomerReachTelevisionPage() {
        homePage
                .ClickHamburgerMenu()
                .ClickMenuCategory("TV, Appliances, Electronics")
                .ClickMenuSubCategory("Televisions");

        assertAll("Television page",
                () -> assertTrue(pageActions.getCurrentPageURL().contains("gp/browse.htm")),
                () -> assertTrue(televisionPage.getSearchCategory().isDisplayed()));
    }

}//class
