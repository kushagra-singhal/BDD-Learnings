package org.example.pageObject;

import org.example.abstractComponent.AbstractComponent;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

public class CartPage extends AbstractComponent {
    WebDriver driver;

    @FindBy(css = "div[id*='sc-active'] div[id*='sc-active']")
    List<WebElement> cartItems;

    public CartPage(WebDriver driver) {
        super(driver);
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }


    public List<WebElement> getCartItems() {
        return cartItems;
    }
}
