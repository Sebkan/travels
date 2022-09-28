import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.time.Duration;

public class SignUpTestTest extends DriverFactory {

    @Test
    public void signUp() {
        String lastName = "Kanecki";
        int randomNumber = (int) (Math.random()*1000);
        String email = "test"+randomNumber+"@tester.pl";
        driver.findElements(By.xpath("//li[@id='li_myaccount']")).stream()
                        .filter(WebElement::isDisplayed)
                        .findFirst()
                        .ifPresent(WebElement::click);
        driver.findElements(By.xpath("//a[contains(text(),' Sign Up')]")).get(1).click();
        driver.findElement(By.name("firstname")).sendKeys("Sebastian");
        driver.findElement(By.name("lastname")).sendKeys(lastName);
        driver.findElement(By.name("phone")).sendKeys("666666666");
        driver.findElement(By.name("email")).sendKeys(email);
        driver.findElement(By.name("password")).sendKeys("test123456");
        driver.findElement(By.name("confirmpassword")).sendKeys("test123456");
        driver.findElement(By.xpath("//button[text()=' Sign Up']")).click();

        FluentWait<WebDriver> wait = new FluentWait<>(driver);
        wait.ignoring(NoSuchElementException.class);
        wait.withTimeout(Duration.ofSeconds(3));
        wait.pollingEvery(Duration.ofMillis(300));
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//h3[@class='RTL']")));
        WebElement heading = driver.findElement(By.xpath("//h3[@class='RTL']"));
        Assert.assertTrue(heading.getText().contains(lastName));
        Assert.assertEquals(heading.getText(), "Hi, Sebastian Kanecki");
    }
}
