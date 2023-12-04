package org.example.pageObject;

import org.example.abstractComponent.AbstractComponent;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

public class PaymentMethodPage extends AbstractComponent {
    WebDriver driver;


    @FindBy(className = "a-box-group")
    List<WebElement> paymentMethods;

    @FindBy(css = ".a-alert-container a")
    WebElement updatePreferenceEle;

    @FindBy(css = "#fullName input")
    WebElement fullnameEle;
    @FindBy(css = "#phoneNumber input")
    WebElement phoneNumberEle;
    @FindBy(css = "#postalCode input")
    WebElement postalCodeEle;
    @FindBy(css = "#line1 input")
    WebElement line1Ele;
    @FindBy(css = "#line2 input")
    WebElement line2Ele;
    @FindBy(css = "#city input")
    WebElement cityEle;
    @FindBy(css = "#stateOrRegion input")
    WebElement stateOrRegionEle;
    @FindBy(css = "input.apx-wrap-text")
    WebElement addPurchasePreferenceButton;
    @FindBy(id = "apx-save-address-button-id")
    WebElement saveButton;
    @FindBy(id = "Continue")
    WebElement continueButton;
    @FindBy(css = "span[id*='announce']")
    WebElement announceButton;

    public PaymentMethodPage(WebDriver driver) {
        super(driver);
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public void addNewPaymentMethod(String name, String phoneNumber, String postalCode, String line1, String line2, String city, String stateOrRegion) throws InterruptedException {

        click(addPurchasePreferenceButton);

        sendKeys(fullnameEle, name);
        sendKeys(phoneNumberEle, phoneNumber);
        sendKeys(postalCodeEle, postalCode);
        sendKeys(line1Ele, line1);
        sendKeys(line2Ele, line2);
        sendKeys(cityEle, city);
        sendKeys(stateOrRegionEle, stateOrRegion);
        actionClick(saveButton);
        actionClick(continueButton);
        actionClick(announceButton);
        driver.navigate().refresh();
        Thread.sleep(1000);
    }

    public int getPaymentMethodsCount() {
        return paymentMethods.size();
    }


    public void gotoUpdatePreference() {
        click(updatePreferenceEle);
    }
}
