package pl.seleniumdemo.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;
import java.util.stream.Collectors;

public class SignUpPage {

    @FindBy(name="firstname")
    private WebElement firstNameInput;
    @FindBy(name = "lastname")
    public WebElement lastNameInput;
    @FindBy(name="phone")
    private WebElement phoneInput;
    @FindBy(name="email")
    private WebElement emailInput;
    @FindBy(name="password")
    private WebElement passwordInput;
    @FindBy(name="confirmpassword")
    private WebElement confirmPassInput;
    @FindBy(xpath="//button[text()=' Sign Up']")
    private WebElement signUpBtn;
    @FindBy(xpath = "//div[@class='alert alert-danger']//p")
    private List<WebElement> errorList;
    @FindBy (xpath = "//div[@class='alert alert-danger']//p")
    private WebElement emailError;

    public SignUpPage(WebDriver driver){
        PageFactory.initElements(driver, this);
    }

    public void setNameLastPhone (String firstName, String lastName, String phoneNumber){
        firstNameInput.sendKeys(firstName);
        lastNameInput.sendKeys(lastName);
        phoneInput.sendKeys(phoneNumber);
    }
    public void setEmail (String email){
        emailInput.sendKeys(email);
    }
    public void setPassword(String password, String confirmPss){
        passwordInput.sendKeys(password);
        confirmPassInput.sendKeys(confirmPss);
    }
    public void submit(){
        signUpBtn.click();
    }
    public List<String> getErrorNames(){
        return errorList.stream().map(WebElement::getText).collect(Collectors.toList());
    }
    public String getEmailError(){
        return emailError.getText();
    }


        /*
        driver.findElement(By.name("email")).sendKeys(email);
        driver.findElement(By.name("password")).sendKeys("test123456");
        driver.findElement(By.name("confirmpassword")).sendKeys("test123456");
        driver.findElement(By.xpath("//button[text()=' Sign Up']")).click();*/
}
