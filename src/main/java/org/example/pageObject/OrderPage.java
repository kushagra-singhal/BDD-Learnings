package org.example.pageObject;

import org.example.abstractComponent.AbstractComponent;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

public class OrderPage extends AbstractComponent {
    WebDriver driver;
    @FindBy(css = ".a-dropdown-container")
    WebElement dropdown;
    @FindBy(css = ".a-dropdown-prompt")
    WebElement dropdownQty;
    @FindBy(css = ".a-popover-inner li")
    List<WebElement> options;

    public OrderPage(WebDriver driver) {
        super(driver);
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }


    public void listPastYearOrders() {
        click(dropdown);
        click(options.get(2));
    }

    public WebElement getDropdownQty() {
        return dropdownQty;
    }
}
