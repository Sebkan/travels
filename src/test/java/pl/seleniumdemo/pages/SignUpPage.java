package pl.seleniumdemo.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import pl.seleniumdemo.model.User;
import pl.seleniumdemo.utils.SeleniumHelper;

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

    private WebDriver driver;

    public SignUpPage(WebDriver driver){
        PageFactory.initElements(driver, this);
        this.driver = driver;
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
        SeleniumHelper.waitForList(driver,By.xpath("//div[@class='alert alert-danger']//p"));
        return errorList.stream().map(WebElement::getText).collect(Collectors.toList());
    }
    public String getEmailError(){
        SeleniumHelper.waitForElementToBeVisible(driver,emailError);
        return emailError.getText();
    }

    public void fillSignUpForm(String firstName,String lastName,String phoneNumber,String email,String password) {
        firstNameInput.sendKeys(firstName);
        lastNameInput.sendKeys(lastName);
        phoneInput.sendKeys(phoneNumber);
        emailInput.sendKeys(email);
        passwordInput.sendKeys(password);
        confirmPassInput.sendKeys(password);
        signUpBtn.click();
    }
    public void fillSignUpForm(User user) {
        firstNameInput.sendKeys(user.getFirstName());
        lastNameInput.sendKeys(user.getLastName());
        phoneInput.sendKeys(user.getPhone());
        emailInput.sendKeys(user.getEmail());
        passwordInput.sendKeys(user.getPassword());
        confirmPassInput.sendKeys(user.getPassword());
        signUpBtn.click();
    }

}
