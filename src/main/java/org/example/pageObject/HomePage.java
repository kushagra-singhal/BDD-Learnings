package org.example.pageObject;

import org.example.abstractComponent.AbstractComponent;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

public class HomePage extends AbstractComponent {
    WebDriver driver;

    @FindBy(id = "twotabsearchtextbox")
    WebElement searchInput;
    @FindBy(id = "nav-search-submit-button")
    WebElement searchButton;
    @FindBy(id = "nav-orders")
    WebElement ordersEle;
    @FindBy(id = "nav-link-accountList")
    WebElement accountsEle;
    @FindBy(linkText = "Today's Deals")
    WebElement todaysDealsEle;
    @FindBy(id = "nav-hamburger-menu")
    WebElement allMenuEle;
    @FindBy(css = "a.hmenu-item div")
    List<WebElement> menuItems;
    @FindBy(xpath = "//a[contains(text(),'Mobile Phones')]")
    WebElement mobilePhonesOption;
    @FindBy(id = "nav-item-signout")
    WebElement signoutAnchor;
    @FindBy(id = "nav-logo-sprites")
    WebElement companyLogo;

    public HomePage(WebDriver driver) {
        super(driver);
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public void enterSearchKey(String searchKey) {
        sendKeys(searchInput, searchKey);
        actionClick(searchButton);
    }

    public void navigateToMobilesAndBack() throws InterruptedException {
        Actions actions = new Actions(driver);
        click(allMenuEle);
        waitForWebElementToAppear(driver.findElement(By.cssSelector("a.hmenu-item div")));
        click(driver.findElements(By.cssSelector("a.hmenu-item div")).stream().filter(item -> item.getText().toLowerCase().contains("mobiles")).findFirst().get());
        waitForWebElementToBeClickable(mobilePhonesOption);
        actions.moveToElement(mobilePhonesOption).click().build().perform();
        driver.navigate().back();
    }

    public void goToTodaysDeals() {
        click(todaysDealsEle);
    }

    public void goToOrders() {
        click(ordersEle);
    }

    public void goToAccount() {
        click(accountsEle);
    }

    public void logout() {
        Actions actions = new Actions(driver);
        actions.moveToElement(accountsEle).perform();
        click(signoutAnchor);
    }
}
