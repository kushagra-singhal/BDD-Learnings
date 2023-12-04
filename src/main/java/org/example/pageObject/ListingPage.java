package org.example.pageObject;

import org.example.abstractComponent.AbstractComponent;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.Iterator;
import java.util.List;
import java.util.Set;

public class ListingPage extends AbstractComponent {
    WebDriver driver;

    @FindBy(css = ".s-card-container")
    List<WebElement> searchResults;
    @FindBy(css = "li.a-list-normal")
    List<WebElement> products;
    @FindBy(css = ".s-search-results .s-card-container img")
    List<WebElement> productsType2;
    @FindBy(xpath = "//div[contains(@class,'Grid-module__gridDisplayGrid')]/div[3]")
    WebElement thirdDeal;
    @FindBy(css = "#primeRefinements .a-checkbox")
    WebElement primeCheckbox;
    @FindBy(css = "#primeRefinements .a-checkbox input")
    WebElement primeCheckboxInput;

    public ListingPage(WebDriver driver) {
        super(driver);
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public void printLastMobileNameAndPrice() {
        waitForWebElementToAppear(driver.findElement(By.cssSelector(".s-card-container")));
        WebElement lastMobile = searchResults.get(searchResults.size() - 1);

        waitForWebElementToAppear(lastMobile.findElement(By.cssSelector("h2 span")));
        String title = lastMobile.findElement(By.cssSelector("h2 span")).getText();
        waitForWebElementToAppear(lastMobile.findElement(By.cssSelector(".a-price-whole")));
        String price = lastMobile.findElement(By.cssSelector(".a-price-whole")).getText();

        System.out.println("Last mobile name : " + title + " \n price : " + price);
    }

    public void selectThirdProduct() {
        thirdDeal.click();
        if (products.size() == 0)
            products = productsType2;
        products.get(0).click();
        Set<String> windowHandles = driver.getWindowHandles();
        Iterator it = windowHandles.iterator();
        String lastWindowId = "";
        while (it.hasNext()) {
            lastWindowId = it.next().toString();
        }
        driver.switchTo().window(lastWindowId);
    }

    public void selectPrimeAndPrintFirstProductDetails() throws InterruptedException {
        primeCheckbox.click();
        click(searchResults.get(0).findElement(By.cssSelector("h2 span")));
        String mainWindowHandle = driver.getWindowHandle();
        Set<String> allWindowHandles = driver.getWindowHandles();
        String newTabHandle = "";
        for (String handle : allWindowHandles) {
            if (!handle.equals(mainWindowHandle)) {
                newTabHandle = handle;
                break;
            }
        }
        driver.switchTo().window(newTabHandle);
    }

    public WebElement getPrimeCheckboxInput() {
        return primeCheckboxInput;
    }
}
