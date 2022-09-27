import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.time.Duration;
import java.util.List;
import java.util.stream.Collectors;

public class HotelSearch {

    @Test
    public void searchHotel()  {
        WebDriverManager.chromedriver().setup();
        WebDriver driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get("http://www.kurs-selenium.pl/demo/");
        driver.findElement(By.xpath("//span[text()='Search by Hotel or City Name']")).click();
        driver.findElement(By.xpath("//div[@id='select2-drop']//input")).sendKeys("Dubai");
        FluentWait<WebDriver> wait = new FluentWait<>(driver);
        wait.withTimeout(Duration.ofSeconds(5));
        wait.ignoring(NoSuchElementException.class);
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//span[@class='select2-match' and text()='Dubai']")));
        driver.findElement(By.xpath("//span[@class='select2-match' and text()='Dubai']")).click();
        driver.findElement(By.name("checkin")).sendKeys("17/10/2022");
        driver.findElement(By.name("checkout")).click();
        WebElement arrowMonth = driver.findElement(By.xpath(("(//th)[@class='next'][4]")));
        arrowMonth.click();
        arrowMonth.click();
        arrowMonth.click();
        List<WebElement> months = driver.findElements(By.xpath("//td[@class='day ' and text()='6']"));
        for (WebElement elements : months){
            if (elements.isDisplayed()){
                elements.click();
            }
        }
        driver.findElement(By.id("travellersInput")).click();
        driver.findElement(By.id("adultPlusBtn")).click();
        driver.findElement(By.id("childPlusBtn")).click();
        driver.findElement(By.cssSelector("button[type='submit']")).click();

        /*List<WebElement> hotelNames = driver.findElements(By.cssSelector("h4[class='RTL go-text-right mt0 mb4 list_title']"));
        for (WebElement names : hotelNames){
            System.out.println(names.getText());
        }*/
        List<String> hotelNames = driver.findElements(By.cssSelector("h4[class='RTL go-text-right mt0 mb4 list_title']")).stream()
                                                                                                                         .map(e->e.getAttribute("textContent"))
                                                                                                                         .collect(Collectors.toList());

        Assert.assertEquals("Jumeirah Beach Hotel", hotelNames.get(0));
        Assert.assertEquals("Oasis Beach Tower", hotelNames.get(1));
        Assert.assertEquals("Rose Rayhaan Rotana", hotelNames.get(2));
        Assert.assertEquals("Hyatt Regency Perth", hotelNames.get(3));
    }
}
