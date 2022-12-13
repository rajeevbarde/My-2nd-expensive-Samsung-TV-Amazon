import actions.PageActions;
import amazon.factories.DriverFactory;
import amazon.pages.HomePage;
import amazon.pages.TelevisionPage;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;

import org.junit.jupiter.api.TestMethodOrder;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
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

    @AfterAll
    public void closeAll() {
        driver.close();
        driver.quit();

    }
    @DisplayName("1. Validate that customer can land on Amazon.in home page")
    @Test
    @Order(1)
    void assertThatAmazonHomePageIsLoaded() {
        homePage.visitHomePage();

        assertAll("Home page",
                () -> assertEquals ("https://www.amazon.in/", pageActions.getCurrentPageURL()),
                () -> assertEquals("Online Shopping site in India: Shop Online for Mobiles, Books, Watches, Shoes and More - Amazon.in", driver.getTitle()),
                () -> assertTrue(homePage.getHamburgerMenu().isDisplayed()));
    }

    @DisplayName("2. Validate that customer can reach Television page")
    @Test
    @Order(2)
    void assertThatCustomerReachTelevisionPage() {
        homePage
                .ClickHamburgerMenu()
                .ClickMenuCategory("TV, Appliances, Electronics")
                .ClickMenuSubCategory("Televisions");

        assertAll("Television page",
                () -> assertTrue(pageActions.getCurrentPageURL().contains("gp/browse.htm")),
                () -> assertTrue(televisionPage.getSearchCategory().isDisplayed()));
    }

    @DisplayName("3. Validate that customer can filter 'Samsung' and sort price : High to low")
    @Test
    @Order(3)
    void assertThatCustomerFilterBrandAndSortPriceDesc() {
        televisionPage
                .clickBrand("Samsung")
                .sortBy("Price: High to Low");

        assertAll("Result page",
                () -> assertTrue(televisionPage.ResultsContainsSameBrand("samsung")),
                () -> assertTrue(televisionPage.ResultContainsPriceDesc()));
    }

    @DisplayName("4. Validate that customer can select 2nd highest result with 'About' details")
    @Test
    @Order(4)
    void assertThatCustomerSelect2ndExpensiveWithDetails()  {
        televisionPage
                .SelectNProduct(2)
                .switchTab();

        assertAll("Product page",
                () -> assertTrue(televisionPage.getAboutItemLbel().isDisplayed()));

        var texts = driver.findElement(By.xpath("//div[@id='feature-bullets']"));
        System.out.println("\033[0;36m" + texts.getText() + "\033[0m"); //Print in CYAN
    }
}//class
