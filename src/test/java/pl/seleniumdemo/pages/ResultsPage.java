package pl.seleniumdemo.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;
import java.util.stream.Collectors;

public class ResultsPage {

    @FindBy(css = "h4[class='RTL go-text-right mt0 mb4 list_title']")
    private List<WebElement> hotelList;
    @FindBy(css = "h2[class='text-center']")
    public WebElement heading;

    public ResultsPage(WebDriver driver) {
        PageFactory.initElements(driver, this);
    }

    public List<String> getHotelNames() {
        return hotelList.stream()
                .map(e -> e.getAttribute("textContent"))
                .collect(Collectors.toList());
    }

    public String getHeadingText() {
        return heading.getText();
    }


}
