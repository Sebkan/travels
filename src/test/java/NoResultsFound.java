import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.Test;

public class NoResultsFound {

    @Test
    public void noResults(){
        WebDriverManager.chromedriver().setup();
        WebDriver driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get("http://www.kurs-selenium.pl/demo/");
        driver.findElement(By.cssSelector("input[name='checkin']")).click();
        driver.findElement(By.xpath("(//th)[@class='next'][1]")).click();
        driver.findElements(By.xpath("//td[text()='11']")).stream()
                                                                       .filter(WebElement::isDisplayed)
                                                                       .findFirst()
                                                                       .ifPresent(WebElement::click);
        driver.findElements(By.xpath("//td[text()='15']")).stream()
                                                                       .filter(WebElement::isDisplayed)
                                                                       .findFirst()
                                                                       .ifPresent(WebElement::click);
        driver.findElement(By.id("travellersInput")).click();
        driver.findElement(By.id("adultPlusBtn")).click();
        driver.findElement(By.id("childPlusBtn")).click();
        driver.findElement(By.xpath("//button[text()=' Search']")).click();

        WebElement heading = driver.findElement(By.cssSelector("h2[class='text-center']"));
        Assert.assertTrue(heading.isDisplayed());
        Assert.assertEquals(heading.getText(),"No Results Found");
        driver.quit();
    }
}
