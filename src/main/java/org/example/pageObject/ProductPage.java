package org.example.pageObject;

import org.example.abstractComponent.AbstractComponent;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class ProductPage extends AbstractComponent {
    WebDriver driver;
    @FindBy(id = "add-to-cart-button")
    WebElement addToCartButton;
    @FindBy(id = "attach-popover-lgtbox")
    WebElement popover;
    @FindBy(id = "nav-cart")
    WebElement navcart;
    @FindBy(id = "productTitle")
    WebElement productTitle;
    @FindBy(css = ".a-box-inner #selectQuantity input")
    WebElement productQuantity;
    @FindBy(css = "div[id*='DELIVERY_BLOCK'] .a-text-bold")
    WebElement deliveryDate;

    public ProductPage(WebDriver driver) {
        super(driver);
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }


    public String getProductQuantity() {
        waitForWebElementToAppear(productTitle);
        return productQuantity.getAttribute("value");
    }

    public void addToCartAndGoToCart() throws InterruptedException {
        click(addToCartButton);
        try {
            click(popover);
        } catch (Exception e) {
        } finally {
            actionClick(navcart);
        }
    }

    public void printProductDetailsAndMoveBack(String mainWindowHandle) {
        System.out.println(productTitle.getText());
        System.out.println(deliveryDate.getText());
        driver.close();
        driver.switchTo().window(mainWindowHandle);
    }
}

