package pl.seleniumdemo.tests;

import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import pl.seleniumdemo.pages.HotelSearchPage;
import pl.seleniumdemo.pages.ResultsPage;
import pl.seleniumdemo.utils.ExcelReader;
import pl.seleniumdemo.utils.SeleniumHelper;

import java.io.IOException;
import java.util.List;

public class HotelSearchTest extends BaseTest {

    @Test
    public void searchHotel() throws IOException {
        ExtentTest test = extentReports.createTest("search Hotel Test");
        HotelSearchPage hotelSearchPage = new HotelSearchPage(driver);
        hotelSearchPage.setCityName("Dubai");
        test.log(Status.PASS,"Setting city done", SeleniumHelper.getScreenshot(driver));
        hotelSearchPage.setDates("17/10/2022","20/10/2022");
        test.log(Status.PASS,"Setting dates done",SeleniumHelper.getScreenshot(driver));
        hotelSearchPage.setTravellers(1,1);
        test.log(Status.PASS,"Setting travellers",SeleniumHelper.getScreenshot(driver));
        hotelSearchPage.performSearch();
        test.log(Status.PASS,"Performing search done",SeleniumHelper.getScreenshot(driver));

        ResultsPage resultsPage = new ResultsPage(driver);
        List<String> hotelNames = resultsPage.getHotelNames();
        Assert.assertEquals(hotelNames.get(0),"Jumeirah Beach Hotel");
        Assert.assertEquals(hotelNames.get(1),"Oasis Beach Tower");
        Assert.assertEquals(hotelNames.get(2),"Rose Rayhaan Rotana");
        Assert.assertEquals(hotelNames.get(3), "Hyatt Regency Perth");
        test.log(Status.PASS,"Assertions passed",SeleniumHelper.getScreenshot(driver));
    }

        @Test
        public void noResults() throws IOException {
            ExtentTest test = extentReports.createTest("No Results Test");
            HotelSearchPage hotelSearchPage = new HotelSearchPage(driver);
            hotelSearchPage.setDates("11/11/2022","15/11/2022");
            test.log(Status.PASS,"Setting dates", SeleniumHelper.getScreenshot(driver));
            hotelSearchPage.setTravellers(1,1);
            test.log(Status.PASS,"Adding travellers", SeleniumHelper.getScreenshot(driver));
            hotelSearchPage.performSearch();
            test.log(Status.PASS,"Performing search done",SeleniumHelper.getScreenshot(driver));
            ResultsPage resultsPage = new ResultsPage(driver);
            Assert.assertEquals(resultsPage.getHeadingText(),"No Results Found");
            Assert.assertTrue(resultsPage.heading.isDisplayed());
            test.log(Status.PASS,"Assertions passed",SeleniumHelper.getScreenshot(driver));
        }
    @Test(dataProvider = "data")
    public void searchHotelWithDataProvider(String city, String hotel) throws IOException {
        ExtentTest test = extentReports.createTest("Search Hotel with DATA Provider For "+city);
        HotelSearchPage hotelSearchPage = new HotelSearchPage(driver);
        hotelSearchPage.setCityName(city);
        test.log(Status.PASS,"Setting city done", SeleniumHelper.getScreenshot(driver));
        hotelSearchPage.setDates("17/10/2022","20/10/2022");
        test.log(Status.PASS,"Setting dates done",SeleniumHelper.getScreenshot(driver));
        hotelSearchPage.setTravellers(1,1);
        test.log(Status.PASS,"Setting travellers",SeleniumHelper.getScreenshot(driver));
        hotelSearchPage.performSearch();
        test.log(Status.PASS,"Performing search done",SeleniumHelper.getScreenshot(driver));
        ResultsPage resultsPage = new ResultsPage(driver);
        List<String> hotelNames = resultsPage.getHotelNames();
        Assert.assertEquals(hotelNames.get(0),hotel);
        test.log(Status.PASS,"Assertions passed",SeleniumHelper.getScreenshot(driver));
    }
    @DataProvider
    public Object[][] data() throws IOException {
        return ExcelReader.readExcel("testData.xlsx");
    }
}

