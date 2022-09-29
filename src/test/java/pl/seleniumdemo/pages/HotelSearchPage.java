package pl.seleniumdemo.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;

import java.time.Duration;

public class HotelSearchPage {
    @FindBy(xpath = "//span[text()='Search by Hotel or City Name']")
    private WebElement searchHotelSpan;
    @FindBy(xpath = "//div[@id='select2-drop']//input")
    private WebElement inputHotelName;

    @FindBy(name="checkin")
    private WebElement checkinInput;
    @FindBy(name="checkout")
    private WebElement checkoutInput;

    @FindBy(id = "travellersInput")
    public WebElement travellersInput;
    @FindBy(id = "adultPlusBtn")
    private WebElement adultNumber;
    @FindBy(id ="childPlusBtn")
    private WebElement childNumber;
    @FindBy(css = "button[type='submit']")
    private WebElement submitButton;

    private WebDriver driver;

    public HotelSearchPage(WebDriver driver){
        PageFactory.initElements(driver, this);
        this.driver = driver;
    }

    public void setCityName(String cityName){
        searchHotelSpan.click();
        inputHotelName.sendKeys(cityName);
        String locator = String.format("//span[@class='select2-match' and text()='%s']",cityName);
        FluentWait<WebDriver> wait = new FluentWait<>(driver);
        wait.withTimeout(Duration.ofSeconds(5));
        wait.ignoring(NoSuchElementException.class);
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(locator)));
        driver.findElement(By.xpath(locator)).click();
    }

    public void setDates(String checkIn, String checkOut){
        checkinInput.sendKeys(checkIn);
        checkoutInput.sendKeys(checkOut);
    }
    public void setTravellers(int adultsToAdd, int childToAdd){
        travellersInput.click();
            addTravellers(adultNumber,adultsToAdd);
            addTravellers(childNumber,childToAdd);
    }
    private void addTravellers (WebElement travellerBtn, int numberOfTravellers){
        for (int i = 0; i < numberOfTravellers; i++){
            travellerBtn.click();
        }
    }
    public void performSearch(){
        submitButton.click();
    }
    /*public void setOldStyleTravellers (int addAdults, int addChild){
        for(int i=0; i < addAdults; i++){
            adultNumber.click();
        }
        for (int i=0; i < addChild; i++){
            childNumber.click();
        }
    }*/
}
