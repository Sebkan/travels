package pl.seleniumdemo.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LoggedUserPage {

    @FindBy(xpath = "//h3[@class='RTL']")
    public WebElement heading;

    public LoggedUserPage(WebDriver driver) {
        PageFactory.initElements(driver, this);
    }

    public String headingText() {
        return heading.getText();
    }
}
