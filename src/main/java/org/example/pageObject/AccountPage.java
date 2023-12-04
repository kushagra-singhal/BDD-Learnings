package org.example.pageObject;

import org.example.abstractComponent.AbstractComponent;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

public class AccountPage extends AbstractComponent {
    WebDriver driver;

    @FindBy(css = "a h2")
    List<WebElement> accountOperations;
    public AccountPage(WebDriver driver) {
        super(driver);
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }


    public void goToSection(String section) {
        for (WebElement operation :
                accountOperations) {
            if (operation.getText().toLowerCase().contains(section)) {
                operation.click();
                break;
            }
        }
    }
}
