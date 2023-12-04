package stepDefinitions.withoutLogin;

import io.cucumber.java.BeforeAll;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.example.pageObject.*;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import testRunners.AppHooks;

import java.util.List;

public class WithoutLoginStepDefinitions {
    private static WebDriver driver;
    private static HomePage homePage;
    private static ListingPage listingPage;
    private static ProductPage productPage;
    private static CartPage cartPage;
    private String prevProductQuantity = "";
    private String currProductQuantity = "";
    private String previousUrl = "";
    private String currentUrl = "";

    @BeforeAll
    public static void init() {
        driver = AppHooks.getDriver();
        homePage = new HomePage(driver);
        listingPage = new ListingPage(driver);
        productPage = new ProductPage(driver);
        cartPage = new CartPage(driver);
    }

    @Given("I am on the homepage")
    public void i_am_on_the_homepage() {
        previousUrl = driver.getCurrentUrl();
    }

    @When("I navigate to Mobiles and back")
    public void i_navigate_to_mobiles_and_back() throws InterruptedException {
        homePage.navigateToMobilesAndBack();
        currentUrl = driver.getCurrentUrl();
    }

    @Then("I should stay on the same page")
    public void i_should_stay_on_the_same_page() {
        Assert.assertEquals(previousUrl, currentUrl);
    }

    @When("I search for {string}")
    public void i_search_for(String searchKeyword) {
        homePage.enterSearchKey(searchKeyword);
    }

    @Then("I get the details of the last mobile on the page")
    public void i_get_the_details_of_the_last_mobile_on_the_page() {
        listingPage.printLastMobileNameAndPrice();
    }

    @When("I go to today's deals")
    public void i_go_to_today_s_deals() {
        homePage.goToTodaysDeals();
    }

    @When("I select the third product")
    public void i_select_the_third_product() {
        listingPage.selectThirdProduct();
    }

    @When("I remember the quantity of the selected product")
    public void i_remember_the_quantity_of_the_selected_product() {
        prevProductQuantity = productPage.getProductQuantity();
    }

    @When("I add the product to the cart")
    public void i_add_the_product_to_the_cart() throws InterruptedException {
        productPage.addToCartAndGoToCart();
    }

    @Then("the product quantity in the cart should match the remembered quantity")
    public void the_product_quantity_in_the_cart_should_match_the_remembered_quantity() {
        Boolean match = false;
        List<WebElement> cartItems = cartPage.getCartItems();
        for (WebElement cartItem : cartItems) {
            currProductQuantity = cartItem.findElement(By.cssSelector("span.a-dropdown-prompt")).getText();
            if (prevProductQuantity.equalsIgnoreCase(currProductQuantity)) {
                match = true;
                break;
            }
        }
        Assert.assertTrue(match);
    }
}
