package org.example.abstractComponent;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class AbstractComponent {
    private WebDriver driver;
    private WebDriverWait wait;
    private Actions actions;

    public AbstractComponent(WebDriver driver) {
        this.driver = driver;
        wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        actions = new Actions(driver);
        PageFactory.initElements(driver, this);
    }

    public void waitForElementToAppear(By findBy) {
        wait.until(ExpectedConditions.visibilityOfElementLocated(findBy));
    }

    public void waitForWebElementToAppear(WebElement findBy) {
        wait.until(ExpectedConditions.visibilityOf(findBy));
    }

    public void waitForWebElementToBeClickable(WebElement findBy) {
        wait.until(ExpectedConditions.elementToBeClickable(findBy));
    }

    public void waitForElementToDisappear(WebElement element) {
        wait.until(ExpectedConditions.invisibilityOf(element));
    }

    public void click(By findBy) {
        wait.until(ExpectedConditions.elementToBeClickable(driver.findElement(findBy))).click();
    }

    public void sendKeys(By findBy, String keys) {
        wait.until(ExpectedConditions.elementToBeClickable(driver.findElement(findBy))).sendKeys(keys);
    }

    public String getText(By findBy) {
        return wait.until(ExpectedConditions.visibilityOf(driver.findElement(findBy))).getText();
    }

    public void click(WebElement element) {
        wait.until(ExpectedConditions.elementToBeClickable(element)).click();
    }

    public void sendKeys(WebElement element, String keys) {
        wait.until(ExpectedConditions.elementToBeClickable(element)).sendKeys(keys);
    }

    public String getText(WebElement element) {
        return wait.until(ExpectedConditions.visibilityOf(element)).getText();
    }

    public void actionClick(WebElement element) {
        actions.moveToElement(element).click().perform();
    }

}
