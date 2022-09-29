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
import java.util.List;

public class HotelSearchPage {
    @FindBy(xpath = "//span[text()='Search by Hotel or City Name']")
    private WebElement searchHotelSpan;
    @FindBy(xpath = "//div[@id='select2-drop']//input")
    private WebElement inputHotelName;
    @FindBy(xpath = "//span[@class='select2-match' and text()='Dubai']")
    private WebElement cityMatch;
    @FindBy(name="checkin")
    private WebElement checkinInput;
    @FindBy(name="checkout")
    private WebElement checkoutInput;

    @FindBy(name = "travellers")
    private WebElement travellersInput;
    @FindBy(id = "adultPlusBtn")
    private WebElement adultNumber;
    @FindBy(id ="childPlusBtn")
    private WebElement childNumber;
    @FindBy(css = "button[type='submit']")
    private WebElement submitButton;

    public HotelSearchPage(WebDriver driver){
        PageFactory.initElements(driver, this);
    }

    public void setCityName(String cityName){
        searchHotelSpan.click();
        inputHotelName.sendKeys(cityName);
    }

    public void setDates(String checkIn, String checkOut){
        checkinInput.sendKeys(checkIn);
        checkoutInput.sendKeys(checkOut);
    }
    public void setTravellers(){
        travellersInput.click();
        adultNumber.click();
        childNumber.click();
    }
    public void performSearch(){
        submitButton.click();
    }
}
