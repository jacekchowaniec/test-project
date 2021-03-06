package com.demoqa.utils;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.File;
import java.util.List;

import static com.demoqa.driver.SeleniumBase.EXPLICIT_TIMEOUT;
import static com.demoqa.driver.SeleniumBase.driver;

public class PageAction {

    private WebDriverWait wait;

    private Actions actions;

    public PageAction() {
        wait = new WebDriverWait(driver, EXPLICIT_TIMEOUT);
        actions = new Actions(driver);
    }

    public void insertText(WebElement element, String value) {
        element.sendKeys(value);
    }

    public void click(WebElement element) {
        element.click();
    }

    public void clickEnterOnElement(WebElement element) {
        element.sendKeys(Keys.ENTER);
    }

    public void jsClick(WebElement clickElement) {
        JavascriptExecutor executor = (JavascriptExecutor) driver;
        executor.executeScript("arguments[0].click();", clickElement);
    }

    public void clickIfInputExist(List<WebElement> elements) {
        waitForAjax();
        if (elements.size() > 0) {
            elements.forEach(WebElement::click);
        }
    }

    public void selectByValue(WebElement element, String value) {
        Select select = new Select(element);
        select.selectByValue(value);
    }

    public void uploadFile(WebElement element, String pathToFile) {
        File file = new File(pathToFile);
        element.sendKeys(file.getAbsolutePath());
    }

    public void waitForVisibilityOfWebElement(WebElement element) {
        wait.until(ExpectedConditions.visibilityOf(element));
    }

    public void waitForElementToBeClickable(WebElement element) {
        wait.until(ExpectedConditions.elementToBeClickable(element));
    }

    public void switchToNewWindow() {
        for (String winHandle : driver.getWindowHandles()) {
            driver.switchTo().window(winHandle);
        }
    }

    public String getElementAttribute(WebElement element, String attribute) {
        return element.getAttribute(attribute);
    }

    public void doubleClick(WebElement element) {
        actions.doubleClick(element).perform();
    }

    public void waitForAjax() {
        wait.until(new ExpectedCondition<Boolean>() {
            public Boolean apply(WebDriver d) {
                JavascriptExecutor js = (JavascriptExecutor) d;
                return (Boolean) js.executeScript("return jQuery.active == 0");
            }
        });
    }


}

