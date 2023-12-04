package org.example.pageObject;

import org.example.abstractComponent.AbstractComponent;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class SignupPage extends AbstractComponent {
    WebDriver driver;
    @FindBy(id = "nav-link-accountList")
    WebElement accountEle;
    @FindBy(id = "ap_email")
    WebElement emailEle;
    @FindBy(id = "continue")
    WebElement continueEle;
    @FindBy(id = "ap_password")
    WebElement passwordEle;
    @FindBy(id = "signInSubmit")
    WebElement signinEle;

    public SignupPage(WebDriver driver) {
        super(driver);
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }


    public void login(String phoneNumber, String password) throws InterruptedException {
        sendKeys(emailEle, phoneNumber);
        click(continueEle);

        sendKeys(passwordEle, password);
        click(signinEle);
    }
}
