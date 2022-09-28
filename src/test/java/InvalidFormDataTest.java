import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.time.Duration;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

public class InvalidFormDataTest extends DriverFactory {

    @Test
    public void notFullData() {
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
    @Test
    public void wrongEmail(){
        driver.findElements(By.xpath("//a[text()=' My Account ']")).stream()
                .filter(WebElement::isDisplayed)
                .findFirst()
                .ifPresent(WebElement::click);
        driver.findElements(By.xpath("//a[text()='  Sign Up']")).get(1).click();
        driver.findElement(By.name("firstname")).sendKeys("Sebastian");
        driver.findElement(By.name("lastname")).sendKeys("Sebuskowy");
        driver.findElement(By.name("phone")).sendKeys("666666666");
        driver.findElement(By.name("email")).sendKeys("test.pl");
        driver.findElement(By.name("password")).sendKeys("test123456");
        driver.findElement(By.name("confirmpassword")).sendKeys("test123456");
        driver.findElement(By.xpath("//button[text()=' Sign Up']")).click();
        FluentWait<WebDriver> wait = new FluentWait<>(driver);
        wait.ignoring(NoSuchElementException.class);
        wait.withTimeout(Duration.ofSeconds(2));
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@class='alert alert-danger']//p")));
        String alert = driver.findElement(By.xpath("//div[@class='alert alert-danger']//p")).getText();
        Assert.assertEquals(alert,"The Email field must contain a valid email address.");
    }
}
