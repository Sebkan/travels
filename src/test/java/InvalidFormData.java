import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.testng.Assert;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import java.time.Duration;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

public class InvalidFormData {

    @Test
    public void notFullData() {
        WebDriverManager.chromedriver().setup();
        WebDriver driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get("http://www.kurs-selenium.pl/demo/");
        driver.findElements(By.xpath("//a[text()=' My Account ']")).stream()
                                                                                .filter(WebElement::isDisplayed)
                                                                                .findFirst()
                                                                                .ifPresent(WebElement::click);
        driver.findElements(By.xpath("//a[text()='  Sign Up']")).get(1).click();
        driver.findElement(By.xpath("//button[text()=' Sign Up']")).click();

        FluentWait<WebDriver> wait = new FluentWait<>(driver);
        wait.ignoring(NoSuchElementException.class);
        wait.withTimeout(Duration.ofSeconds(2));
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@class='alert alert-danger']//p")));
        List<String> alertList = driver.findElements(By.xpath("//div[@class='alert alert-danger']//p")).stream()
                                                                                                                    .map(WebElement::getText)
                                                                                                                    .collect(Collectors.toList());
        Assert.assertEquals(alertList.get(0),"The Email field is required.");
        Assert.assertEquals(alertList.get(1),"The Password field is required.");
        Assert.assertEquals(alertList.get(2),"The Password field is required.");
        Assert.assertEquals(alertList.get(3),"The First name field is required.");
        Assert.assertEquals(alertList.get(4),"The Last Name field is required.");

        /*SoftAssert softAssert = new SoftAssert();
        softAssert.assertTrue(alertList.contains("The Email field is required."));
        softAssert.assertTrue(alertList.contains("The Password field is required."));
        softAssert.assertTrue(alertList.contains("The Password field is required."));
        softAssert.assertTrue(alertList.contains("The First name field is required."));
        softAssert.assertTrue(alertList.contains("The Last Name field is required."));
        softAssert.assertAll();*/





    }
}
