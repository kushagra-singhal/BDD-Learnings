package stepDefinitions.withLogin;

import io.cucumber.datatable.DataTable;
import io.cucumber.java.AfterAll;
import io.cucumber.java.Before;
import io.cucumber.java.BeforeAll;
import io.cucumber.java.en.*;
import org.example.pageObject.*;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.TestInstance;
import testRunners.AppHooks;

import java.util.Map;

public class WithLoginStepDefinitions {
    private static WebDriver driver;
    private static HomePage homePage;
    private static ListingPage listingPage;
    private static SignupPage signupPage;
    private static OrderPage orderPage;
    private static AccountPage accountPage;
    private static AddressPage addressPage;
    private static PaymentMethodPage paymentMethodPage;
    private static ProductPage productPage;
    private String phoneNumber = "";
    private String password = "";
    private String previousTimeperiod = "";
    private String currentTimeperiod = "";
    private int previousAddressesCount;
    private int currentAddressesCount;
    private int previousPaymentsCount;
    private int currentPaymentsCount;
    private static boolean loginAgain = true;

    @BeforeAll
    public static void init() throws InterruptedException {
        driver = AppHooks.getDriver();
        homePage = new HomePage(driver);
        signupPage = new SignupPage(driver);
        listingPage = new ListingPage(driver);
        productPage = new ProductPage(driver);
        orderPage = new OrderPage(driver);
        paymentMethodPage = new PaymentMethodPage(driver);
        addressPage = new AddressPage(driver);
        accountPage = new AccountPage(driver);

    }

    @AfterAll(order = 2)
    public static void clearAll() {
        homePage.logout();
    }

    @Given("I am logged in with valid credentials")
    public void i_am_logged_in_with_valid_credentials() throws InterruptedException {
        if (loginAgain) {
            phoneNumber = AppHooks.getProperties().getProperty("phoneNumber");
            password = AppHooks.getProperties().getProperty("password");
            homePage.goToAccount();
            signupPage.login(phoneNumber, password);
            loginAgain = false;
        }
    }

    @When("I search for {string}")
    public void i_search_for(String searchKeyword) {
        homePage.enterSearchKey(searchKeyword);
    }

    @When("I select prime delivery and print the first product details")
    public void i_select_prime_delivery_and_print_the_first_product_details() throws InterruptedException {

        String mainWindowHandle = driver.getWindowHandle();
        listingPage.selectPrimeAndPrintFirstProductDetails();
        productPage.printProductDetailsAndMoveBack(mainWindowHandle);

    }

    @Then("the prime checkbox should be selected")
    public void the_prime_checkbox_should_be_selected() {
        Assert.assertTrue(listingPage.getPrimeCheckboxInput().isSelected());
    }

    @When("I go to the orders page")
    public void i_go_to_the_orders_page() {
        homePage.goToOrders();
    }

    @When("I list past year orders")
    public void i_list_past_year_orders() {
        previousTimeperiod = orderPage.getDropdownQty().getText();
        orderPage.listPastYearOrders();
        currentTimeperiod = orderPage.getDropdownQty().getText();

    }

    @Then("the time period should have changed")
    public void the_time_period_should_have_changed() {
        Assert.assertNotEquals(previousTimeperiod, currentTimeperiod);
    }


    @When("I go to the address page")
    public void i_go_to_the_address_page() {
        homePage.goToAccount();
        accountPage.goToSection("address");
    }

    @When("I add a new address with the following details:")
    public void i_add_a_new_address_with_the_following_details(DataTable dataTable) {
        previousAddressesCount = addressPage.getAddressCount();
        Map<String, String> addressData = dataTable.asMaps(String.class, String.class).get(0);

        String name = addressData.get("name");
        String phoneNumber = addressData.get("phoneNumber");
        String postalCode = addressData.get("postalCode");
        String line1 = addressData.get("line1");
        String line2 = addressData.get("line2");

        addressPage.addNewAddress(name, phoneNumber, postalCode, line1, line2);

        homePage.goToAccount();
        accountPage.goToSection("address");

        currentAddressesCount = addressPage.getAddressCount();
    }

    @Then("the address should be added successfully")
    public void the_address_should_be_added_successfully() {
        Assert.assertEquals(previousAddressesCount , currentAddressesCount);
    }

    @When("I go to the payment methods page")
    public void i_go_to_the_payment_methods_page() {
        homePage.goToAccount();
        accountPage.goToSection("payment");
    }

    @When("I add a new payment method with the following details:")
    public void i_add_a_new_payment_method_with_the_following_details(DataTable dataTable) throws InterruptedException {
        paymentMethodPage.gotoUpdatePreference();

        previousPaymentsCount = paymentMethodPage.getPaymentMethodsCount();
        Map<String, String> paymentData = dataTable.asMaps(String.class, String.class).get(0);

        String name = paymentData.get("name");
        String phoneNumber = paymentData.get("phoneNumber");
        String postalCode = paymentData.get("postalCode");
        String line1 = paymentData.get("line1");
        String line2 = paymentData.get("line2");
        String city = paymentData.get("city");
        String stateOrRegion = paymentData.get("stateOrRegion");

        paymentMethodPage.addNewPaymentMethod(name, phoneNumber, postalCode, line1, line2, city, stateOrRegion);

        currentPaymentsCount = paymentMethodPage.getPaymentMethodsCount();
    }

    @Then("the payment method should be added successfully")
    public void the_payment_method_should_be_added_successfully() {
        Assert.assertEquals(previousPaymentsCount + 1, currentPaymentsCount);
    }


}