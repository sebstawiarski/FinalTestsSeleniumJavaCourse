/*
package pl.seleniumdemo.utils;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.concurrent.TimeUnit;

public class SeleniumHelper {

    public static void waitForElementToExist(WebDriver driver, By locator) {
        WebDriverWait wait = new WebDriverWait(driver, 10L);
        wait.until(ExpectedConditions.presenceOfElementLocated(locator));

    }

    public static void waitForElementToExist(WebDriver driver, WebElement element) {
        WebDriverWait wait = new WebDriverWait(driver, 10L);
        wait.until(ExpectedConditions.visibilityOf(element));
    }

    public static void waitForEmptyList(WebDriver driver, By locator) {
        WebDriverWait wait = new WebDriverWait(driver, 10L);
        wait.until(browser -> browser.findElements(locator).size() > 0);
    }

}
*/




