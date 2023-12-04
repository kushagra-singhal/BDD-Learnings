package org.example.pageObject;

import org.example.abstractComponent.AbstractComponent;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

public class AddressPage extends AbstractComponent {
    WebDriver driver;
    @FindBy(css = ".add-new-address-button")
    WebElement newAddressButton;
    @FindBy(id = "address-ui-widgets-enterAddressFullName")
    WebElement fullNameEle;
    @FindBy(id = "address-ui-widgets-enterAddressPhoneNumber")
    WebElement phoneNumberEle;
    @FindBy(id = "address-ui-widgets-enterAddressPostalCode")
    WebElement postalCodeEle;
    @FindBy(id = "address-ui-widgets-enterAddressLine1")
    WebElement line1Ele;
    @FindBy(id = "address-ui-widgets-enterAddressLine2")
    WebElement line2Ele;
    @FindBy(id = "address-ui-widgets-form-submit-button-announce")
    WebElement confirmAddressButton;
    @FindBy(id = "div[id*='address-block']")
    List<WebElement> addressList;

    public AddressPage(WebDriver driver) {
        super(driver);
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }


    public void addNewAddress(String name, String phoneNumber, String postalCode, String line1, String line2) {
        Actions actions = new Actions(driver);
        click(newAddressButton);

        sendKeys(fullNameEle, name);
        sendKeys(phoneNumberEle, phoneNumber);
        sendKeys(postalCodeEle, postalCode);
        sendKeys(line1Ele, line1);
        sendKeys(line2Ele, line2);
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("window.scrollBy(0,150)", "");
        actions.moveToElement(confirmAddressButton).click().build().perform();
    }

    public int getAddressCount() {
        return driver.findElements(By.cssSelector("div[id*='address-block']")).size();
    }
}
