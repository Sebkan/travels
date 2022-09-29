package pl.seleniumdemo.tests;

import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;
import pl.seleniumdemo.pages.HotelSearchPage;

import java.time.Duration;
import java.util.List;
import java.util.stream.Collectors;

public class HotelSearchTest extends DriverFactory {

    @Test
    public void searchHotel() {
        HotelSearchPage hotelSearchPage = new HotelSearchPage(driver);
        hotelSearchPage.setCityName("Dubai");
        FluentWait<WebDriver> wait = new FluentWait<>(driver);
        wait.withTimeout(Duration.ofSeconds(5));
        wait.ignoring(NoSuchElementException.class);
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//span[@class='select2-match' and text()='Dubai']")));
        driver.findElement(By.xpath("//span[@class='select2-match' and text()='Dubai']")).click();
        hotelSearchPage.setDates("17/10/2022","20/10/2022");
        hotelSearchPage.setTravellers();
        hotelSearchPage.performSearch();
        List<String> hotelNames = driver.findElements(By.cssSelector("h4[class='RTL go-text-right mt0 mb4 list_title']")).stream()
                                                                                                                         .map(e->e.getAttribute("textContent"))
                                                                                                                         .collect(Collectors.toList());
        Assert.assertEquals(hotelNames.get(0),"Jumeirah Beach Hotel");
        Assert.assertEquals(hotelNames.get(1),"Oasis Beach Tower");
        Assert.assertEquals(hotelNames.get(2),"Rose Rayhaan Rotana");
        Assert.assertEquals(hotelNames.get(3), "Hyatt Regency Perth");
    }

        @Test
        public void noResults(){
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
        }
    }

