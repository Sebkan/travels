package pl.seleniumdemo.tests;

import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;
import pl.seleniumdemo.pages.HotelSearchPage;
import pl.seleniumdemo.pages.ResultsPage;

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
        hotelSearchPage.setTravellers(1,1);
        hotelSearchPage.performSearch();

        ResultsPage resultsPage = new ResultsPage(driver);
        List<String> hotelNames = resultsPage.getHotelNames();
        Assert.assertEquals(hotelNames.get(0),"Jumeirah Beach Hotel");
        Assert.assertEquals(hotelNames.get(1),"Oasis Beach Tower");
        Assert.assertEquals(hotelNames.get(2),"Rose Rayhaan Rotana");
        Assert.assertEquals(hotelNames.get(3), "Hyatt Regency Perth");
    }

        @Test
        public void noResults()  {
            HotelSearchPage hotelSearchPage = new HotelSearchPage(driver);
            hotelSearchPage.setDates("11/11/2022","15/11/2022");
            hotelSearchPage.setTravellers(1,1);
            hotelSearchPage.performSearch();

            ResultsPage resultsPage = new ResultsPage(driver);
            Assert.assertEquals(resultsPage.getHeadingText(),"No Results Found");
            Assert.assertTrue(resultsPage.heading.isDisplayed());
        }
    }

