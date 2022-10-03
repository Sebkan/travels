package pl.seleniumdemo.utils;

import com.aventstack.extentreports.MediaEntityBuilder;
import com.aventstack.extentreports.MediaEntityModelProvider;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.File;
import java.io.IOException;
import java.time.Duration;

public class SeleniumHelper {

    public static void waitForElementToExist(WebDriver driver, By locator) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.presenceOfElementLocated(locator));
    }

    public static void waitForElementToBeVisible(WebDriver driver, WebElement webElement){
        WebDriverWait wait = new WebDriverWait(driver,Duration.ofSeconds(10));
        wait.until(ExpectedConditions.visibilityOf(webElement));
    }
    public static void waitForList (WebDriver driver, By locator) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(browser -> browser.findElements(locator).size() > 0);
    }

    public static MediaEntityModelProvider getScreenshot(WebDriver driver) throws IOException {
        String path = takeScreenshot(driver);
        return MediaEntityBuilder.createScreenCaptureFromPath(path).build();
    }

    public static String takeScreenshot( WebDriver driver) throws IOException {
        int randomNumber = (int)(Math.random()*1000);
        String fileName = "screenshot"+randomNumber+".png";
        TakesScreenshot screenshot = (TakesScreenshot) driver;
        File file = screenshot.getScreenshotAs(OutputType.FILE);
        String path = "src/test/resources/screenshots/"+fileName;

        FileUtils.copyFile(file,new File(path));
        return path;
    }
}
